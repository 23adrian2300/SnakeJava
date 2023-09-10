package sp.snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testStartGame() {
        game.startGame();
        assertFalse(game.isGameOver());
        assertEquals(0, game.getScore());
        assertNotNull(game.getSnake());
        assertNotNull(game.getFood());
        assertNotNull(game.getObstacles());
        assertTrue(game.getObstacles().size() > 0);
    }

    @Test
    public void testMove() {
        game.startGame();
        int initialX = game.getSnake().getHead().x;
        int initialY = game.getSnake().getHead().y;

        game.move();

        int newX = game.getSnake().getHead().x;
        int newY = game.getSnake().getHead().y;

        assertNotEquals(initialX, newX);
        assertEquals(initialY, newY);
    }


    @Test
    public void testCollisionWithObstacle() {
        game.startGame();
        SnakeMovement snake = game.getSnake();
        Obstacle obstacle = game.getObstacles().get(0);
        snake.setDirection(KeyEvent.VK_RIGHT);
        snake.move();
        snake.setDirection(KeyEvent.VK_DOWN);
        snake.move();

        obstacle.setPosition(snake.getHead());

        assertTrue(game.collidesWithObstacle());
    }

    @Test
    public void testHighScore() {
        game.setHighScore(100);
        game.saveHighScore();
        game.loadHighScore();
        assertEquals(100, game.getHighScore());
    }
}
