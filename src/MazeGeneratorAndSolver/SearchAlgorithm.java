package MazeGeneratorAndSolver;

public abstract class SearchAlgorithm {
    protected Node[][] grid;
    protected int step = 0;
    
    public void setGrid(Node[][] m){
        grid = m;
    }
    
    public abstract Node[][] NextStep();   
    
    public int getStep(){
        return step;
    }
    
}

