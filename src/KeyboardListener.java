import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    /**
     * keyReleased()
     * wenn Keyboardtaste losgelassen wird entsprechendes ausgefuehrt
     * @param e KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_MINUS:
                Methods.zoomOut(0.2);
                break;
            case KeyEvent.VK_PLUS:
                Methods.zoomIn(0.2);
                break;
            case KeyEvent.VK_LEFT:
                Methods.xMove -= 50;
                //ServerThread.sendMessageText("Left/.../50");

                Methods.plotValRe();
                break;
            case KeyEvent.VK_RIGHT:
                Methods.xMove += 50;
                //ServerThread.sendMessageText("Right/.../50");

                Methods.plotValRe();
                break;
            case KeyEvent.VK_UP:
                Methods.yMove -= 50;
                //ServerThread.sendMessageText("Up/.../50");

                Methods.plotValRe();
                break;
            case KeyEvent.VK_DOWN:
                Methods.yMove += 50;
                //ServerThread.sendMessageText("Down/.../50");

                Methods.plotValRe();
                break;
            case KeyEvent.VK_ESCAPE:
                Methods.restart();
                break;
            default:
                break;
        }
    }

    /**
     * keyTyped()
     * @IGNORE
     * @param e KeyEvent
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * keyPressed()
     * @IGNORE
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {}
}
