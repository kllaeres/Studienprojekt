import javax.swing.*;
import java.awt.*;

public class UI extends JFrame{

    /**
     * JPanels
     */
    static JPanel contentPane = new JPanel();
    static JPanel imgPicture;
    JPanel bottomPane = new JPanel();
    JPanel iterationPane = new JPanel();

    /**
     * JLabels
     */
    static JLabel lblAnzClients;
    JLabel lblAnzItr;

    /**
     * JButtons
     */
    static JButton btnZoomIn;
    static JButton btnZoomOut;
    static JButton btnRestart;
    static JButton btnUp;
    static JButton btnDown;
    static JButton btnLeft;
    static JButton btnRight;
    static JButton btnEnd;

    /**
     * TextAreas
     */
    static JTextField txtAnzItr;

    /**
     * screenSize
     */
    private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int width = (int) screenSize.getWidth();
    private static final int height = (int) screenSize.getHeight();

    /**
     * paneSize
     */
    private static final int bottomPaneHeight = (int) Math.round((height / 14.4)); //bei height=720: bottomPaneHeight=50
    static final int imgPictureHeight = (int) Math.round(((UI.height / 1.152))); //bei height=720: imgPictureHeight=625
    static final int paneWidth = width - 20; //bei width=1280: paneWidth=1260

    /**
     * UI()
     */
    UI(){
        setTitle("Mandelbrot_Server_Java");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setIconImage(new ImageIcon("picture/mandelbrot.png").getImage());
        setBounds(0, 0, width, height);
        setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addWindowListener(new WindowListener());

//contentPane
        contentPane.setLayout(null);
        contentPane.setFocusable(true);
        contentPane.addKeyListener(new KeyboardListener());
        setContentPane(contentPane);

//imgPicture
        imgPicture = new MethodsUI.imgPanel();
        imgPicture.addMouseMotionListener(new pictureListener.pictureMouseMotionListener());
        imgPicture.addMouseListener(new pictureListener.pictureMouseListener());
        contentPane.add(imgPicture);

//bottomPane
        FlowLayout flowLayoutBottomPane = new FlowLayout();
        flowLayoutBottomPane.setHgap(10);
        flowLayoutBottomPane.setVgap(10);
        bottomPane.setLayout(flowLayoutBottomPane);
        bottomPane.setBounds(10, imgPictureHeight + 15, paneWidth, bottomPaneHeight);
        contentPane.add(bottomPane);

//btnRestart
        btnRestart = new JButton("Restart");
        btnRestart.addActionListener(new ButtonListener());
        //btnRestart.setEnabled(false);
        bottomPane.add(btnRestart);

//iterationPane
        FlowLayout flowLayoutIterationPane = new FlowLayout();
        flowLayoutIterationPane.setHgap(0);
        iterationPane.setLayout(flowLayoutIterationPane);
        bottomPane.add(iterationPane);

//lblAnzItr
        lblAnzItr = new JLabel("Iterations:");
        iterationPane.add(lblAnzItr);

//txtAnzItr
        txtAnzItr = new JTextField("" + MethodsUI.ITR);
        txtAnzItr.addKeyListener(new KeyboardListener());
        iterationPane.add(txtAnzItr);

//btnZoomIn
        btnZoomIn = new JButton("Zoom in");
        btnZoomIn.addActionListener(new ButtonListener());
        //btnZoomIn.setEnabled(false);
        bottomPane.add(btnZoomIn);

//btnZoomOut
        btnZoomOut = new JButton("Zoom out");
        btnZoomOut.addActionListener(new ButtonListener());
        //btnZoomOut.setEnabled(false);
        bottomPane.add(btnZoomOut);

//btnLeft
        btnLeft = new JButton("Left");
        btnLeft.addActionListener(new ButtonListener());
        //btnLeft.setEnabled(false);
        bottomPane.add(btnLeft);

//btnUp
        btnUp = new JButton("Up");
        btnUp.addActionListener(new ButtonListener());
        //btnUp.setEnabled(false);
        bottomPane.add(btnUp);

//btnDown
        btnDown = new JButton("Down");
        btnDown.addActionListener(new ButtonListener());
        //btnDown.setEnabled(false);
        bottomPane.add(btnDown);

//btnRight
        btnRight = new JButton("Right");
        btnRight.addActionListener(new ButtonListener());
        //btnRight.setEnabled(false);
        bottomPane.add(btnRight);

//lblAnzClients
        lblAnzClients = new JLabel("anzClients: 0");
        bottomPane.add(lblAnzClients);

//btnEnd
        btnEnd = new JButton("End");
        btnEnd.addActionListener(new ButtonListener());
        //bottomPane.add(btnEnd);

        // auskommentieren/loeschen
        MethodsUI.plotValRe();

    }
}