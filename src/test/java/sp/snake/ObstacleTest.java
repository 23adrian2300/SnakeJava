package sp.snake;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObstacleTest {

    @Test
    public void testRandomizePosition() {
        Obstacle obstacle = new Obstacle();
        Point position = obstacle.getPosition();

        assertTrue(position.x >= 0 && position.x < Game.getSWidth() / Game.getUnitSize());
        assertTrue(position.y >= 0 && position.y < Game.getSHeight() / Game.getUnitSize());

        Color color = obstacle.getColor();
        assertTrue(color == Color.BLACK);
    }
}
