package sp.snake;

import java.awt.*;
import java.util.Random;

public class Obstacle {
    private Point position;
    private final Color color;

    public Obstacle() {
        randomizePosition();
        color = Color.BLACK;
    }

    public void randomizePosition() {
        int x = new Random().nextInt(Game.getSWidth() / Game.getUnitSize());
        int y = new Random().nextInt(Game.getSHeight() / Game.getUnitSize());
        position = new Point(x, y);
    }

    public void draw(Graphics g, int unitSize) {
        g.setColor(color);
        g.fillRect(position.x * unitSize, position.y * unitSize, unitSize, unitSize);
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}