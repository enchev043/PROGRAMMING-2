/**
 * This is the functional part of the program. Here is the code for all
 * buttons and elements that the user can use to interact with the
 * doily on the screen.
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel{

    private DoilyScreen doilyScreen; //doily screen that is being changed by the buttons

    public ControlPanel(DoilyScreen doilyScreen, Gallery gallery){

        super();

        this.doilyScreen = doilyScreen;
        this.setLayout(new FlowLayout());
        this.setVisible(true);

        /**
         * Button for clearing the screen.
         */
        JButton clearScreen = new JButton("Clear Screen");
        clearScreen.addActionListener(new ActionListener() {
            /**
             * Clears the whole screen, leaving it blank.
             * @param e action triggered by pressing the button Clear Screen
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                doilyScreen.clearStrokes();
                doilyScreen.repaint();
            }
        });

        /**
         * Button for undo-ing the previous action.
         */
        JButton undoPrevious = new JButton("Undo previous");
        undoPrevious.addActionListener(new ActionListener() {
            /**
             * Undoes the last action of the user.
             * @param e action triggered by pressing the button Undo previous
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                doilyScreen.undoPrevious();
                doilyScreen.repaint();
            }
        });

        /**
         * Button for redo-ing the previous action.
         */
        JButton redoPrevious = new JButton("Redo previous");
        redoPrevious.addActionListener(new ActionListener() {
            /**
             * Redoes the last action of the user.
             * @param e action triggered by pressing hte button Redo previous
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                doilyScreen.redoPrevious();
                doilyScreen.repaint();
            }
        });
        this.add(clearScreen);
        this.add(undoPrevious);
        this.add(redoPrevious);

        /**
         * Button for the color switch panel.
         */
        JButton switchColor = new JButton("Choose Color");
        switchColor.addActionListener(new ActionListener() {
            /**
             * Allows the user to switch colors by displaying a JColorChooser screen, containing colors.
             * @param e action triggered by pressing the button Choose Color
             */
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(doilyScreen, "Pick a color for your brush", doilyScreen.getBackground());
                if (!color.equals(null)){
                    doilyScreen.setBrushColor(color);
                    doilyScreen.setPointsColor(color);
                }
                doilyScreen.repaint();
            }
        });
        this.add(switchColor);

        /**
         * Slider for the size of the brush.
         */
        JSlider brushSizeSlider = new JSlider(1,Math.max(20,doilyScreen.getBrushSize()),doilyScreen.getBrushSize());
        //label that shows the current size of the brush and changes dynamically by changing the size of the brush
        JLabel brushSizeLabel = new JLabel("Brush size (" + brushSizeSlider.getValue() + ")");
        brushSizeLabel.setHorizontalAlignment(JLabel.CENTER);
        brushSizeLabel.setVerticalAlignment(JLabel.CENTER);
        brushSizeSlider.addChangeListener(new ChangeListener() {
            /**
             * Changes the brush size using the value on the JSlider slider.
             * @param e action triggered by changing the value on the slider
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                int BrushSize = brushSizeSlider.getValue();
                doilyScreen.setBrushSize(BrushSize);
                brushSizeLabel.setText("Brush size (" + brushSizeSlider.getValue() + ")");
            }

        });
        this.add(brushSizeLabel);
        this.add(brushSizeSlider);

        /**
         * Button for toggling reflection.
         */
        JButton toggleReflection = new JButton(switchReflection());
        toggleReflection.addActionListener(new ActionListener() {
            /**
             * Turns on and off the reflection option while drawing.
             * @param e action triggered by pressing the button Reflection:On/Off
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                doilyScreen.setReflectionToggle(!doilyScreen.isReflection());
                toggleReflection.setText(switchReflection());
                doilyScreen.repaint();

            }
        });

        /**
         * Button for toggling sector lines.
         */
        JButton toggleSectorLines = new JButton(switchSectorLines());
        toggleSectorLines.addActionListener(new ActionListener() {
            /**
             * Turns on and off the sectors in the middle of the screen.
             * @param e action triggered by pressing the button Sectors:On/Off
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                doilyScreen.setSectorsToggle(!doilyScreen.isSectorsToggle());
                toggleSectorLines.setText(switchSectorLines());
                doilyScreen.repaint();
            }
        });
        this.add(toggleReflection);
        this.add(toggleSectorLines);

        /**
         * Slider for changing number of sectors.
         */
        JSlider numberOfSectorsSlider = new JSlider(1,Math.max(72, doilyScreen.getSectorCount()), doilyScreen.getSectorCount());
        //label that shows the number of sectors on the screen
        JLabel numberOfSectorsLabel = new JLabel("Sector count (" + numberOfSectorsSlider.getValue() + ")");
        numberOfSectorsSlider.addChangeListener(new ChangeListener() {
            /**
             * Changes the count of the sectors on the screen. First, the method takes tha value in the JSlider
             * and sets it as the new sector count. Secondly, it determines the angle between each of sectors
             * so that they can be drawn correctly. The label is changed corresponding to the new sector value.
             * the sectors.
             * @param e action triggered by changing the value on the slider
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                doilyScreen.setSectorCount(numberOfSectorsSlider.getValue());
                doilyScreen.setSectorAngle((double)360 / doilyScreen.getSectorCount());
                numberOfSectorsLabel.setText("Sector count (" + numberOfSectorsSlider.getValue() + ")");
                doilyScreen.repaint();
            }
        });
        this.add(numberOfSectorsLabel);
        this.add(numberOfSectorsSlider);

        /**
         * Implementation for the eraser. Unsuccessful.
         */
        /*
        switchPenEraser = new JButton();
        switchPenEraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doilyScreen.setBrushColor(doilyScreen.getBackground());
                doilyScreen.repaint();
            }
        });
        this.add(switchPenEraser);
        */

        /**
         * Gallery buttons that saves the image.
         */
        JButton saveImage = new JButton("Save");
        saveImage.addActionListener(new ActionListener() {
            /**
             * Saves the image on the screen in the gallery. It calls the method saveImage() from the @Gallery class
             * and then passes as an argument the image, which is take from the doily screen using the save() method
             * defined in @DoilyScreen class. The values correspond to the new size of the saved image.
             * @param e action triggered by pressing the button Save
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                gallery.saveImage(new JLabel(new ImageIcon(doilyScreen.save(doilyScreen, 75, 75))));
                gallery.revalidate();
            }
        });

        /**
         * Gallery button that deletes the image.
         */
        JButton deleteImage = new JButton("Delete");
        deleteImage.addActionListener(new ActionListener() {
            /**
             * Deletes the image from the gallery by calling the deleteImage() method defined in @Gallery class.
             * @param e action triggered by pressing the button Delete
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                gallery.deleteImage();
                gallery.revalidate();
            }
        });

        /**
         * Gallery button that deletes all images in the gallery.
         */
        JButton deleteAll = new JButton("Clear gallery");
        deleteAll.addActionListener(new ActionListener() {
            /**
             * Clears the whole gallery panel by calling the clearGallery() method defined in @Gallery class.
             * @param e action triggered by pressing the button Clear gallery
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                gallery.clearGallery();
                gallery.revalidate();
            }
        });
        this.add(deleteImage);
        this.add(saveImage);
        this.add(deleteAll);


    }

    /**
     *
     * Class methods
     *
     *
     */

    /**
     * Using the value from @doilyScreen for sectors, it dynamically changes the label on
     * the button for turing the sector lines on and off.
     * @return String with the new text for the button
     */
    private String switchSectorLines(){
        boolean sectorsToggle = doilyScreen.isSectorsToggle();
        if (sectorsToggle) {
          return "Sector:On";
        }
        else {
            return "Sector:Off";
        }
    }

    /**
     * Using the value from @doilyScreen for reflection, it dynamically changes the label on
     * the button for turing the reflection on and off.
     * @return String with the new text for the button
     */
    private String switchReflection(){
        boolean reflectionToggle = doilyScreen.isReflection();
        if (reflectionToggle) {
            return "Reflection:On";
        }
        else {
            return "Reflection:Off";
        }
    }

}
