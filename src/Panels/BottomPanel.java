package src.Panels;

import java.awt.*;
import javax.swing.*;

public class BottomPanel extends JPanel {

	private int previousItr;

	private JButton btnRestart;
	private JButton btnZoomIn;
	private JButton btnLeft;
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnRight;
	private JButton btnZoomOut;

	private JPanel iterationPane;
	private JButton btnChangeITR;
	private JTextField txtAnzItr;

	private final Dimension buttonSize;
	private final Dimension paneSize;

	public BottomPanel(int width, int height){

		Dimension DIMENSION = new Dimension(width, height);

		setBorder(BorderFactory.createLineBorder(Color.WHITE));

		setMinimumSize(DIMENSION);
		setPreferredSize(DIMENSION);
		setMaximumSize(DIMENSION);

		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);

		// Divide width by number of elements
		buttonSize = new Dimension( width / 8, height);

		// for iteration change
		paneSize = new Dimension(buttonSize.width / 2, buttonSize.height);

		initializeButtons();
		initializeIterationPane();
		addElements();
	}

	private void initializeButtons() {
		btnRestart = new JButton("Restart");
		btnZoomIn = new JButton("Zoom In");
		btnLeft = new JButton("Left");
		btnUp = new JButton("Up");
		btnDown = new JButton("Down");
		btnRight = new JButton("Right");
		btnZoomOut = new JButton("Zoom Out");
		btnChangeITR = new JButton("<html>ITR<br>Change</html>");
		btnChangeITR.setFont(new Font("Dialog", Font.BOLD, 10));
		
		btnRestart.setMinimumSize(buttonSize);
		btnZoomIn.setMinimumSize(buttonSize);
		btnLeft.setMinimumSize(buttonSize);
		btnUp.setMinimumSize(buttonSize);
		btnDown.setMinimumSize(buttonSize);
		btnRight.setMinimumSize(buttonSize);
		btnZoomOut.setMinimumSize(buttonSize);
		btnChangeITR.setMinimumSize(paneSize);

		btnRestart.setPreferredSize(buttonSize);
		btnZoomIn.setPreferredSize(buttonSize);
		btnLeft.setPreferredSize(buttonSize);
		btnUp.setPreferredSize(buttonSize);
		btnDown.setPreferredSize(buttonSize);
		btnRight.setPreferredSize(buttonSize);
		btnZoomOut.setPreferredSize(buttonSize);
		btnChangeITR.setPreferredSize(paneSize);

		btnRestart.setMaximumSize(buttonSize);
		btnZoomIn.setMaximumSize(buttonSize);
		btnLeft.setMaximumSize(buttonSize);
		btnUp.setMaximumSize(buttonSize);
		btnDown.setMaximumSize(buttonSize);
		btnRight.setMaximumSize(buttonSize);
		btnZoomOut.setMaximumSize(buttonSize);
		btnChangeITR.setMaximumSize(paneSize);
	}

	private void initializeIterationPane(){
		iterationPane  = new JPanel();
		iterationPane.setLayout(new BoxLayout(iterationPane, BoxLayout.PAGE_AXIS));
		iterationPane.setMinimumSize(paneSize);
		iterationPane.setPreferredSize(paneSize);
		iterationPane.setMaximumSize(paneSize);

		JLabel lblAnzItr = new JLabel("Iterations:");
		iterationPane.add(lblAnzItr);

		txtAnzItr = new JTextField("50");
		previousItr = 50;
		iterationPane.add(txtAnzItr);
	}

	private void addElements() {
		add(btnRestart);
		add(btnZoomIn);
		add(btnLeft);
		add(btnUp);
		add(btnDown);
		add(btnRight);
		add(btnZoomOut);

		add(iterationPane);
		add(btnChangeITR);
	}

	public int getAnzItr(){
		int itr = previousItr;
		try{
			itr = Integer.parseInt(txtAnzItr.getText());
			previousItr = itr;
		}catch(NumberFormatException e){
			JOptionPane.showOptionDialog(null, "Wrong input for number of iterations", "ERROR",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.ERROR_MESSAGE, null,
					new String[]{"OK"}, null);
		}
		return itr;
	}

	public void setTextAnzItr(int itr){
		txtAnzItr.setText("" + itr);
		previousItr = itr;
	}
}
