import java.util.LinkedList;
import java.util.Random;

public class SnakeGameModel {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction currentDirection;
    private LinkedList<Point> snake;
    private Point food;
    private boolean gameOver;

    public SnakeGameModel() {
        // Initialize the model
        currentDirection = Direction.RIGHT;
        snake = new LinkedList<>();
        snake.add(new Point(5, 5));  // Initial position of the snake
        generateFood();
        gameOver = false;
    }

    public void update() {
        // Update the game state (e.g., move the snake, check for collisions)
        if (!gameOver) {
            moveSnake();
            checkCollisions();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public LinkedList<Point> getSnake() {
        return snake;
    }

    public Point getFood() {
        return food;
    }

    public void changeDirection(Direction newDirection) {
        // Change the direction of the snake, ignoring opposite directions
        if (newDirection != null && !newDirection.equals(oppositeDirection(currentDirection))) {
            currentDirection = newDirection;
        }
    }

    private Direction oppositeDirection(Direction direction) {
        // Helper method to get the opposite direction
        switch (direction) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                return null;
        }
    }

    private void moveSnake() {
        // Move the snake in the current direction
        Point head = snake.getFirst();
        Point newHead;

        switch (currentDirection) {
            case UP:
                newHead = new Point(head.getX(), head.getY() - 1);
                break;
            case DOWN:
                newHead = new Point(head.getX(), head.getY() + 1);
                break;
            case LEFT:
                newHead = new Point(head.getX() - 1, head.getY());
                break;
            case RIGHT:
                newHead = new Point(head.getX() + 1, head.getY());
                break;
            default:
                newHead = head;
        }

        snake.addFirst(newHead);

        // Check if the snake ate the food
        if (newHead.equals(food)) {
            generateFood();
        } else {
            // If the snake didn't eat the food, remove the last segment
            snake.removeLast();
        }
    }

    private void checkCollisions() {
        // Check for collisions with walls or itself
        Point head = snake.getFirst();

        // Check for collisions with walls
        if (head.getX() < 0 || head.getX() >= SnakeGameView.BOARD_WIDTH ||
                head.getY() < 0 || head.getY() >= SnakeGameView.BOARD_HEIGHT) {
            gameOver = true;
            return;
        }

        // Check for collisions with itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                gameOver = true;
                return;
            }
        }
    }

    private void generateFood() {
        // Generate a new food at a random position
        Random random = new Random();
        int x = random.nextInt(SnakeGameView.BOARD_WIDTH);
        int y = random.nextInt(SnakeGameView.BOARD_HEIGHT);
        food = new Point(x, y);

        // Regenerate if the food overlaps with the snake
        while (snake.contains(food)) {
            x = random.nextInt(SnakeGameView.BOARD_WIDTH);
            y = random.nextInt(SnakeGameView.BOARD_HEIGHT);
            food = new Point(x, y);
        }
    }
}

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
