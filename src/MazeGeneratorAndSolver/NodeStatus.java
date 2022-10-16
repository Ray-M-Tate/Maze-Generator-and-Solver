package MazeGeneratorAndSolver;

import javafx.scene.paint.Color;

public abstract class NodeStatus {
    public abstract Color getColor();
    @Override
    public NodeStatus clone() throws CloneNotSupportedException{
        NodeStatus clonedStatus = (NodeStatus)super.clone();
        return clonedStatus;
    }
}
