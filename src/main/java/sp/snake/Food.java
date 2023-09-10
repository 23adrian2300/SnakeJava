package sp.snake;

import java.awt.*;
import java.util.Random;

public class Food {
    private Point position;
    private int points;
    private Color color;

    public Food() {
        randomizeFood();
    }

    public void randomizeFood() {
        int x = new Random().nextInt(Game.getSWidth() / Game.getUnitSize());
        int y = new Random().nextInt(Game.getSHeight() / Game.getUnitSize());
        position = new Point(x, y);

        double randomValue = Math.random();

        if (randomValue < 0.7) {
            points = 1;
            color = Color.GREEN;
        } else if (randomValue < 0.9) {
            points = 2;
            color = Color.MAGENTA;
        } else {
            points = 3;
            color = Color.BLUE;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(position.x * Game.getUnitSize(), position.y * Game.getUnitSize(), Game.getUnitSize(), Game.getUnitSize());
    }

    public Point getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }
    public Color getColor() {
        return color;
    }
}
