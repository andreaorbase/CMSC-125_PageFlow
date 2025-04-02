
public class FIFO extends PageReplacementAlgorithm{

    boolean[] hitMatrix;
    int[][] framesMatrix;

    public FIFO(int[] pages, int numOfPages, int frameSize){
        super(frameSize);
        framesMatrix = new int[numOfPages][frameSize];
        hitMatrix = new boolean[numOfPages];
        for(int iter = 0; iter < numOfPages; iter++){
            
            if(foundPage(pages[iter],iter)){
                hits[0] = true;
                hitCount++;
            }else{
                if(pageCount < frameSize){
                    pageFrames[pageCount] = pages[iter];
                    pageCount++;
                }else{
                    removeBottom(pageFrames);
                    pageFrames[frameCount-1] = pages[iter];
                }
                hits[0] = false;
                missCount++;
            }
            for(int i = 0; i < frameSize; i++){
                framesMatrix[iter][i] = pageFrames[i];
                hitMatrix[iter] = hits[0];
            }
        }
    }

    public boolean[] getHitMatrix() {
        return hitMatrix;
    }

    public int[][] getFramesMatrix() {
        return framesMatrix;
    }
    public void removeBottom(int[] frames){
        int[] tempArray = frames.clone();
        frames[0] = -1; // remove bottom
        for(int i = 0; i < (frameCount - 1); i++){
            frames[i] = tempArray[i+1];
        }
    }

}