import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener{
    private static final int WIDTH = 6;
    private static final int HEIGHT = 12;
    private Screen screen;

    public Main() {
        screen = new Screen(WIDTH, HEIGHT);
        setTitle("Puyo Puyo");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);

        System.out.println("Welcome to Puyo Puyo!");
        screen.printField();
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);

        while (true) {
            try {
                Thread.sleep(500); // 自動的にぷよを落とす
                game.screen.movePuyoDown();
                game.screen.update();
                game.screen.printField();

                if (game.screen.isGameOver()) {
                    System.out.println("Game Over!");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                screen.movePuyoLeft();
                break;
            case KeyEvent.VK_RIGHT:
                screen.movePuyoRight();
                break;
            case KeyEvent.VK_DOWN:
                screen.movePuyoDown();
                break;
            case KeyEvent.VK_UP:
                screen.rotatePuyo();
                break;
            case KeyEvent.VK_Q:
                System.out.println("Thanks for playing!");
                System.exit(0);
        }
        screen.printField();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}