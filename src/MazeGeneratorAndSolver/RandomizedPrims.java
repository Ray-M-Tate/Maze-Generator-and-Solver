package MazeGeneratorAndSolver;

import java.util.ArrayList;
import java.util.Random;

public class RandomizedPrims extends MazeGenAlgorithm{
    ArrayList<Coordinates> frontier = new ArrayList<>();
    
    public RandomizedPrims(int n){
        super(n);
    }

    @Override
    public Node[][] NextStep(){
        Random r = new Random();
        int x, y;
        if(step == 0){
            x = r.nextInt(grid.length);
            y = r.nextInt(grid.length);
            grid[y][x].setState(new Done());
            expandFrontier(grid,y,x);
            step++;
        }
        else if(step == -1){
        }
        else{
            int nextLocation = r.nextInt(frontier.size());
            x = frontier.get(nextLocation).getX();
            y = frontier.get(nextLocation).getY();
            randomRemoveWall(y,x);
            expandFrontier(grid,y,x);
            grid[y][x].setState(new Done());
            frontier.remove(nextLocation);
            step++;
            if(frontier.size()==0){
                step = -1;
            }
        }
        return grid;
    }
    
    public void randomRemoveWall(int y,int x){
        Random r = new Random();
        int random;
        int n = grid.length;
        
        while (true){
            random = r.nextInt(4);
            if(random == 0 && x+1<n){
                if(grid[y][x+1].getState() instanceof Done){
                    removeNodeWallRight(grid,x,y);
                    break;
                }
            }
            else if(random == 1 && x-1>=0){
                if(grid[y][x-1].getState() instanceof Done){
                    removeNodeWallLeft(grid,x,y);
                    break;
                }
            }
            else if(random == 2 && y+1<n){
                if(grid[y+1][x].getState() instanceof Done){
                    removeNodeWallBottom(grid,x,y);
                    break;
                }
            }
            else if(random == 3 && y-1>=0){
                if(grid[y-1][x].getState() instanceof Done){
                    removeNodeWallTop(grid,x,y);
                    break;
                }
            }
        }
    }
    
    public void expandFrontier(Node[][] grid,int y, int x){
        int n = grid.length;
        if(x+1<n && grid[y][x+1].getState() instanceof Incomplete){
            frontier.add(new Coordinates(y,x+1));
            grid[y][x+1].setState(new Working());
        }
        if(x-1>=0 && grid[y][x-1].getState() instanceof Incomplete){
            frontier.add(new Coordinates(y,x-1));
            grid[y][x-1].setState(new Working());
        }
        if(y+1<n && grid[y+1][x].getState() instanceof Incomplete){
            frontier.add(new Coordinates(y+1,x));
            grid[y+1][x].setState(new Working());
        }
        if(y-1>=0 && grid[y-1][x].getState() instanceof Incomplete){
            frontier.add(new Coordinates(y-1,x));
            grid[y-1][x].setState(new Working());
        }
    }   
}

