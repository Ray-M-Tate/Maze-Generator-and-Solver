package MazeGeneratorAndSolver;

import java.util.ArrayList;
import java.util.Random;

public class BreadthFirstSearch extends SearchAlgorithm{
    ArrayList<BFSPreviousNode> frontier = new ArrayList<>();
    @Override
    public Node[][] NextStep(){
        Random r = new Random();
        int frontierSize;
        if(step == 0){
            grid[grid.length-1][0].setState(new Working());
            frontier.add(new BFSPreviousNode(grid[grid.length-1][0],null));
            step++;
        }
        else if(step == -1){
            return grid;
        }
        else if(step > 0){
            frontierSize = frontier.size();
            for(int i = 0; i<frontierSize;i++){
                if(!frontier.get(0).getCurrent().getTopWall() && grid[frontier.get(0).getCurrent().getY()-1][frontier.get(0).getCurrent().getX()].getState()instanceof Done){
                    frontier.add(new BFSPreviousNode(grid[frontier.get(0).getCurrent().getY()-1][frontier.get(0).getCurrent().getX()],frontier.get(0)));
                    grid[frontier.get(0).getCurrent().getY()-1][frontier.get(0).getCurrent().getX()].setState(new Working());
                    CheckDone();
                }
                if(!frontier.get(0).getCurrent().getBottomWall() && grid[frontier.get(0).getCurrent().getY()+1][frontier.get(0).getCurrent().getX()].getState()instanceof Done){
                    frontier.add(new BFSPreviousNode(grid[frontier.get(0).getCurrent().getY()+1][frontier.get(0).getCurrent().getX()],frontier.get(0)));
                    grid[frontier.get(0).getCurrent().getY()+1][frontier.get(0).getCurrent().getX()].setState(new Working());
                    CheckDone();
                }
                if(!frontier.get(0).getCurrent().getLeftWall() && grid[frontier.get(0).getCurrent().getY()][frontier.get(0).getCurrent().getX()-1].getState()instanceof Done){
                    frontier.add(new BFSPreviousNode(grid[frontier.get(0).getCurrent().getY()][frontier.get(0).getCurrent().getX()-1],frontier.get(0)));
                    grid[frontier.get(0).getCurrent().getY()][frontier.get(0).getCurrent().getX()-1].setState(new Working());
                    CheckDone();
                }
                if(!frontier.get(0).getCurrent().getRightWall() && grid[frontier.get(0).getCurrent().getY()][frontier.get(0).getCurrent().getX()+1].getState()instanceof Done){
                    frontier.add(new BFSPreviousNode(grid[frontier.get(0).getCurrent().getY()][frontier.get(0).getCurrent().getX()+1],frontier.get(0)));
                    grid[frontier.get(0).getCurrent().getY()][frontier.get(0).getCurrent().getX()+1].setState(new Working());
                    CheckDone();
                }
                frontier.remove(0);
            }
        }
        return grid;
    }
    public void CheckDone(){
        BFSPreviousNode reference;
        if(grid[0][grid.length-1].getState() instanceof Working){
            step = -1;
            reference = frontier.get(frontier.size()-1);
            while (reference.getPrevious() != null){
                reference.getCurrent().setState(new Path());
                reference = reference.getPrevious();
            }
            reference.getCurrent().setState(new Path());
        }
    }
}
