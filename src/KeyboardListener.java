import javax.swing.*;
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
        if(UI.contentPane.hasFocus()) {
            switch(e.getKeyCode()){
                case KeyEvent.VK_MINUS:
                    MethodsUI.anzZoomsOut++;
                    if(MethodsUI.anzZoomsIn > -9 || MethodsUI.ITR < 100){
                        MethodsUI.anzZoomsIn--;
                    }else{
                        MethodsUI.anzZoomsIn = 0;
                    }
                    MethodsUI.zoomOut(0.2);
                    break;
                case KeyEvent.VK_PLUS:
                    MethodsUI.anzZoomsIn++;
                    if(MethodsUI.anzZoomsOut > -9){
                        MethodsUI.anzZoomsOut--;
                    }else{
                        MethodsUI.anzZoomsOut = 0;
                    }
                    MethodsUI.zoomIn(0.2);
                    break;
                case KeyEvent.VK_LEFT:
                    MethodsUI.xMove -= 50;
                    /*ServerThread.sendMessageText("Left/.../50");
                    ServerThreadWebSocket.sendMessageText("Left/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case KeyEvent.VK_RIGHT:
                    MethodsUI.xMove += 50;
                    /*ServerThread.sendMessageText("Right/.../50");
                    ServerThreadWebSocket.sendMessageText("Right/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case KeyEvent.VK_UP:
                    MethodsUI.yMove -= 50;
                    /*ServerThread.sendMessageText("Up/.../50");
                    ServerThreadWebSocket.sendMessageText("Up/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case KeyEvent.VK_DOWN:
                    MethodsUI.yMove += 50;
                    /*ServerThread.sendMessageText("Down/.../50");
                    ServerThreadWebSocket.sendMessageText("Down/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case KeyEvent.VK_ESCAPE:
                    MethodsUI.restart();
                    break;
                default:
                    break;
            }
        }else{
            if(e.getKeyCode() == KeyEvent.VK_ENTER && UI.txtAnzItr.hasFocus()){
                try {
                    int itr = Integer.parseInt(UI.txtAnzItr.getText());
                    MethodsUI.ITR = itr;
                    //ServerThread.sendMessageText("Itr/.../" + itr);
                    //ServerThreadWebSocket.sendMessageText("Itr/.../" + itr);//*/

                    UI.contentPane.requestFocus();

                    MethodsUI.plotValRe();
                }catch(Exception ex){
                    JOptionPane.showOptionDialog(null, "Wrong input for number of iterations", "ERROR",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE, null,
                            new String[]{"OK"}, null);
                }
            }
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
