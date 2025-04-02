import java.util.ArrayList;

public class SC extends PageReplacementAlgorithm{

    
    boolean[] hitMatrix;
    int[][] framesMatrix;
   
    int head = 0; 
    int[] refBits; 

    public SC(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        refBits = new int[frameSize];
     
        for(int iter = 0; iter < numOfPages; iter++){
       
            if(foundPage(pages[iter],iter)){
                hitMatrix[iter] = true;
            
                for(int i = 0; i < pageCount; i++){
                    if(pages[iter] == pageFrames[i]){
                        refBits[i] = 1; 
                        break;
                    }
                }
            }else{
             
                if(pageCount == frameSize){
                    int index = head;
                 
                    int frameChosen = -1;
                    for(int i = 0; i < pageCount; i++){
                        int frame = (index+i) % frameSize;
                        if(refBits[frame] == 0){
                            frameChosen = frame;
                            break;
                        }
                    }
                 
                    for(int i = 0; i < pageCount; i++){
                        
                        if(refBits[i] == 1){
                            refBits[i] = 0;
                        }
                    }
                 
                    for(int i = 0; (i < pageCount && frameChosen == -1); i++){
                        int frame = (index+i) % frameSize;
                        if(refBits[frame] == 0){
                            frameChosen = frame;
                            break;
                        }
                    }
             
                    pageFrames[frameChosen] = pages[iter];
                    refBits[frameChosen] = 0;
                }else{
                    pageFrames[pageCount] = pages[iter];
                    refBits[pageCount] = 0;
                }
                
                if(pageCount < frameSize){
                    pageCount++;
                }
                hitMatrix[iter] = false;
            }
          
            head = (head + 1) % frameSize;
          
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