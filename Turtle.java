import java.awt.*;

public class Turtle {
    private double x, y;        // turtle is at (x, y)
    private double angle;       // facing this many degrees counterclockwise from the x-axis

    // start at (x0, y0), facing a0 degrees counterclockwise from the x-axis
    public Turtle(double x0, double y0, double a0) {
        x = x0;
        y = y0;
        angle = a0;
    }

    // rotate orientation delta degrees counterclockwise
    public void turnLeft(double delta) {
        angle += delta;
    }

    // move forward the given amount, with the pen down
    public void forward(double step) {
        double oldx = this.x;
        double oldy = this.y;
        this.x += step * Math.cos(Math.toRadians(angle));
        this.y += step * Math.sin(Math.toRadians(angle));
        StdDraw.line(oldx, oldy, this.x, this.y);
    }

    // copy to onscreen
    public void show() {
        StdDraw.show();
    }

    // pause t milliseconds
    public void pause(int t) {
        StdDraw.pause(t);
    }
    public void setPenColor(Color color) {
        StdDraw.setPenColor(color);
    }
    public void setPenRadius(double radius) {
        StdDraw.setPenRadius(radius);
    }
    public void setCanvasSize(int width, int height) {
        StdDraw.setCanvasSize(width, height);
    }
    public void setXscale(double min, double max) {
        StdDraw.setXscale(min, max);
    }
    public void setYscale(double min, double max) {
        StdDraw.setYscale(min, max);
    }
}
