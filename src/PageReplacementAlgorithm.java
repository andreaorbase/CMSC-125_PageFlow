
public class PageReplacementAlgorithm{
   int[] pageFrames;
   boolean[] hits;
   int pageCount;
   int frameCount;
   int hitCount;
   int missCount;
   
   PageReplacementAlgorithm(int frames){
      frameCount = frames;
      pageFrames = new int[frames];
      for(int i = 0; i < frames; i++){
        pageFrames[i] = -1;
      }
      hits = new boolean[frames];
      pageCount = 0;
      hitCount = 0;
      missCount = 0;
   }
   
   public boolean[] getHits() {
       return hits;
   }
   
   public int[] getPageFrames(){
       return pageFrames;
   }
   
   public int getFrameCount(){
       return frameCount;
   }    
   
   public int getHitCount(){
       return hitCount;
   }
   
   public int getMissCount(){
       return missCount;
   }

   public boolean foundPage(int page, int bound){
        boolean found = false;
        int upperBound = (bound < pageFrames.length) ? bound : pageFrames.length;
        for(int i = 0; i < upperBound; i++){
            if(pageFrames[i] == page){
                found = true;
                break;
            }
        }
        return found;
   }

   public int findPageIndex(int page, int start){
    int index = -1;
    for(int i = start; i < pageFrames.length; i++){
        if(pageFrames[i] == page){
            index = i;
            break;
        }
    }
    return index;
    }
}