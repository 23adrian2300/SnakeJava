package sp.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements ActionListener {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int UNIT_SIZE = 20;
    private static final int DELAY = 80;
    private SnakeMovement snake;
    private MainMenu mainMenu;

    private Food food;
    private boolean isGameOver = false;
    private int score = 0;
    private List<Obstacle> obstacles;

    private int highScore = 0;

    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        mainMenu = new MainMenu(this);
        addKeyListener(new MyKeyAdapter());
        loadHighScore();
        startGame();
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    public void startGame() {
        snake = new SnakeMovement();
        food = new Food();
        isGameOver = false;
        score = 0;
        obstacles = new ArrayList<>();
        addObstacles();
    }

    public void move() {
        if (!isGameOver) {
            snake.move();
            checkCollision();
        }
    }

    public void checkCollision() {
        if (snake.collidesWithItself() || snake.collidesWithWall() || collidesWithObstacle()) {
            isGameOver = true;
            return;
        }

        if (snake.getHead().equals(food.getPosition())) {
            snake.extend();
            score += food.getPoints();
            food.randomizeFood();
        }
    }

    public boolean collidesWithObstacle() {
        Point head = snake.getHead();
        return obstacles.stream()
                .anyMatch(obstacle -> obstacle.getPosition().equals(head));
    }

    public void addObstacles() {
        int numObstacles = 5;
        for (int i = 0; i < numObstacles; i++) {
            Obstacle obstacle = new Obstacle();
            obstacles.add(obstacle);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isGameOver) {
            super.paintComponent(g);
            snake.draw(g, getUnitSize());
            food.draw(g);
            drawObstacles(g);
            drawScore(g);
        } else {
            gameOver(g);
        }
    }

    public void drawObstacles(Graphics g) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g, getUnitSize());
        }
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 20);
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        if (score > highScore) {
            highScore = score;
            saveHighScore();
        }
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("Game Over", WIDTH / 4 + 30, HEIGHT / 2-90);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, WIDTH / 2 - 50, HEIGHT / 2 -60);
        g.setColor(Color.GREEN);
        g.drawString("Highscore: " + highScore, WIDTH / 2 - 75, HEIGHT / 2 - 30);
        g.setColor(Color.white);
        g.drawString("Press SPACE to restart", WIDTH / 4 + 30, HEIGHT / 2);
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
    public void loadHighScore() {
        try {
            FileReader fileReader = new FileReader("highscore.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            if (line != null) {
                highScore = Integer.parseInt(line);
            }
            bufferedReader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void saveHighScore() {
        try {
            FileWriter fileWriter = new FileWriter("highscore.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(Integer.toString(highScore));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SnakeMovement getSnake() {
        return snake;
    }

    public void setSnake(SnakeMovement snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
