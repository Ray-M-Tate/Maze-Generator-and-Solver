package MazeGeneratorAndSolver;

public class Coordinates {
    private int x;
    private int y;
    public Coordinates(int y, int x){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean compare(Coordinates n){
        if(x == n.getX() && y == n.getY()){
            return true;
        }
        else
            return false;
    }
    @Override
    public String toString(){
        return "" + x +", "+y;
    }
}
