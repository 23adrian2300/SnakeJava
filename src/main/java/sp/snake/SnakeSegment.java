package sp.snake;

import java.awt.*;

public class SnakeSegment {
    private final Point position;

    public SnakeSegment(int x, int y) {
        position = new Point(x, y);
    }

    public void draw(Graphics g, int unitSize) {
        g.setColor(Color.orange);
        g.fillRect(position.x * unitSize, position.y * unitSize, unitSize, unitSize);
    }

    public Point getPosition() {
        return position;
    }
}
