package sp.snake;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeSegmentTest {

    @Test
    public void testGetPosition() {
        SnakeSegment segment = new SnakeSegment(2, 3);
        Point position = segment.getPosition();
        assertEquals(2, position.x);
        assertEquals(3, position.y);
    }
}