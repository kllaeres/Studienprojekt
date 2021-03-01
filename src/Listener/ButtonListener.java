package Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Server.Server;

public class ButtonListener implements ActionListener {

	private Server server;

	public ButtonListener(Server server) {
		this.server = server;
	}

	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "Up":
			server.moveY(-10);
			break;
		case "Down":
			server.moveY(10);
			break;
		case "Left":
			server.moveX(-10);
			break;
		case "Right":
			server.moveX(10);
			break;
		case "Zoom Out":
			server.zoomOut(0.2);
			break;
		case "Zoom In":
			server.zoomIn(0.2);
			break;
		case "Restart":
			server.defaultImage();
			break;
		default:
			break;
		}
	}
}