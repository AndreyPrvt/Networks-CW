package Models;

/**
 * Created by andreyprvt on 13.12.15.
 */
public class Node {
    private int adress; // good coding practice would have this as private
    private boolean enable;
    static int count;

    public Node() {
        this.adress = count++;
        enable = true;
    }
    public String toString() { // Always a good idea for debuging
        return String.valueOf(adress);
        // JUNG2 makes good use of these.
    }
    public void makeEnable(){
        enable = true;
    }
    public void makeDisable(){
        enable = false;
    }

    public boolean isEnable() {
        return enable;
    }
}
