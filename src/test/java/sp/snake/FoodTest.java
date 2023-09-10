package sp.snake;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FoodTest {

    @Test
    public void testRandomizeFood() {
        Food food = new Food();
        Point position = food.getPosition();

        assertTrue(position.x >= 0 && position.x < Game.getSWidth() / Game.getUnitSize());
        assertTrue(position.y >= 0 && position.y < Game.getSHeight() / Game.getUnitSize());

        int points = food.getPoints();
        assertTrue(points == 1 || points == 2 || points == 3);

        Color color = food.getColor();
        assertTrue(color == Color.GREEN || color == Color.MAGENTA || color == Color.BLUE);
    }
}
