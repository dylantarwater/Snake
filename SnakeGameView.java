import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class SnakeGameView extends JFrame {
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 20;
    private SnakeGameModel model;
    private SnakeGameController controller;
    private Timer timer;

    public SnakeGameView(SnakeGameModel model, SnakeGameController controller) {
        this.model = model;
        this.controller = controller;
        initializeUI();
        initializeTimer();
    }

    private void initializeUI() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        add(new SnakeGamePanel(model), BorderLayout.CENTER);
        addKeyListener(controller);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeTimer() {
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.update();
                repaint();
            }
        });
        timer.start();
    }

    private class SnakeGamePanel extends JPanel {
        private static final int CELL_SIZE = 20;

        public SnakeGamePanel(SnakeGameModel model) {
            setPreferredSize(new Dimension(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE));
            setBackground(Color.BLACK);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the snake
            g.setColor(Color.GREEN);
            for (Point point : model.getSnake()) {
                g.fillRect(point.getX() * CELL_SIZE, point.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }

            // Draw the food
            g.setColor(Color.RED);
            Point food = model.getFood();
            g.fillRect(food.getX() * CELL_SIZE, food.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);

            // Draw game over message if the game is over
            if (model.isGameOver()) {
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.drawString("Game Over", getWidth() / 2 - 100, getHeight() / 2);
            }
        }
    }
}

