/**
 * This is the frame where the doilies are created.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DoilyScreen extends JPanel {

    private static final int MINIMUM_PANEL_SIZE = 200;  //minimum panel size for the doily panel
    private static final Color DEFAULT_SECTORS_COLOR = Color.CYAN;  //the default color of the sectors
    private static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;  //the color of the drawing area
    private static final Color DEFAULT_BRUSH_COLOR = Color.WHITE;   //default color of the brush
    private static final int DEFAULT_SECTOR_COUNT = 4;  //default count of sectors when the program is started
    private static final int DEFAULT_BRUSH_SIZE = 1; //default size of the brush
    private int brushSize = DEFAULT_BRUSH_SIZE;  //corresponds to the size of the brush
    private Color brushColor = DEFAULT_BRUSH_COLOR;   //corresponds to the color of the brush
    private int sectorCount = DEFAULT_SECTOR_COUNT; //sets the default sector count when the program is started
    private BufferedImage drawing = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);  //The doily image that is drawn on the screen
    private double sectorAngle = 360 / getSectorCount();    //Calculates the degree between each sector.
    private boolean sectorsToggle = true;   //boolean for sectors on and off
    private boolean reflectionToggle = true;    //boolean for reflection when drawing on and off
    private Stroke points = new Stroke(this);   //Variable for each stroke
    private ArrayList<Stroke> sectorStrokes = new ArrayList<>();    //Stores all strokes
    private ArrayList<Stroke> lastStrokes = new ArrayList<>();  //Stores deleted strokes



    public DoilyScreen(){
        super();

        this.setMinimumSize(new Dimension(MINIMUM_PANEL_SIZE, MINIMUM_PANEL_SIZE));
        this.setBackground(DEFAULT_BACKGROUND_COLOR);

        /**
         * Adding mouse listeners to track user interactions.
         */
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            /**
             * Adds the a newly created line(stroke) to @ArrayList<Stroke> sectorStrokes
             * and stores all previous strokes. New @points is declared so the user
             * can draw again.
             * @param e event that triggers when the mouse button is released
             */
            @Override
            public void mouseReleased(MouseEvent e) {

                if (!brushColor.equals(null)){
                    sectorStrokes.add(points);
                    points = new Stroke(DoilyScreen.this);
                } else {
                    sectorStrokes.remove(points);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            /**
             * Dragging the mouse adds points to @Stroke points, which are
             * used to display a connected line on the screen. If else statement
             * is for eraser.
             * @param e event that triggers when mouse is dragged
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!brushColor.equals(null)){
                    addPoint(e);
                } else {
                    // System.out.println("I am here");
                    removePoint(e);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    /**
     * Getters and setters for the brush
     */
    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushColor(Color brushColor) {
        this.brushColor = brushColor;

    }

    public Color getBrushColor() {
        return brushColor;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
        this.points.setBrushSize(getBrushSize());
    }

    /**
     * Getters and setters for the drawing area
     */
    public void setPointsColor(Color color){
        this.points.setColor(this.getBrushColor());
    }

    public double getSectorAngle() {
        return sectorAngle;
    }

    public void setSectorAngle(double sectorAngle) {
        this.sectorAngle = sectorAngle;
    }

    public int getSectorCount() {
        return sectorCount;
    }

    public void setSectorCount(int sectorCount) {
        this.sectorCount = sectorCount;
    }

    public boolean isSectorsToggle() {
        return sectorsToggle;
    }

    public void setSectorsToggle(boolean sectorsToggle) {
        this.sectorsToggle = sectorsToggle;
    }

    public boolean isReflection() {
        return reflectionToggle;
    }

    public void setReflectionToggle(boolean reflectionToggle) {
        this.reflectionToggle = reflectionToggle;
        this.points.setReflected(this.reflectionToggle);
    }


    /**
     *
     * Class methods
     *
     *
     */

    public void paint(Graphics g){
        super.paint(g);
    }

    /**
     * Creates the coordinates for the stroke and creates a new Point.
     * The coordinates are generated by using the coordinates of the mouse
     * when a MouseEvent is triggered. The height of is subtracted from Y coordinate of
     * the position of the mouse and is divided by 2 to be centered and in that way
     * the user draws where it is intended. The width is subtracted
     * from the X coordinate of the mouse and divided by 2 to be centered on the doilyScreen and in that way
     * the user draws where it is intended. Afterwards, pointY and pointX are added as
     * coordinates of a point in @points.
     * @param e MouseEvent that calls the method when triggered
     */
    public void addPoint(MouseEvent e) {
        int pointX; //X coordinate
        int pointY; //Y coordinate

        pointX = e.getX() - getWidth() / 2;
        pointY = e.getY() - getHeight() / 2;
        points.addPoint(new Point(pointX, pointY));

        repaint();
    }

    /**
     * Same as the method from above, but does the opposite. Tries to delete the lines under the
     * mouse cursor. Used for the eraser.
     * @param e MouseEvent that calls the method when triggered
     */
    public void removePoint(MouseEvent e){
        int pointX; //X coordinate
        int pointY; //Y coordinate

        pointX = e.getX() - getWidth() / 2;
        pointY = e.getY() - getHeight() / 2;
        points.removePoint(new Point(pointX, pointY));

        repaint();
    }

    /**
     * Paints the doilyScreen on the screen.
     * @param graphics the painted object
     */
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.translate(this.getWidth()/2, this.getHeight()/2); //To center in the window

        /**
         * Draws sector lines if @sectorsToggle is true
         */
        if (sectorsToggle) {
            for (int i = 0; i < sectorCount; i++) {
                graphics2D.setStroke(new BasicStroke(1));
                graphics2D.setColor(DEFAULT_SECTORS_COLOR);
                graphics2D.drawLine(0, 0, 0, 360);
                graphics2D.rotate(Math.toRadians(sectorAngle));
            }
        }

        graphics2D.setStroke(new BasicStroke(brushSize));
        graphics2D.setColor(DEFAULT_BRUSH_COLOR);

        /**
         * Draws lines from @sectorStrokes on the screen by calling
         * the draw() method from @Stroke class.
         */
        for (Stroke stroke:sectorStrokes) {
            stroke.draw(graphics2D);
        }
        points.draw(graphics2D);
    }

    private int deletedStrokes; //stores how many things were deleted for the undo and redo methods

    /**
     * Clears the screen of all strokes by emptying sectorStrokes and
     * adding all strokes to lastStrokes so the action can be
     * undo-ed.
     */
    public void clearStrokes(){
        if(!sectorStrokes.isEmpty()){
            for (int i = 0; i < sectorStrokes.size(); i++) {
                lastStrokes.add(sectorStrokes.get(i));
            }
            repaint();
        }
        deletedStrokes = sectorStrokes.size();
        sectorStrokes.clear();
    }

    /**
     * Undoes the last action by removing an elements from
     * sectorStrokes and adds a new element to lastStrokes so that
     * this can be redo-ed.
     */
    public void undoPrevious(){
        if (!sectorStrokes.isEmpty()){
            lastStrokes.add(sectorStrokes.get(sectorStrokes.size()-1));
            sectorStrokes.remove(sectorStrokes.size()-1);
        } else {
            for (int i = 0; i < deletedStrokes; i++) {
                sectorStrokes.add(lastStrokes.get((lastStrokes.size()-1)-i));
            }

            paintComponent(drawing.getGraphics());
        }
    }

    /**
     * Replicate the last undo-ed action by the user by using the lines
     * stored in lastStrokes.
     */
    public void redoPrevious(){
        if(!lastStrokes.isEmpty()){
            sectorStrokes.add(lastStrokes.get(lastStrokes.size()-1));
            lastStrokes.remove(lastStrokes.size()-1);
        }

    }

    /**
     * This method captures the screen and saves it as an image. It captures the current image on the screen
     * and then uses it to make a new image, which is resized, using the passed width and height parameters.
     * Then the image is drawn.
     * @param panel this is where the image is
     * @param width of the resized image
     * @param height of the resized image
     * @return new resized image of the current image on the screen
     */
    public BufferedImage save(JPanel panel, int width, int height){
        BufferedImage currentImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        panel.paint(currentImage.getGraphics());
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        graphics2D.drawImage(currentImage,0,0,width,height,null);
        graphics2D.dispose();

        return resizedImage;
    }

}
