import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeGameController implements KeyListener {
    private SnakeGameModel model;
    private SnakeGameView view;

    public SnakeGameController(SnakeGameModel model, SnakeGameView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key-pressed events to change the direction of the snake
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                model.changeDirection(SnakeGameModel.Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                model.changeDirection(SnakeGameModel.Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                model.changeDirection(SnakeGameModel.Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                model.changeDirection(SnakeGameModel.Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key-typed events if needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key-released events if needed
    }
}
