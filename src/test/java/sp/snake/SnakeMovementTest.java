package sp.snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeMovementTest {
    private SnakeMovement snake;
    @BeforeEach
    public void setUp() {
        snake = new SnakeMovement();
    }

    @Test
    public void testMoveRight() {
        Point initialHead = snake.getHead();
        snake.setDirection(KeyEvent.VK_RIGHT);
        snake.move();
        Point newHead = snake.getHead();
        assertEquals(initialHead.x + 1, newHead.x);
        assertEquals(initialHead.y, newHead.y);
    }

    @Test
    public void testMoveLeft() {
        Point initialHead = snake.getHead();
        snake.setDirection(KeyEvent.VK_DOWN);
        snake.move();
        snake.setDirection(KeyEvent.VK_LEFT);
        snake.move();
        Point newHead = snake.getHead();
        assertEquals(initialHead.x - 1, newHead.x);
        assertEquals(initialHead.y, newHead.y-1);
    }

    @Test
    public void testMoveUp() {
        Point initialHead = snake.getHead();
        snake.setDirection(KeyEvent.VK_UP);
        snake.move();
        Point newHead = snake.getHead();
        assertEquals(initialHead.x, newHead.x);
        assertEquals(initialHead.y - 1, newHead.y);
    }

    @Test
    public void testMoveDown() {
        Point initialHead = snake.getHead();
        snake.setDirection(KeyEvent.VK_DOWN);
        snake.move();
        Point newHead = snake.getHead();
        assertEquals(initialHead.x, newHead.x);
        assertEquals(initialHead.y + 1, newHead.y);
    }

    @Test
    public void testCollidesWithItself() {
        assertFalse(snake.collidesWithItself());
        for (int i = 0; i < 30; i++) {
            snake.extend();
        }
        snake.setDirection(KeyEvent.VK_DOWN);
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.setDirection(KeyEvent.VK_LEFT);
        snake.move();
        snake.setDirection(KeyEvent.VK_UP);
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        System.out.println(snake.getHead());

        assertTrue(snake.collidesWithItself());
    }

    @Test
    public void testCollidesWithWall() {
        assertFalse(snake.collidesWithWall());
        int sWidth = Game.getSWidth();
        int unitSize = Game.getUnitSize();
        // Przesuń węża na lewo, aby uderzył w lewą ścianę
        for (int i = 0; i < sWidth/unitSize; i++) {
            snake.move();
        }

        assertTrue(snake.collidesWithWall());
    }

    @Test
    public void testExtend() {
        int initialLength = snake.getLength();
        snake.extend();
        assertEquals(initialLength + 1, snake.getLength());
    }
}
