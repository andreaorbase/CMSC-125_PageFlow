
import java.util.LinkedList;
public class LRU extends PageReplacementAlgorithm{

    // this class implements the least recently used algorithm
    // input: pages to get from storage
    // output: the hit / miss status
    //         the 2-dimensional hit/miss matrix for each page requested
    //         the 2-dimensional page frame matrix showing the page contents
    //         other outputs inherited from the PageReplacementAlgorithm class 
    boolean[] hitMatrix;
    int[][] framesMatrix;
    LinkedList<Integer> framesList = new LinkedList<>();// doubly linked list needed to easily perform LRU

    public LRU(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        // execute the algorithm
        for(int iter = 0; iter < numOfPages; iter++){
            
            // if page is found in the linked list don't insert
            Integer pageNum = Integer.valueOf(pages[iter]);
            if(framesList.indexOf(pageNum) != -1){
                hitMatrix[iter] = true;
                // remove first occurrence
                framesList.removeFirstOccurrence(pageNum);
                // insert to end of linked list
                framesList.addLast(pageNum);
            }else{
                
                if(pageCount == frameCount){
                    framesList.remove();
                }
                // check if array is full 
                // if array is full, remove least recently used page first
                if(pageCount < frameSize){
                    pageCount++;
                }
                // else insert it to end of linked list
                framesList.addLast(pageNum);
                
                hitMatrix[iter] = false;
            }
            // then save it to the matrix for the iteration
            int fListLength = framesList.size();
            for(int j = 0; j < fListLength; j++){
                pageFrames[j] = framesList.get(j);
            }
            for(int j = fListLength; j < frameSize; j++){
                pageFrames[j] = -1;
            }
            framesMatrix[iter] = pageFrames.clone();
        }
    }

    public boolean[] getHitMatrix() {
        return hitMatrix;
    }

    public int[][] getFramesMatrix() {
        return framesMatrix;
    }

}