public class SnakeGameMain {
    public static void main(String[] args) {
        SnakeGameModel model = new SnakeGameModel();
        SnakeGameView view = new SnakeGameView(model, new SnakeGameController(model, null));
    }
}