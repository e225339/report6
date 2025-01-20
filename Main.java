import java.util.Scanner;

public class Main {
    private static final int WIDTH = 6;
    private static final int HEIGHT = 12;

    public static void main(String[] args) {
        Screen screen = new Screen(WIDTH, HEIGHT);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Puyo Puyo!");
        screen.printField();

        while (true) {
            System.out.println("Enter command (left, right, down, rotate, quit): ");
            String command = scanner.nextLine();

            switch (command) {
                case "left":
                    screen.movePuyoLeft();
                    break;
                case "right":
                    screen.movePuyoRight();
                    break;
                case "down":
                    screen.movePuyoDown();
                    break;
                case "rotate":
                    screen.rotatePuyo();
                    break;
                case "quit":
                    System.out.println("Thanks for playing!");
                    return;
                default:
                    System.out.println("Invalid command.");
                    break;
            }

            screen.update();
            screen.printField();

            if (screen.isGameOver()) {
                System.out.println("Game Over!");
                break;
            }
        }

        scanner.close();
    }
}
