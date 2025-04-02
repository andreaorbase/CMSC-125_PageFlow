
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

public class MFU extends PageReplacementAlgorithm{

   
    boolean[] hitMatrix;
    int[][] framesMatrix;
    
    PriorityQueue<IntegerEntry> queue = new PriorityQueue<>(10, (x,y) -> Integer.compare(y.getFreq(),x.getFreq()));

    public MFU(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
       
        for(int iter = 0; iter < numOfPages; iter++){
            
         
            int pageNum = pages[iter];
            Boolean isFound = false;
            Iterator values = queue.iterator();
            while(values.hasNext()){
                IntegerEntry value = ((IntegerEntry) values.next());
                if(value.getKey() == pageNum){
                    hitMatrix[iter] = true;
                    isFound = true;
                 
                    queue.remove(value);
                 
                    value.setFreq(value.getFreq() + 1);
               
                    queue.add(value);
                    break;
                }
            }
            
            if(!isFound){
              
                if(pageCount == frameCount){
                    IntegerEntry value = queue.poll();
                    int maxVal = value.getKey();
                 
                    int index = 0;
                    for (int i = 0; i < pageCount; i++){
                        if(pageFrames[i] == maxVal){
                            pageFrames[i] = -1;
                            index = i;
                            break;
                        }
                    }
              
                    for (int i = index; i < frameSize - 1; i++){
                        pageFrames[i] = pageFrames[i+1];
                    }
                    pageFrames[frameSize-1] = pageNum;
                }
                
                
                queue.add(new IntegerEntry(pageNum, 1));
                if(pageCount < frameSize){
                    pageFrames[pageCount] = pageNum;
                    pageCount++;
                }
                hitMatrix[iter] = false;
            }
       
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