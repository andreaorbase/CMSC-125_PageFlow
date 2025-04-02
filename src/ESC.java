import java.util.ArrayList;
import java.util.Random;

public class ESC extends PageReplacementAlgorithm{

    boolean[] hitMatrix;
    int[][] framesMatrix;
    
    
    int head; 
    int[] refBits; 
    int[] modifyBits;

    public ESC(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        refBits = new int[frameSize];
        modifyBits = new int[numOfPages];
        for(int i = 0; i < numOfPages; i++){
            
            Random rand = new Random(System.currentTimeMillis());
            
            modifyBits[i] = rand.nextInt(2); 
        }
        
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
                    int frameNumModified = -1;
                    int frameNumNotModified = -1;
                    for(int i = 0; i < pageCount; i++){
                        int frame = (index+i) % frameSize;
                        if(refBits[frame] == 0 && modifyBits[frame] == 0 && frameNumNotModified == -1){
                            frameNumNotModified = frame;
                        }
                        if(refBits[frame] == 0 && modifyBits[frame] == 1 && frameNumModified == -1){
                            frameNumModified = frame;
                        }
                    }
                    for(int i = 0; i < pageCount; i++){
                        if(refBits[i] == 1){
                            refBits[i] = 0;
                        }
                    }
                    for(int i = 0; (i < pageCount && frameNumModified == -1 && frameNumNotModified == -1); i++){
                        int frame = (index+i) % frameSize;
                        if(refBits[frame] == 0 && modifyBits[iter] == 0 && frameNumNotModified == -1){
                            frameNumNotModified = frame;
                        }
                        if(refBits[frame] == 0 && modifyBits[iter] == 1 && frameNumModified == -1){
                            frameNumModified = frame;
                        }
                }
                if(frameNumModified > -1){
                    pageFrames[frameNumModified] = -1;
                    index = frameNumModified;
                }else if(frameNumNotModified > -1){
                    pageFrames[frameNumNotModified] = -1;
                    index = frameNumNotModified;
                }
                    pageFrames[index] = pages[iter];
                    refBits[index] = 0;
                }
                else{
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

    public int[] getModifyBits() {
        return modifyBits;
    }

}