package MazeGeneratorAndSolver;

public abstract class MazeGenAlgorithm{  
    protected int step = 0;
    protected Node[][] grid;
    
    public MazeGenAlgorithm(int n){
        grid = new Node[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                grid[i][j] = new Node(j,i);
            }
        }      
    }
    
    public abstract Node[][] NextStep();
    
    public int getStep(){
        return step;
    }
    
    public void removeNodeWallTop(Node[][] grid, int x, int y){
        grid[y][x].removeTopWall();
        grid[y-1][x].removeBottomWall();
    }
    public void removeNodeWallBottom(Node[][] grid, int x, int y){
        grid[y][x].removeBottomWall();
        grid[y+1][x].removeTopWall();
    }
    public void removeNodeWallLeft(Node[][] grid, int x, int y){
        grid[y][x].removeLeftWall();
        grid[y][x-1].removeRightWall();
    }
    public void removeNodeWallRight(Node[][] grid, int x, int y){
        grid[y][x].removeRightWall();
        grid[y][x+1].removeLeftWall();
    }
}


