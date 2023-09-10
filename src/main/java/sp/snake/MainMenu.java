package sp.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private final Game game;
    private JButton playButton;
    private JButton quitButton;

    public MainMenu(Game game) {
        this.game = game;

        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Snake", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.GREEN);
        add(titleLabel, BorderLayout.NORTH);



        playButton = new JButton("Play");
        quitButton = new JButton("Quit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.startGame();
                setVisible(false);
                game.requestFocusInWindow();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        playButton.setPreferredSize(new Dimension(220, 100));
        quitButton.setPreferredSize(new Dimension(220, 100));
        playButton.setBackground(Color.BLACK);
        quitButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.GREEN);
        quitButton.setForeground(Color.GREEN);
        buttonPanel.setBackground(Color.BLACK);
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
