package MazeGeneratorAndSolver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.TimeUnit;


public class Main extends Application{
    static int testCount = 0;
    static Stage window;
    static MazeGenAlgorithm GenAlgorithm;
    static SearchAlgorithm SolveAlgorithm = new DepthFirstSearch();
    static int screenX = 1000;
    static int screenY = 1000;
    static int mazeSize;
    static Node[][] map;
    static int nodeSize;
    static int wallThickness = 1;
    static int timer;
    static int delay = 30;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Maze Generator and Solver by Ray Tate");
        startScreen();
    }
    
    public void startScreen(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        final Label prompt = new Label("Enter maze size:");
        GridPane.setConstraints(prompt,0,0);
        grid.getChildren().add(prompt);
        
        final TextField fieldSize = new TextField();
        GridPane.setConstraints(fieldSize, 0, 1);
        grid.getChildren().add(fieldSize);
        
        Button Btn = new Button("Submit");
        GridPane.setConstraints(Btn, 0, 2);
        grid.getChildren().add(Btn);
        Btn.setOnAction(e-> {
            try{
                mazeSize = Integer.valueOf(fieldSize.getText());
                enterSpeed();
            }
            catch(NumberFormatException error){
                final Label errorMsg = new Label("Error: enter an integer value.");
                GridPane.setConstraints(errorMsg,0,3);
                grid.getChildren().add(errorMsg);

            }
        });
        
        
        Scene startScreen = new Scene(grid, screenX, screenY);
        window.setScene(startScreen);
        window.show();
    }
    
        public void enterSpeed(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        final Label prompt = new Label("Enter an animation speed (Steps/Frame) must be integer >= 1:");
        GridPane.setConstraints(prompt,0,0);
        grid.getChildren().add(prompt);
        
        final TextField fieldSize = new TextField();
        GridPane.setConstraints(fieldSize, 0, 1);
        grid.getChildren().add(fieldSize);
        
        Button submitBtn = new Button("Submit");
        GridPane.setConstraints(submitBtn, 0, 2);
        grid.getChildren().add(submitBtn);
        submitBtn.setOnAction(e-> {
            try{
                
                timer = Integer.valueOf(fieldSize.getText());
                if(timer >= 1){
                    selectGenAlgorithm();
                }
                else{
                    final Label errorMsg = new Label("Error: enter an integer greater than or equal to 1.");
                    GridPane.setConstraints(errorMsg,0,3);
                    grid.getChildren().add(errorMsg);
                }
            }
            catch(NumberFormatException error){
                final Label errorMsg = new Label("Error: enter an integer value.");
                GridPane.setConstraints(errorMsg,0,3);
                grid.getChildren().add(errorMsg);

            }
        });
        
        
        Scene startScreen = new Scene(grid, screenX, screenY);
        window.setScene(startScreen);
        window.show();
    }
    
    
    public void selectGenAlgorithm(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        
        final Label prompt = new Label("Select maze generation algorithm:");
        GridPane.setConstraints(prompt,0,0);
        grid.getChildren().add(prompt);
        
        Button primsBtn = new Button("Randomized Prim's");
        GridPane.setConstraints(primsBtn, 0, 1);
        grid.getChildren().add(primsBtn);
        primsBtn.setOnAction(e-> {
           GenAlgorithm = new RandomizedPrims(mazeSize);
           selectSolveAlgorithm();
        });
        
        Button KruskralBtn = new Button("Randomized Kruskal's");
        GridPane.setConstraints(KruskralBtn, 0, 2);
        grid.getChildren().add(KruskralBtn);
        KruskralBtn.setOnAction(e-> {
           GenAlgorithm = new RandomizedKruskals(mazeSize);
           selectSolveAlgorithm();
        });
        
        Scene startScreen = new Scene(grid, screenX, screenY);
        window.setScene(startScreen);
    }
    
        public void selectSolveAlgorithm(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setAlignment(Pos.CENTER);
        
        final Label prompt = new Label("Select maze solving algorithm:");
        GridPane.setConstraints(prompt,0,0);
        grid.getChildren().add(prompt);
        
        Button NoSolveBtn = new Button("No Solve");
        GridPane.setConstraints(NoSolveBtn, 0, 1);
        grid.getChildren().add(NoSolveBtn);
        NoSolveBtn.setOnAction(e-> {
           SolveAlgorithm = new NoSolve();
           AnimateGeneration();
        });
        
        Button DFSBtn = new Button("Depth First Search");
        GridPane.setConstraints(DFSBtn, 0, 2);
        grid.getChildren().add(DFSBtn);
        DFSBtn.setOnAction(e-> {
           SolveAlgorithm = new DepthFirstSearch();
           AnimateGeneration();
        });
        
        Button BFSBtn = new Button("Breadth First Search");
        GridPane.setConstraints(BFSBtn, 0, 3);
        grid.getChildren().add(BFSBtn);
        BFSBtn.setOnAction(e-> {
           SolveAlgorithm = new BreadthFirstSearch();
           AnimateGeneration();
        });
        
        Scene startScreen = new Scene(grid, screenX, screenY);
        window.setScene(startScreen);
    }
    
    public void AnimateGeneration(){
        AnimationTimer time = new AnimationTimer(){
            @Override
            public void handle(long now){
                if(GenAlgorithm.getStep() != -1){
                    for(int i=1; i<timer ;i++){
                        GenAlgorithm.NextStep();
                    }
                    try{
                        TimeUnit.MILLISECONDS.sleep(delay);
                    }catch(Exception e){
                        System.out.println("TimeUnit Error");
                    }
                    displayMap(GenAlgorithm.NextStep());
                }
                else{
                    SolveAlgorithm.setGrid(GenAlgorithm.NextStep());
                    AnimateSolve();
                }
            }
        };
        time.start();
    }
    public void AnimateSolve(){
        AnimationTimer time = new AnimationTimer(){
            @Override
            public void handle(long now){
                if(SolveAlgorithm.getStep() != -1){
                    for(int i=1; i<timer ;i++){
                        SolveAlgorithm.NextStep();
                    }
                    displayMap(SolveAlgorithm.NextStep());
                    try{
                        TimeUnit.MILLISECONDS.sleep(delay*2);
                    }catch(Exception e){
                        System.out.println("TimeUnit Error");
                    }
                }
            }
        };
        time.start();
    }
    
    public static void displayMap(Node[][] givenMap){
        map = givenMap;
        nodeSize = lower(screenX,screenY)/map.length;

        Group elements = new Group();
        for(int i = 0; i<map.length; i++){
            for(int j = 0; j<map.length; j++){
                paintNode(map[i][j],j,i,elements);
            }
        }
        
        Scene currentScene = new Scene(elements,screenX,screenY);
        window.setScene(currentScene); 
    }
    
    public static void paintNode(Node n,int x, int y, Group givenGroup){
        int startLocX = x * nodeSize;
        int startLocY = y * nodeSize;
        Rectangle rect = new Rectangle(startLocX, startLocY, nodeSize, nodeSize);
        rect.setFill(n.getState().getColor());
        givenGroup.getChildren().add(rect);
        if(n.getTopWall()){
            rect = new Rectangle(startLocX,startLocY,nodeSize,wallThickness);
            givenGroup.getChildren().add(rect);
        }
        if(n.getBottomWall()){
            rect = new Rectangle(startLocX,startLocY+nodeSize-wallThickness,nodeSize,wallThickness);
            givenGroup.getChildren().add(rect);
        }
        if(n.getLeftWall()){
            rect = new Rectangle(startLocX,startLocY,wallThickness,nodeSize);
            givenGroup.getChildren().add(rect);
        }
        if(n.getRightWall()){
            rect = new Rectangle(startLocX+nodeSize-wallThickness,startLocY,wallThickness,nodeSize);
            givenGroup.getChildren().add(rect);
        }
    }
    
    public static int lower(int x, int y){
        if(x<y)
            return x;
        else
            return y;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}


