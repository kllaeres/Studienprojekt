package Listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import Panels.MandelbrotPanel;
import Server.Server;

public class MandelbrotMouseListener extends MouseAdapter {

	private Server server;
	private MandelbrotPanel mandelbrotPanel;
	private boolean canceled;

	private int mWidth;
	private int mHeight;

	private int startX;
	private int startY;

	private int middleRectX;
	private int middleRectY;

	private int endX;
	private int endY;

	private double factorX;
	private double factorY;

	private int rectX;
	private int rectY;

	private int rectWidth;
	private int rectHeight;

	private int widthRect;
	private int heightRect;

	public MandelbrotMouseListener(Server server, MandelbrotPanel mandelbrotPanel) {
		this.server = server;
		this.mandelbrotPanel = mandelbrotPanel;
		this.mWidth = mandelbrotPanel.getWidth();
		this.mHeight = mandelbrotPanel.getHeight();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			startX = e.getX();
			startY = e.getY();
			canceled = false;
		}

		if (e.getButton() == MouseEvent.BUTTON3) {
			mandelbrotPanel.setRectangle(0, 0, 0, 0);
			mandelbrotPanel.setRectangleX(0, 0);
			canceled = true;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (!canceled) {
			endX = e.getX();
			endY = e.getY();

			rectX = Math.min(startX, endX);
			rectY = Math.min(startY, endY);

			rectWidth = Math.abs(startX - endX);
			rectHeight = Math.abs(startY - endY);

			// Mitte des Rechtecks
			middleRectX = (startX + endX) / 2;
			middleRectY = (startY + endY) / 2;

			// Ueberpruefung, damit das Rechteck richtig gezeichnet wird
			/*if (startX > endX) {
				widthRect = startX - endX;
				if (startY > endY) {
					heightRect = startY - endY;
					mandelbrotPanel.setRectangle(endX, endY, widthRect, heightRect);
				} else {
					heightRect = endY - startY;
					mandelbrotPanel.setRectangle(endX, startY, widthRect, heightRect);
				}
			} else {
				widthRect = endX - startX;
				if (startY > endY) {
					heightRect = startY - endY;
					mandelbrotPanel.setRectangle(startX, endY, widthRect, heightRect);
				} else {
					heightRect = endY - startY;
					mandelbrotPanel.setRectangle(startX, startY, widthRect, heightRect);
				}
			}//*/

			// Mittelpunkt des Rechtecks wird mit einem Kreuz dargestellt
			mandelbrotPanel.setRectangle(rectX, rectY, rectWidth, rectHeight);
			mandelbrotPanel.setRectangleX(middleRectX, middleRectY);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!canceled) {
			
			mandelbrotPanel.setRectangle(0, 0, 0, 0);
			mandelbrotPanel.setRectangleX(0, 0);

			factorX = (middleRectX - (mWidth / 2.0));
			factorY = (middleRectY - (mHeight / 2.0));

			server.moveX(factorX);
			server.moveY(factorY);

			server.zoomIn(((mWidth / (widthRect * 1.0)) + (mHeight / (heightRect * 1.0))) / 2.0);
		
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		double mouseRotation = e.getPreciseWheelRotation();

		if (mouseRotation < 0)
			server.zoomIn(mouseRotation * -0.2);
		else
			server.zoomOut(mouseRotation * 0.2);

	}
}
