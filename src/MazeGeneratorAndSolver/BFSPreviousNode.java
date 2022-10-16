package MazeGeneratorAndSolver;

public class BFSPreviousNode {
    private final Node current;
    private final BFSPreviousNode previous;
    
    public BFSPreviousNode(Node current, BFSPreviousNode previous){
        this.current = current;
        this.previous = previous;
    }
    public Node getCurrent(){
        return current;
    }
    public BFSPreviousNode getPrevious(){
        return previous;
    }
}

