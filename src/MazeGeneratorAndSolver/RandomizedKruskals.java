package MazeGeneratorAndSolver;

import java.util.ArrayList;
import java.util.Random;

public class RandomizedKruskals extends MazeGenAlgorithm{
    ArrayList<ArrayList<Node>> group = new ArrayList<>();
    int locA;
    int locB;
 
    public RandomizedKruskals(int n){
        super(n);
    }
    
    @Override
    public Node[][] NextStep(){
        return NextStepRecursive(0);
    }

    public Node[][] NextStepRecursive(int callCount){
        ArrayList<Node> temp = new ArrayList<>();
        if(step != -1){
        Random r = new Random();
        int x, y;
        x = r.nextInt(grid.length);
        y = r.nextInt(grid.length);
        if(grid[y][x].getState() instanceof Incomplete){
            grid[y][x].setState(new Working());
            temp.add(grid[y][x]);
            group.add(temp);
        }
        if(group.get(0).size() == (grid.length * grid.length)){
            for(int i = 0; i<grid.length; i++){
                for(int j = 0; j<grid.length; j++){
                    grid[i][j].setState(new Done());
                }
            }
            step = -1;
            return grid;
        }
        if(callCount >= 150){
            return grid;
        }
        if(group.size() == 1){
            return NextStepRecursive(callCount+1);
        }
        checkNeighbours(x,y);
        step++;
        }
        return grid;
    }
    
    public void checkNeighbours(int x, int y){
        int neighbourX, neighbourY;
        ArrayList<Integer> dir = new ArrayList<>();
        int choice;
        dir.add(0);
        dir.add(1);
        dir.add(2);
        dir.add(3);
        Random r = new Random();
        for(int i = 0; i<4; i++){
            choice = dir.get(r.nextInt(dir.size()));
            dir.remove(Integer.valueOf(choice));
        if(choice == 0 && x + 1 < grid.length && grid[y][x+1].getState() instanceof Working && grid[y][x].getRightWall()){
            neighbourX = x + 1;
            neighbourY = y;
            if(searchArrayList(x,y,neighbourX,neighbourY)){

            if(locA != locB){
                removeNodeWallRight(grid,x,y);
                removeGroup();
            }
        }
        }
        if(choice == 1 && y - 1 >= 0 && grid[y-1][x].getState() instanceof Working && grid[y][x].getTopWall()){
            neighbourX = x;
            neighbourY = y - 1;
            if(searchArrayList(x,y,neighbourX,neighbourY)){ 

            if(locA != locB){
                removeNodeWallTop(grid,x,y);
                removeGroup();
            }
            }
        }
        
        if(choice == 2 && x - 1 >= 0 && grid[y][x-1].getState() instanceof Working && grid[y][x].getLeftWall()){
            neighbourX = x - 1;
            neighbourY = y;
            if(searchArrayList(x,y,neighbourX,neighbourY)){ 


            if(locA != locB){
                removeNodeWallLeft(grid,x,y);
                removeGroup();
            }
            
        }
        }
        if(choice == 3 && y + 1 < grid.length && grid[y+1][x].getState() instanceof Working && grid[y][x].getBottomWall()){
            neighbourX = x;
            neighbourY = y + 1;
            if(searchArrayList(x,y,neighbourX,neighbourY)){

            if(locA != locB){
                removeNodeWallBottom(grid,x,y);
                removeGroup();
            }
        }
        }
    }
    }
    
    public boolean searchArrayList(int x, int y, int neighbourX, int neighbourY){
        boolean a, b;
        a = false;
        b = false;
        
        for(int i=0; i < group.size();i++){
            for(int j=0; j<group.get(i).size();j++){
                if(group.get(i).get(j).compare(grid[y][x])){
                    locA = i;
                    a = true;
                }
                if(group.get(i).get(j).compare(grid[neighbourY][neighbourX])){
                    locB = i;
                    b = true;
                }
                if(a && b){
                    return true;
                }
            }
        }
        return false;
    }
    public void removeGroup(){
        group.get(locA).addAll(group.get(locB));
        group.get(locB).removeAll(group);
        group.remove(locB);
    }
}

