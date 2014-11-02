package firespread;

public class Cell {
    
    public static final int EMPTY = 0;
    public static final int TREE = 1;
    public static final int BURNING = 2;
    private int status;
    int p;

    public Cell(int x){
        this.status = x;
    }
    
    // Status
    public int getStatus(){
        return this.status;
    }
    
    public void setStatus(int status){
       this.status = status;
    }
    
    // Probability
    public int getProb(){
        return this.p;
    }
    
    public void setProb(int p){
        this.p = p;
    }
    
}
