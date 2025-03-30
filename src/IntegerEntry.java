
public class IntegerEntry implements Comparable<IntegerEntry> {
    private int key;
    private int freq;

    IntegerEntry(int k, int v){
        key = k;
        freq = v;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setFreq(int value) {
        this.freq = value;
    }

    public int getKey() {
        return key;
    }

    public int getFreq() {
        return freq;
    }

    @Override
    public int compareTo(IntegerEntry anotherEntry) {
        return this.getFreq() - anotherEntry.getFreq();
    }

}
