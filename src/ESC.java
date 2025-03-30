import java.util.ArrayList;
import java.util.Random;

public class ESC extends PageReplacementAlgorithm{

    // this class implements the enhanced second chance algorithm
    // input: pages to get from storage
    // output: the hit / miss status
    //         the 2-dimensional hit/miss matrix for each page requested
    //         the 2-dimensional page frame matrix showing the page contents
    //         other outputs inherited from the PageReplacementAlgorithm class 
    boolean[] hitMatrix;
    int[][] framesMatrix;
    
    
    // circular linked list needed to easily perform ESC
    int head; // pointer for start of circular linked list
    int[] refBits; // reference bits for second chance
    int[] modifyBits; // modify bits for enhanced second chance

    public ESC(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        refBits = new int[frameSize];
        modifyBits = new int[numOfPages];
        // initialize randomness in modify bits
        for(int i = 0; i < numOfPages; i++){
            // unique random seed for every run of app
            Random rand = new Random(System.currentTimeMillis());
            // random numbers from 0 to 1 inclusive
            modifyBits[i] = rand.nextInt(2); 
        }
        // execute the algorithm
        for(int iter = 0; iter < numOfPages; iter++){
            // if page is found in the pageFrames array don't insert
            if(foundPage(pages[iter],iter)){
                hitMatrix[iter] = true;
                // find where to give second chance
                for(int i = 0; i < pageCount; i++){
                    if(pages[iter] == pageFrames[i]){
                        refBits[i] = 1; // second chance creation
                        break;
                    }
                }
            }else{
                // search for the page to be replaced
                // start at head
                if(pageCount == frameSize){
                    int index = head;
                    // values for detecting priority for removal
                    int frameNumModified = -1;
                    int frameNumNotModified = -1;
                    // first pass: find the possible victim pages
                    for(int i = 0; i < pageCount; i++){
                        // remove first page with refBit = 0
                        int frame = (index+i) % frameSize;
                        // find first occurrence of a victim page with
                        // modify bit equal to zero
                        if(refBits[frame] == 0 && modifyBits[frame] == 0 && frameNumNotModified == -1){
                            frameNumNotModified = frame;
                        }
                        // find first occurrence of a victim page with
                        // modify bit not equal to zero
                        if(refBits[frame] == 0 && modifyBits[frame] == 1 && frameNumModified == -1){
                            frameNumModified = frame;
                        }
                    }
                    // first pass fails or not, now decrease all second chances
                    for(int i = 0; i < pageCount; i++){
                        // use the second chance for the rest of pages
                        if(refBits[i] == 1){
                            refBits[i] = 0;
                        }
                    }
                    // second pass: find the possible victim pages
                    // only run if neither possible victim pages were not found
                    for(int i = 0; (i < pageCount && frameNumModified == -1 && frameNumNotModified == -1); i++){
                        // remove first page with refBit = 0
                        int frame = (index+i) % frameSize;
                        // find first occurrence of a victim page with
                        // modify bit equal to zero
                        if(refBits[frame] == 0 && modifyBits[iter] == 0 && frameNumNotModified == -1){
                            frameNumNotModified = frame;
                        }
                        // find first occurrence of a victim page with
                        // modify bit not equal to zero
                        if(refBits[frame] == 0 && modifyBits[iter] == 1 && frameNumModified == -1){
                            frameNumModified = frame;
                        }
                }
                // remove the one with modify bit 1 first
                // if it exists, else remove the one with 
                // modify bit 0
                if(frameNumModified > -1){
                    pageFrames[frameNumModified] = -1;
                    index = frameNumModified;
                }else if(frameNumNotModified > -1){
                    pageFrames[frameNumNotModified] = -1;
                    index = frameNumNotModified;
                }
                    // else insert it to the chosen page frame
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
            // move the circular queue head. page hit or page fault
            head = (head + 1) % frameSize;
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

    public int[] getModifyBits() {
        return modifyBits;
    }

}