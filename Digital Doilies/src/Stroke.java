/**
 * This is the class that is responsible for all strokes and drawings on the screen.
 */

import java.awt.*;
import java.util.ArrayList;

public class Stroke {

    private DoilyScreen doily;
    private int brushSize;
    private Color color;
    private ArrayList<Point> points = new ArrayList<>(); //keeps all the strokes
    private boolean reflected;
    private Graphics2D graphics2D;


    public Stroke(DoilyScreen doilyScreen){

        this.doily = doilyScreen;
        this.brushSize = doilyScreen.getBrushSize();
        this.color = doilyScreen.getBrushColor();
        this.reflected = doilyScreen.isReflection();

    }

    /**
     * Getters and setters for the color of the Strokes
     */
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Getters and setters for the color of the Strokes
     */
    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    /**
     * Getters and setters for the coordinates of the Strokes
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public void removePoint(Point point){
        this.points.remove(point);
    }

    /**
     * Getters and setters for the boolean reflection of the Strokes
     */
    public boolean isReflected() {
        return reflected;
    }

    public void setReflected(boolean reflected) {
        this.reflected = reflected;
    }

    /**
     *
     * Class methods
     *
     *
     */

    /**
     * Draws every point in every sector.
     * @param graphics the object being drawn onto
     */
    public void draw(Graphics graphics){

        graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(color);

        for (int i = 0; i < doily.getSectorCount(); i++) {
            graphics2D.setStroke(new BasicStroke(brushSize));
            drawLines();
        }

    }

    /**
     * Draws lines in all sectors using the coordinates of the mouse. If reflected is true, the strokes
     * are reflected in the sector. Every new point is a point from the array points, that has
     * all the points for the drawing. The method iterates through it to find all points
     * and draw them on the screen to create a stroke(line). At the end, we call rotate() so that
     * we draw in the same lines in all other sectors.
     */
    private void drawLines() {

        //Starting and ending points for the strokes
        Point startPoint = null;
        Point endPoint = null;

        if (points.size() == 1){
            startPoint = points.get(0);

            graphics2D.drawRect((int)startPoint.getX(), (int)startPoint.getY(),
                    doily.getBrushSize(), doily.getBrushSize());

            if (reflected){
                reflect(startPoint,endPoint);
            }

        } else{
            for (int i = 0; i < points.size();) {
                startPoint = points.get(i);

                for (Point point : points) {
                    endPoint = point;

                    graphics2D.drawLine((int)startPoint.getX(), (int)startPoint.getY(),
                            (int)endPoint.getX(), (int)endPoint.getY());

                    if (reflected){
                        reflect(startPoint,endPoint);
                    }
                    startPoint = endPoint;
                    i++;
                }
                rotate();
            }
        }
    }

    /**
     * Reflects the line in a sector by changing the X coordinate to be -X.
     * That way the line is drawn on the other side of the sector.
     * @param startPoint starting point of the stroke
     * @param endPoint ending point of the stroke
     */
    private void reflect(Point startPoint, Point endPoint){
        if (points.size() == 1){
            graphics2D.drawRect(-(int) startPoint.getX(), (int) startPoint.getY(),
                    doily.getBrushSize(), doily.getBrushSize());
        } else {
            graphics2D.drawLine(-(int)startPoint.getX(), (int)startPoint.getY(),
                    -(int)endPoint.getX(), (int)endPoint.getY());
        }
    }

    /**
     * Rotates the graphical object by using the sector angles of the doily. That way,
     * each line(stroke) is drawn in each sector.
     */
    private void rotate(){
       graphics2D.rotate(Math.toRadians(doily.getSectorAngle()));
    }

}




