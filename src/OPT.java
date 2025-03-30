import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

public class OPT extends PageReplacementAlgorithm{

    // this class implements the optimal replacement algorithm
    // input: pages to get from storage
    // output: the hit / miss status
    //         the 2-dimensional hit/miss matrix for each page requested
    //         the 2-dimensional page frame matrix showing the page contents
    //         other outputs inherited from the PageReplacementAlgorithm class 
    boolean[] hitMatrix;
    int[][] framesMatrix;
    // doubly linked list needed to easily perform OPT

    public OPT(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        // execute the algorithm
        for(int iter = 0; iter < numOfPages; iter++){
            // if page is found in the pageFrames array don't insert
            // if page exists, just use it
            if(foundPage(pages[iter],iter)){
                hitMatrix[iter] = true;
            }else{
                if(pageCount == frameSize){
                    // if page does not exist, replace the one by priority
                    int frameToClear = -1;
                    // 2. which will be used the farthest in the future
                
                    // for every frame, find the last occurrence 
                    for(int i = iter + 1; i < numOfPages; i++){
                        // find if the page exists in the page frames
                        int firstIndex = findPageIndex(pages[i], 0);
                        if(firstIndex > -1){
                            frameToClear = firstIndex;
                        }
                    }
                    // 1. which will never be used in the future
                    Set<Integer> intSet = new HashSet<>();
                    for(int i = iter + 1; i < numOfPages; i++){
                        int pageNum = pages[i];
                        
                        intSet.add(pageNum);
                    }
                    
                    // check if never used
                    for(int i = 0; i < frameSize; i++){
                        if(!intSet.contains(pageFrames[i])){
                            frameToClear = i;
                            break;
                        }
                    }
                    // replace the page
                    pageFrames[frameToClear] = pages[iter];
                }else{
                    // add the page
                    pageFrames[pageCount] = pages[iter];
                    if(pageCount < frameSize){
                        pageCount++;
                    }
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