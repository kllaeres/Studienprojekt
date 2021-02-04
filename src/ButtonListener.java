import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {

    /**
     * actionPerformed()
     * ueberpreuft welcher Button geklickt wurde und
     * fuehrt entsprechende Aktion aus
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //if(ServerThread.runningClients > 0) {
            switch (e.getActionCommand()) {
                case "Up":
                    Methods.yMove -= 50;
                    //ServerThread.sendMessageText("Up/.../50");

                    Methods.plotValRe();
                    break;
                case "Down":
                    Methods.yMove += 50;
                    //ServerThread.sendMessageText("Down/.../50");

                    Methods.plotValRe();
                    break;
                case "Left":
                    Methods.xMove -= 50;
                    //ServerThread.sendMessageText("Left/.../50");

                    Methods.plotValRe();
                    break;
                case "Right":
                    Methods.xMove += 50;
                    //ServerThread.sendMessageText("Right/.../50");

                    Methods.plotValRe();
                    break;
                case "Zoom out":
                    Methods.zoomOut(0.2);
                    break;
                case "Zoom in":
                    Methods.zoomIn(0.2);
                    break;
                case "Restart":
                    Methods.restart();
                    break;
                default:
                    break;
            }
            UI.contentPane.requestFocus();
        //}
    }
}
