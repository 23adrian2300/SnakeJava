package sp.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JPanel implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int UNIT_SIZE = 20;
    private static final int DELAY = 80;
    private SnakeMovement snake;
    private Food food;
    private boolean isGameOver = false;
    private int score = 0;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        startGame();
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public void startGame() {
        snake = new SnakeMovement();
        food = new Food();
        isGameOver = false;
        score = 0;
    }

    public void move() {
        if (!isGameOver) {
            snake.move();
            checkCollision();
        }
    }

    public void checkCollision() {
        if (snake.collidesWithItself() || snake.collidesWithWall()) {
            isGameOver = true;
            return;
        }

        if (snake.getHead().equals(food.getPosition())) {
            snake.extend();
            score += food.getPoints();
            food.randomizeFood();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isGameOver) {
            super.paintComponent(g);
            snake.draw(g, getUnitSize());
            food.draw(g);
            drawScore(g);
        } else {
            gameOver(g);
        }
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Game Over", WIDTH / 4 + 30, HEIGHT / 2);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, WIDTH / 2 - 35, HEIGHT / 2 + 30);
        g.drawString("Press SPACE to restart", WIDTH / 4 + 30, HEIGHT / 2 + 60);
    }

    public static int getSWidth() {
        return WIDTH;
    }

    public static int getSHeight() {
        return HEIGHT;
    }

    public static int getUnitSize() {
        return UNIT_SIZE;
    }

    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (!isGameOver) {
                snake.setDirection(key);
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    startGame();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }


}