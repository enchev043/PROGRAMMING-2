/**
 * This is the Content panel that contains all other panels. They are
 * displayed on the MainFrame class.
 */

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.YELLOW; //background color for the whole content panel

    public ContentPanel(JFrame frame,Gallery gallery, ControlPanel controlPanel, DoilyScreen doilyScreen){

        this.setLayout(new BorderLayout());
        this.setBackground(BACKGROUND_COLOR);

        JPanel tools = new JPanel(new FlowLayout()); //The panel with all buttons
        tools.setBackground(BACKGROUND_COLOR);
        //sets the dynamic size of this panel using the size of the main frame
        tools.setPreferredSize(new Dimension(frame.getWidth()/3, frame.getHeight()));

        gallery.setBackground(BACKGROUND_COLOR);
        //sets the dynamic size of this panel using the size of the main frame
        gallery.setPreferredSize(new Dimension(frame.getWidth()/3,frame.getHeight()));
        gallery.setVisible(true);

        //sets the dynamic size of this panel using the size of the main frame
        doilyScreen.setPreferredSize(new Dimension(frame.getWidth()/2,frame.getHeight()));

        //sets the dynamic size of this panel using the size of the main frame
        controlPanel.setPreferredSize(new Dimension(frame.getWidth()/3, frame.getHeight()));

        tools.add(new JLabel("Controls"));
        tools.add(controlPanel);

        this.add(gallery, BorderLayout.WEST);
        this.add(tools, BorderLayout.EAST);
        this.add(doilyScreen, BorderLayout.CENTER);

    }

}
