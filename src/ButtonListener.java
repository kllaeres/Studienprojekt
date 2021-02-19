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
                    MethodsUI.yMove -= 50;
                    /*ServerThread.sendMessageText("Up/.../50");
                    ServerThreadWebSocket.sendMessageText("Up/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case "Down":
                    MethodsUI.yMove += 50;
                    /*ServerThread.sendMessageText("Down/.../50");
                    ServerThread.sendMessageText("Down/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case "Left":
                    MethodsUI.xMove -= 50;
                    /*ServerThread.sendMessageText("Left/.../50");
                    ServerThread.sendMessageText("Left/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case "Right":
                    MethodsUI.xMove += 50;
                    /*ServerThread.sendMessageText("Right/.../50");
                    ServerThreadWebSocket.sendMessageText("Right/.../50");//*/

                    MethodsUI.plotValRe();
                    break;
                case "Zoom out":
                    MethodsUI.anzZoomsOut++;
                    if(MethodsUI.anzZoomsIn > -9 || MethodsUI.ITR < 100){
                        MethodsUI.anzZoomsIn--;
                    }else{
                        MethodsUI.anzZoomsIn = 0;
                    }
                    MethodsUI.zoomOut(0.2);
                    break;
                case "Zoom in":
                    MethodsUI.anzZoomsIn++;
                    if(MethodsUI.anzZoomsOut > -9){
                        MethodsUI.anzZoomsOut--;
                    }else{
                        MethodsUI.anzZoomsOut = 0;
                    }
                    MethodsUI.zoomIn(0.2);
                    break;
                case "Restart":
                    MethodsUI.restart();
                    break;
                case "End":
                    System.exit(0);
                    break;
                default:
                    break;
            }
            UI.contentPane.requestFocus();
        //}
    }
}
