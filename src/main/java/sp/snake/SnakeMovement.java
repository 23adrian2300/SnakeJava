package sp.snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SnakeMovement {
    private final List<SnakeSegment> snakeBody;
    private Direction direction;

    public SnakeMovement() {
        snakeBody = new ArrayList<>();
        direction = Direction.RIGHT;
        for (int i = 2; i >= 1; i--) {
            snakeBody.add(new SnakeSegment(i, 1));
        }
    }

    public void move() {
        Point newHead = getNextHeadPosition();
        snakeBody.add(0, new SnakeSegment(newHead.x, newHead.y));
        snakeBody.remove(snakeBody.size() - 1);
    }

    public boolean collidesWithItself() {
        Point head = getHead();
        return snakeBody.subList(1, snakeBody.size()).stream()
                .map(SnakeSegment::getPosition)
                .anyMatch(head::equals);
    }

    public boolean collidesWithWall() {
        Point head = getHead();
        int sWidth = Game.getSWidth();
        int sHeight = Game.getSHeight();
        int unitSize = Game.getUnitSize();
        return head.x < 0 || head.x >= (double) sWidth / unitSize ||
                head.y < 0 || head.y >= (double) sHeight / unitSize;
    }

    public void extend() {
        SnakeSegment tail = snakeBody.get(snakeBody.size() - 1);
        snakeBody.add(new SnakeSegment(tail.getPosition().x, tail.getPosition().y));
    }

    public void setDirection(int key) {
        switch (key) {
            case KeyEvent.VK_RIGHT -> {
                if (direction != Direction.LEFT)
                    direction = Direction.RIGHT;
            }
            case KeyEvent.VK_LEFT -> {
                if (direction != Direction.RIGHT)
                    direction = Direction.LEFT;
            }
            case KeyEvent.VK_UP -> {
                if (direction != Direction.DOWN)
                    direction = Direction.UP;
            }
            case KeyEvent.VK_DOWN -> {
                if (direction != Direction.UP)
                    direction = Direction.DOWN;
            }
        }
    }

    public void draw(Graphics g, int unitSize) {
        for (SnakeSegment segment : snakeBody) {
            segment.draw(g, unitSize);
        }
    }

    public Point getHead() {
        return snakeBody.get(0).getPosition();
    }

    private Point getNextHeadPosition() {
        Point head = getHead();
        int x = head.x;
        int y = head.y;
        switch (direction) {
            case RIGHT -> x++;
            case LEFT -> x--;
            case UP -> y--;
            case DOWN -> y++;
        }
        return new Point(x, y);
    }
}
