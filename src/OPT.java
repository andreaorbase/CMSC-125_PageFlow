import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class OPT extends PageReplacementAlgorithm{

   
    boolean[] hitMatrix;
    int[][] framesMatrix;
 

    public OPT(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
      
        for(int iter = 0; iter < numOfPages; iter++){
           
            if(foundPage(pages[iter],iter)){
                hitMatrix[iter] = true;
            }else{
                if(pageCount == frameSize){
              
                    int frameToClear = -1;
               
                    for(int i = iter + 1; i < numOfPages; i++){
                     
                        int firstIndex = findPageIndex(pages[i], 0);
                        if(firstIndex > -1){
                            frameToClear = firstIndex;
                        }
                    }
                  
                    Set<Integer> intSet = new HashSet<>();
                    for(int i = iter + 1; i < numOfPages; i++){
                        int pageNum = pages[i];
                        
                        intSet.add(pageNum);
                    }
                    
                  
                    for(int i = 0; i < frameSize; i++){
                        if(!intSet.contains(pageFrames[i])){
                            frameToClear = i;
                            break;
                        }
                    }
                 
                    pageFrames[frameToClear] = pages[iter];
                }else{
                  
                    pageFrames[pageCount] = pages[iter];
                    if(pageCount < frameSize){
                        pageCount++;
                    }
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