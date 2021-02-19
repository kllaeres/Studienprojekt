import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MethodsUI {
    /**
     * fuer Mandelbrotmengenberechnung
     */
    static BufferedImage I;
    static int xMove, yMove = 0;
    private static double zoomX = 200;
    private static double zoomY = 200;
    static double cx, cy = 0;

    static int ITR = 500;
    static final int startITR = ITR;
    public static int anzZoomsIn = 0;
    public static int anzZoomsOut = 0;

    /**
     * fuer Rechteck zum hereinzoomen in einen Bereich
     */
    private static boolean rectangle = false;
    private static boolean rightClicked = false;
    private static int widthRect = 0;
    private static int heightRect = 0;
    private static int startX = 0;
    private static int startY = 0;
    private static int endX = 0;
    private static int endY = 0;
    private static int middleRectX = 0;
    private static int middleRectY = 0;
    private static int middleImageX = 0;
    private static int middleImageY = 0;

    /**
     * plotValRe()
     * TODO Testzwecke
     * (am Ende loeschen/auskommentieren)
     */
    public static void plotValRe(){
        plotPoints();
        UI.imgPicture.validate();
        UI.imgPicture.repaint();
    }

    /**
     * plotPoints()
     * berechnet die Mandelbrotmenge
     * (am Ende loeschen/auskommentieren)
     */
    private static void plotPoints(){
        I = new BufferedImage(UI.imgPicture.getWidth(), UI.imgPicture.getHeight(), BufferedImage.TYPE_INT_RGB);
        //System.out.println("anzZoomsIn: " + anzZoomsIn + "; anzZoomsOut: " + anzZoomsOut + "; itr: " + ITR);
        if(anzZoomsIn == 10) {
            ITR = ITR + 50;
            anzZoomsIn = 0;
        }else {
            if(anzZoomsOut == 10) {
                if(ITR > 50) {
                    ITR = Math.max(ITR - 50, 50);
                }
                anzZoomsOut = 0;
            }
        }
        UI.txtAnzItr.setText("" + ITR);
        for (int y = 0; y < I.getHeight(); y++) {
            for (int x = 0; x < I.getWidth(); x++) {
                double zy;
                double zx = zy = 0;
                cx = (x - (I.getWidth() / 2.0) + xMove) / zoomX;
                cy = (y - (I.getHeight() / 2.0) + yMove) / zoomY;
                // jeweils Division mit 2, damit in der Mitte des Bildschirms
                //int itr = KeyboardListener.itr;
                int itr = ITR;
                while (zx * zx + zy * zy < 4 && itr > 0) {
                    double temp = zx * zx - zy * zy + cx;
                    zy = 2 * zx * zy + cy;
                    zx = temp;
                    itr--;
                }
                int colorItr = 20;
                I.setRGB(x, y, itr | (itr << colorItr));
            }
        }
        // zeichnet Kreuz ueber das Bild (Testzwecke)
        middleImageX = I.getWidth() / 2;
        middleImageY = I.getHeight() / 2;
        Graphics2D g = (Graphics2D) I.getGraphics();
        g.setColor(Color.WHITE);
        g.drawLine(middleImageX, 0, middleImageX, I.getHeight());
        g.drawLine(0, middleImageY, I.getWidth(), middleImageY);
    }

    /**
     * imgPanel
     * stellt die Mandelbrotmenge dar
     */
    public static class imgPanel extends JPanel {
        public imgPanel(){
            setBounds(10, 10, UI.paneWidth, UI.imgPictureHeight);
        }

        @Override
        public void paintComponent (Graphics g){
            super.paintComponent(g);
            g.drawImage(I, 0, 0, this);
        }
    }

    /**
     * restart()
     */
    public static void restart(){
        //if(ServerThread.runningClients > 0) {
            anzZoomsOut = anzZoomsIn = 0;
            ITR = 500;
            zoomX = 200;
            zoomY = 200;
            xMove = 0;
            yMove = 0;
            widthRect = 0;
            heightRect = 0;
            startX = 0;
            startY = 0;
            endX = 0;
            endY = 0;
            middleRectX = 0;
            middleRectY = 0;
            middleImageX = 0;
            middleImageY = 0;
            rightClicked = false;

            plotValRe();

            /*ServerThread.sendMessageText("restart/.../");
            ServerThreadWebSocket.sendMessageText("restart/.../");//*/
        //}
    }

    /**
     * zoomIn()
     * @param factor double
     */
    public static void zoomIn(double factor) {
        //if(ServerThread.runningClients > 0) {
            zoomX *= (1 + factor);
            zoomY *= (1 + factor);

            /*int zoom = (int) Math.round(factor/0.2);
            System.out.println("zoom: " + zoom);
            if(zoom >= 10){
                System.out.println("zoom/10: " + (zoom / 10));
                ITR = Math.min(ITR + (50 * (zoom / 10)), 10000);
                anzZoomsIn = anzZoomsIn + (zoom - (10 * (zoom / 10)));
                anzZoomsOut = anzZoomsOut - anzZoomsIn;
            }else{
                anzZoomsIn = anzZoomsIn + zoom;
                if(anzZoomsOut > -9){
                    anzZoomsOut--;
                }else{
                    anzZoomsOut = 0;
                }
                if(anzZoomsIn == 10){
                    ITR = ITR + 50;
                    anzZoomsIn = 0;
                }
            }//*/

            xMove += xMove * factor;
            yMove += yMove * factor;

            plotValRe();//*/

            /*if(!rectangle) {
                ServerThread.sendMessageText("zoomIn/.../" + factor);
                ServerThreadWebSocket.sendMessageText("zoomIn/.../" + factor);
            }else{
                ServerThread.sendMessageText("rectangle/.../" + xMove + "/.../" + yMove + "/.../" + factor);
                ServerThreadWebSocket.sendMessageText("rectangle/.../" + xMove + "/.../" + yMove + "/.../" + factor);
            }//*/
            UI.contentPane.requestFocus();
        //}
    }

    /**
     * zoomOut()
     * @param factor double
     */
    public static void zoomOut(double factor){
        //if(ServerThread.runningClients > 0) {
            zoomX *= (1 - factor);
            zoomY *= (1 - factor);

            xMove -= xMove * factor;
            yMove -= yMove * factor;

            plotValRe();

            /*ServerThread.sendMessageText("zoomOut/.../" + factor);
            ServerThreadWebSocket.sendMessageText("zoomOut/.../" + factor);//*/
            UI.contentPane.requestFocus();
        //}
    }

    /**
     * drawX()
     * @param x x-Koordinate (int)
     * @param y y-Koordinate (int)
     */
    private static void drawX(int x, int y){
        Graphics g = UI.imgPicture.getGraphics();

        g.setColor(Color.WHITE);
        g.drawLine(x, y, x-5, y);
        g.drawLine(x, y, x+5, y);
        g.drawLine(x, y, x, y-5);
        g.drawLine(x, y, x, y+5);
    }

    /**
     * drawRectangle()
     * zeichnet Rechteck
     */
    private static void drawRectangle(){
        // Mitte des Rechtecks
        middleRectX = (startX + endX) / 2;
        middleRectY = (startY + endY) / 2;

        // Mitte des Bildes
        middleImageX = I.getWidth() / 2;
        middleImageY = I.getHeight() / 2;

        Graphics2D g2 = (Graphics2D) UI.imgPicture.getGraphics();
        g2.setColor(Color.WHITE);

        // Ueberpruefung, damit das Rechteck richtig gezeichnet wird
        if (startX > endX) {
            widthRect = startX - endX;
            if (startY > endY) {
                heightRect = startY - endY;
                g2.drawRect(endX, endY, widthRect, heightRect);
            } else {
                heightRect = endY - startY;
                g2.drawRect(endX, startY, widthRect, heightRect);
            }
        } else {
            widthRect = endX - startX;
            if (startY > endY) {
                heightRect = startY - endY;
                g2.drawRect(startX, endY, widthRect, heightRect);
            } else {
                heightRect = endY - startY;
                g2.drawRect(startX, startY, widthRect, heightRect);
            }
        }

        //Mittelpunkt des Rechtecks wird mit einem Kreuz dargestellt
        drawX(middleRectX, middleRectY);
        UI.imgPicture.repaint();
    }

//fuer pictureListener
    /**
     * mouseEventDragged()
     * @param e MouseEvent
     */
    public static void mouseEventDragged(MouseEvent e){
        if(I != null && !rightClicked) {
            endX = e.getX();
            endY = e.getY();

            drawRectangle();
        }
    }

    /**
     * mouseEventPressed()
     * @param e MouseEvent
     */
    public static void mouseEventPressed(MouseEvent e){
        if(I != null && e.getButton() == MouseEvent.BUTTON1) {
            rightClicked = false;
            rectangle = true;
            startX = e.getX();
            startY = e.getY();
        }else{
            if(e.getButton() == MouseEvent.BUTTON3){
                startX = startY = endX = endY = 0;
                rightClicked = true;
            }
        }
    }

    /**
     * mouseEventReleased()
     * @param e MouseEvent
     */
    public static void mouseEventReleased(MouseEvent e){
        if(I != null && e.getButton() == MouseEvent.BUTTON1 && !rightClicked) {
            endX = e.getX();
            endY = e.getY();
            if(startX != endX || startY != endY) {
                xMove += (middleRectX - middleImageX);
                yMove += (middleRectY - middleImageY);
                zoomIn(I.getWidth()/(widthRect*1.0));
                startX = 0;
                startY = 0;
                endX = 0;
                endY = 0;
                widthRect = 0;
                heightRect = 0;
                rectangle = false;
            }
        }
    }

    /**
     * mouseEventClicked()
     * @param e MouseEvent
     */
    public static void mouseEventClicked(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON3){
            startX = startY = endX = endY = 0;
            rightClicked = true;
        }
    }
}
