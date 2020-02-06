/**
 * This frame is the gallery where drawings and images can be saved and deleted.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gallery extends JPanel {

    private ArrayList<JLabel> gallery = new ArrayList<>(); //keeps the images

    public Gallery(){
        super();
        this.setVisible(true);
        this.setLayout(new GridLayout(4,3));
    }

    /**
     *
     * Class methods
     *
     *
     */

    /**
     * Deletes an image from the gallery. If there is no image, error screen pops up. Otherwise, the users sets
     * which image he wants to delete. If there is such image, the image is deleted and the gallery is repainted.
     * If there is no such image, error pops up.
     */
    public void deleteImage(){

        if (gallery.isEmpty()){
            JOptionPane.showMessageDialog(null, "Gallery is empty!","Empty Gallery",JOptionPane.ERROR_MESSAGE);
        } else {

            int picPosition = Integer.parseInt(JOptionPane.showInputDialog("Select which image you want to delete."));

            if (picPosition-1 < gallery.size()){
                if (picPosition == 1 && gallery.size() == 1){
                    this.removeAll();
                    gallery.clear();
                    repaint();
                } else {
                    gallery.remove(picPosition-1);
                    this.remove(picPosition-1);
                    repaint();
                }
            }else {
                JOptionPane.showMessageDialog(null, "There is no such " +
                        "image. Please select another one.", "Missing image in Gallery", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Saves images to the gallery. The image is added to the gallery array and also
     * to the panel so it is visible and controllable.
     * @param image from the screen
     */
    public void saveImage(JLabel image){
        if (gallery.size() < 12){
            gallery.add(image);
            this.add(image);
        } else {
            JOptionPane.showMessageDialog(null, "The gallery is full. Delete a pic " +
                            "to add a new one.", "Gallery Full", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Empties the whole gallery from all saves. Also clears the array.
     */
    public void clearGallery(){
        this.removeAll();
        gallery.clear();
        repaint();
    }


}
