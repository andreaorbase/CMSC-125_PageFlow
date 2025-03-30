
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

public class MFU extends PageReplacementAlgorithm{

    // this class implements the most frequently used algorithm
    // input: pages to get from storage
    // output: the hit / miss status
    //         the 2-dimensional hit/miss matrix for each page requested
    //         the 2-dimensional page frame matrix showing the page contents
    //         other outputs inherited from the PageReplacementAlgorithm class 
    boolean[] hitMatrix;
    int[][] framesMatrix;
    // priority queue needed to easily perform MFU
    PriorityQueue<IntegerEntry> queue = new PriorityQueue<>(10, (x,y) -> Integer.compare(y.getFreq(),x.getFreq()));

    public MFU(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        // execute the algorithm
        for(int iter = 0; iter < numOfPages; iter++){
            
            // if page is found in the priority queue don't insert
            int pageNum = pages[iter];
            Boolean isFound = false;
            Iterator values = queue.iterator();
            while(values.hasNext()){
                IntegerEntry value = ((IntegerEntry) values.next());
                if(value.getKey() == pageNum){
                    hitMatrix[iter] = true;
                    isFound = true;
                    // remove the element from queue
                    queue.remove(value);
                    // increment the frequency
                    value.setFreq(value.getFreq() + 1);
                    // insert to priority queue
                    queue.add(value);
                    break;
                }
            }
            
            if(!isFound){
                // check if array is full 
                // if array is full, remove most frequently used page first
                if(pageCount == frameCount){
                    IntegerEntry value = queue.poll();
                    int maxVal = value.getKey();
                    // remove the page with most frequent use
                    int index = 0;
                    for (int i = 0; i < pageCount; i++){
                        if(pageFrames[i] == maxVal){
                            pageFrames[i] = -1;
                            index = i;
                            break;
                        }
                    }
                    // move the elements to make space for other values
                    for (int i = index; i < frameSize - 1; i++){
                        pageFrames[i] = pageFrames[i+1];
                    }
                    pageFrames[frameSize-1] = pageNum;
                }
                // else insert it to end of priority queue
                // and page frame array
                
                queue.add(new IntegerEntry(pageNum, 1));
                if(pageCount < frameSize){
                    pageFrames[pageCount] = pageNum;
                    pageCount++;
                }
                hitMatrix[iter] = false;
            }
            // then save it to the matrix for the iteration
            ArrayList<Integer> framesList = new ArrayList<>();
            for(int i = 0; i < frameSize; i++){
                if(pageFrames[i] > -1){
                    framesList.add(Integer.valueOf(pageFrames[i]));
                }
            }
            int fListLength = framesList.size();
            for(int j = 0; j < fListLength; j++){
                framesMatrix[iter][j] = framesList.get(j);
            }
            for(int j = fListLength; j < frameSize; j++){
                framesMatrix[iter][j] = -1;
            }
        }
    }

    public boolean[] getHitMatrix() {
        return hitMatrix;
    }

    public int[][] getFramesMatrix() {
        return framesMatrix;
    }

}