package MazeGeneratorAndSolver;

import java.util.Random;
import java.util.ArrayList;

public class DepthFirstSearch extends SearchAlgorithm{
    ArrayList<Node> Path = new ArrayList<>();
    protected int headX, headY;
    
    @Override
    public Node[][] NextStep(){
     
        Random r = new Random();
        if(step == 0){
            headY = grid.length-1;
            headX = 0;
            grid[headY][headX].setState(new Path());
            Path.add(grid[headY][headX]);
            step++;
        }
        else if(headX==grid.length-1 && headY == 0){
            step = -1;
        }
        else if(step == -1){
           return grid;
        }
        else{
            if(!checkNeighbours()){
               Path.get(Path.size()-1).setState(new Working());
               Path.remove(Path.size()-1);
               headX = Path.get(Path.size()-1).getX();
               headY = Path.get(Path.size()-1).getY();
               step++;
            }
        }
        return grid;
    }
       
    
    public boolean checkNeighbours(){
        if(headX + 1 < grid.length && grid[headY][headX+1].getState() instanceof Done && !grid[headY][headX].getRightWall()){
            headX = headX + 1;
            grid[headY][headX].setState(new Path());
            Path.add(grid[headY][headX]);
            return true;
            
        }
        else if(headY - 1 >= 0 && grid[headY-1][headX].getState() instanceof Done && !grid[headY][headX].getTopWall()){

            headY = headY-1;
            grid[headY][headX].setState(new Path());
            Path.add(grid[headY][headX]);
            return true;
            }
        
        else if(headX - 1 >= 0 && grid[headY][headX-1].getState() instanceof Done && !grid[headY][headX].getLeftWall()){
            headX = headX - 1;
            grid[headY][headX].setState(new Path());
            Path.add(grid[headY][headX]);
            return true;
            
        }
        else if(headY + 1 < grid.length && grid[headY+1][headX].getState() instanceof Done && !grid[headY][headX].getBottomWall()){
            headY = headY + 1;
            grid[headY][headX].setState(new Path());
            Path.add(grid[headY][headX]);
            return true;
            
        }
        return false;
    }
}

