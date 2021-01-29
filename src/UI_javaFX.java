import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class UI_javaFX extends Application{
    // in main einsetzen:        UI_javaFX.UI_launch();

    public static Button btnZoomIn;
    public static Button btnZoomOut;
    public static Button btnRestart;
    public static Button btnUp;
    public static Button btnDown;
    public static Button btnLeft;
    public static Button btnRight;
    public static Button btnEnd;

    //Get primary screen bounds
    static Rectangle2D screenBounds;
    public static int width;
    public static int height;

    boolean click = false;

    int xMove, yMove = 0;
    double zoomX = 200;
    double zoomY = 200;
    /*double zx, zy, cx, cy, temp;
    int numItr = 50;
    int colorItr = 20;
    double zoomIncrease = 100;//*/

    int testX = 0;
    int testY = 0;

    //private int processors = Runtime.getRuntime().availableProcessors();

    /**
     * restart()
     */
    private void restart(){
        if(ServerThread.runningClients > 0) {
            zoomX = 200;
            zoomY = 200;
            xMove = 0;
            yMove = 0;
            testX = 0;
            testY = 0;
            click = false;

            /*plotPoints();
            validate();
            repaint();//*/

            ServerThread.sendMessageText("restart/.../");
        }
    }

    /**
     * zoomIn()
     * @param factor double
     */
    private void zoomIn(double factor) {
        if(ServerThread.runningClients > 0) {
            zoomX *= (1 + factor);
            zoomY *= (1 + factor);

            //I = new BufferedImage(UI.imgPicture.getWidth(), UI.imgPicture.getHeight(), BufferedImage.TYPE_INT_RGB);
            /*plotPoints();
            validate();
            repaint();//*/

            ServerThread.sendMessageText("zoomIn/.../" + factor);
            //ServerThread.sendMessageText("zoomIn/.../" + zoomX + "/.../" + zoomY);
        }
    }

    /**
     * zoomOut()
     * @param factor double
     */
    private void zoomOut(double factor){
        if(ServerThread.runningClients > 0) {
            zoomX *= (1 - factor);
            zoomY *= (1 - factor);

            /*plotPoints();
            validate();
            repaint();//*/

            ServerThread.sendMessageText("zoomOut/.../" + factor);
            //ServerThread.sendMessageText("zoomOut/.../" + zoomX + "/.../" + zoomY);
        }
    }

    /**
     * start()
     * @param stage Stage
     */
    @Override
    public void start(Stage stage){
        stage.setMaximized(true);
        stage.setResizable(false);

        screenBounds = Screen.getPrimary().getBounds();
        width = (int) screenBounds.getWidth();
        height = (int) screenBounds.getHeight();
        System.out.println(width + "; " + height);
        System.out.println((int)screenBounds.getWidth() + "; " + (int)screenBounds.getHeight());

//btnRestart
        btnRestart = new Button("Restart");
        btnRestart.setOnAction(e -> restart());
        btnRestart.setTranslateX((int) (width / 128.0));
        btnRestart.setTranslateY((int) (height / 1.1));
        btnRestart.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnZoomIn
        btnZoomIn = new Button("Zoom in");
        btnZoomIn.setOnAction(e -> zoomIn(0.2));
        btnZoomIn.setTranslateX((int) (width / 3.88));
        btnZoomIn.setTranslateY((int) (height / 1.1));
        btnZoomIn.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnLeft
        btnLeft = new Button("Left");
        btnLeft.setOnAction(e -> {
            xMove -= 50;
            ServerThread.sendMessageText("Left/.../50");
        });
        btnLeft.setTranslateX((int) ((width / 2.0) - (int) (width / 8.5)));
        btnLeft.setTranslateY((int) (height / 1.1));
        btnLeft.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnUp
        btnUp = new Button("Up");
        btnUp.setOnAction(e -> {
            yMove -= 50;
            ServerThread.sendMessageText("Up/.../50");
        });
        btnUp.setTranslateX((int) ((width / 2.0) - (int) (width / (8.5*2))));
        btnUp.setTranslateY((int) (height / 1.1));
        btnUp.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnDown
        btnDown = new Button("Down");
        btnDown.setOnAction(e -> {
            yMove += 50;
            ServerThread.sendMessageText("Down/.../50");
        });
        btnDown.setTranslateX((int) (width / 2.0));
        btnDown.setTranslateY((int) (height / 1.1));
        btnDown.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnRight
        btnRight = new Button("Right");
        btnRight.setOnAction(e -> {
            xMove += 50;
            ServerThread.sendMessageText("Right/.../50");
        });
        btnRight.setTranslateX((int) (width / 2.0) + (int) (width / (8.5*2)));
        btnRight.setTranslateY((int) (height / 1.1));
        btnRight.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnZoomOut
        btnZoomOut = new Button("Zoom out");
        btnZoomOut.setOnAction(e -> zoomOut(0.2));
        btnZoomOut.setTranslateX((int) (width / 1.57));
        btnZoomOut.setTranslateY((int) (height / 1.1));
        btnZoomOut.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//btnEnd
        btnEnd = new Button("End");
        btnEnd.setOnAction(e -> System.exit(0));
        btnEnd.setTranslateX(width - (int) (width / 9.6) - 10);
        btnEnd.setTranslateY((int) (height / 1.1));
        btnEnd.setPrefSize((int) (width / (8.5*2)), (int) (height / 25.6));

//Setting the stage
        Group button = new Group(btnRestart, btnZoomIn, btnLeft, btnUp,btnDown, btnRight, btnZoomOut, btnEnd);

        Group root = new Group(button);

        Scene scene = new Scene(root,0,0,Color.WHITESMOKE);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.PLUS) {
                System.out.println("You pressed +");
                zoomIn(0.05);
            }
            if(key.getCode() == KeyCode.MINUS) {
                System.out.println("You pressed -");
                zoomOut(0.05);
            }
        });

        stage.setTitle("Server Mandelbrot");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * launch()
     */
    public static void UI_launch(){
        launch();
    }
}
