
import java.util.LinkedList;
public class LRU extends PageReplacementAlgorithm{

   
    boolean[] hitMatrix;
    int[][] framesMatrix;
    LinkedList<Integer> framesList = new LinkedList<>();

    public LRU(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
       
        for(int iter = 0; iter < numOfPages; iter++){
            
        
            Integer pageNum = Integer.valueOf(pages[iter]);
            if(framesList.indexOf(pageNum) != -1){
                hitMatrix[iter] = true;
               
                framesList.removeFirstOccurrence(pageNum);
               
                framesList.addLast(pageNum);
            }else{
                
                if(pageCount == frameCount){
                    framesList.remove();
                }
               
                if(pageCount < frameSize){
                    pageCount++;
                }
               
                framesList.addLast(pageNum);
                
                hitMatrix[iter] = false;
            }
           
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