package MazeGeneratorAndSolver;

public class NoSolve extends SearchAlgorithm{
    @Override
    public Node[][] NextStep(){
        step = -1;
        return grid;
    } 
}