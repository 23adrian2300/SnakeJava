package sp.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            Game game = new Game();
            MainMenu mainMenu = new MainMenu(game);

            JPanel container = new JPanel();
            CardLayout cardLayout = new CardLayout();
            container.setLayout(cardLayout);
            container.add(mainMenu, "MainMenu");
            container.add(game, "Game");

            mainMenu.getPlayButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(container, "Game");
                    game.startGame();
                    game.requestFocusInWindow();
                }
            });

            mainMenu.getQuitButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            frame.add(container);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - frame.getWidth()) / 3;
            int y = (screenSize.height - frame.getHeight()) / 8;
            frame.setLocation(x, y);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}


