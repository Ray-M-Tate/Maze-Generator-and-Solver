package MazeGeneratorAndSolver;

public class Node {
    private boolean wallTop, wallBottom, wallLeft, wallRight;
    private NodeStatus state;
    private int x, y, index;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
        wallTop = true;
        wallBottom = true;
        wallLeft = true;
        wallRight = true;
        state = new Incomplete();
        index = -1;
    }
    public void setIndex(int n){
        index = n;
    }
    public int getIndex(){
        return index;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public NodeStatus getState(){
        return state;
    } 
    public void setState(NodeStatus s){
        state = s;
    }
    public void removeTopWall(){
        wallTop = false;
    }
    public void removeBottomWall(){
        wallBottom = false;
    }
    public void removeLeftWall(){
        wallLeft = false;
    }
    public void removeRightWall(){
        wallRight = false;
    }
    public boolean getTopWall(){
        return wallTop;
    }
    public boolean getBottomWall(){
        return wallBottom;
    }
    public boolean getRightWall(){
        return wallRight;
    }
    public boolean getLeftWall(){
        return wallLeft;
    }
    public boolean compare(Node n){
        if(this.x == n.getX() && this.y == n.getY())
            return true;
        else
            return false;
    }
    @Override
    public String toString(){
        return x+", "+y+"  ";
        
    }
    @Override
    public Node clone() throws CloneNotSupportedException{
        Node clonedNode = (Node)super.clone();
        clonedNode.state = this.state.clone();
        return clonedNode;
    }
}
