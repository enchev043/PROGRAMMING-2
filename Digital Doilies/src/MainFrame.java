/**
 * This is the main screen that interacts with the user.
 */

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    private static final int DEFAULT_WIDTH = 1500; //default width of the screen
    private static final int DEFAULT_HEIGHT = 750; //default height of the screen
    private static final int MINIMUM_WIDTH = 750;  //minimum width of the screen
    private static final int MINIMUM_HEIGHT = 375; //minimum height of the screen

    public MainFrame(){

        super("Digital Doily");
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        Gallery gallery = new Gallery();
        DoilyScreen doilyScreen = new DoilyScreen();
        ControlPanel controlPanel = new ControlPanel(doilyScreen, gallery);

        JPanel contentPanel = new ContentPanel(this,gallery, controlPanel, doilyScreen);

        this.add(contentPanel);
        this.pack();
    }

}
