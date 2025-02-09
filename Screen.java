import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Screen {
    private Puyo[][] screen;
    private int currentX = 2, currentY = 0; // 初期位置
    private Puyo[] currentPuyoPair;

    public Screen(int width, int height) {
        screen = new Puyo[width][height];
        spawnNewPuyo();
    }

    public void spawnNewPuyo() {
        String[] colors = {"red", "blue", "green", "yellow"};
        Random random = new Random();
        String color1 = colors[random.nextInt(colors.length)];
        String color2 = colors[random.nextInt(colors.length)];
        currentPuyoPair = new Puyo[]{
            new Puyo(currentX, currentY, color1),
            new Puyo(currentX, currentY + 1, color2) // 2つ目のぷよは1つ下に
        };
    }

    public void update() {
        if (!canMove(currentX, currentY + 1) || !canMove(currentX, currentY + 2)) {
            for (Puyo puyo : currentPuyoPair) {
                screen[puyo.getX()][puyo.getY()] = puyo;
            }
            clearMatches();
            spawnNewPuyo();
        }
    }

    public void movePuyoLeft() {
        if (canMove(currentX - 1, currentY) && canMove(currentX - 1, currentY + 1)) {
            currentX--;
            for (Puyo puyo : currentPuyoPair) {
                puyo.setX(puyo.getX() - 1);
            }
        }
    }

    public void movePuyoRight() {
        if (canMove(currentX + 1, currentY) && canMove(currentX + 1, currentY + 1)) {
            currentX++;
            for (Puyo puyo : currentPuyoPair) {
                puyo.setX(puyo.getX() + 1);
            }
        }
    }

    public void movePuyoDown() {
        if (canMove(currentX, currentY + 1) && canMove(currentX, currentY + 2)) {
            currentY++;
            for (Puyo puyo : currentPuyoPair) {
                puyo.setY(puyo.getY() + 1);
            }
        }
    }

    public void rotatePuyo() {
        Puyo top = currentPuyoPair[0];
        Puyo bottom = currentPuyoPair[1];
        if (top.getY() < bottom.getY()) {
            top.setY(bottom.getY() + 1);
        } else {
            top.setY(bottom.getY() - 1);
        }
    }

    public boolean canMove(int x, int y) {
        return x >= 0 && x < screen.length && y >= 0 && y < screen[0].length && screen[x][y] == null;
    }

    public void clearMatches() {
        boolean[][] visited = new boolean[screen.length][screen[0].length];
        for (int x = 0; x < screen.length; x++) {
            for (int y = 0; y < screen[0].length; y++) {
                if (screen[x][y] != null && !visited[x][y]) {
                    if (checkAndClear(x, y, screen[x][y].getColor(), visited)) {
                        // スコアを加算する場合はここで処理
                    }
                }
            }
        }
    }
    private boolean checkAndClear(int x, int y, String color, boolean[][] visited) {
        List<int[]> matched = new ArrayList<>();
        dfs(x, y, color, visited, matched);

        if (matched.size() >= 4) {
            for (int[] pos : matched) {
                screen[pos[0]][pos[1]] = null; // 消去
            }
            return true;
        }
        return false;
    }
    
    private void dfs(int x, int y, String color, boolean[][] visited, List<int[]> matched) {
        if (x < 0 || y < 0 || x >= screen.length || y >= screen[0].length || visited[x][y] || screen[x][y] == null || !screen[x][y].getColor().equals(color)) {
            return;
        }
        visited[x][y] = true;
        matched.add(new int[]{x, y});
    
        dfs(x + 1, y, color, visited, matched);
        dfs(x - 1, y, color, visited, matched);
        dfs(x, y + 1, color, visited, matched);
        dfs(x, y - 1, color, visited, matched);
    }
    

    public boolean isGameOver() {
        return !canMove(currentX, currentY);
    }

    public void printField() {
        for (int y = 0; y < screen[0].length; y++) {
            for (int x = 0; x < screen.length; x++) {
                boolean isPuyo = false;
                for (Puyo puyo : currentPuyoPair) {
                    if (puyo.getX() == x && puyo.getY() == y) {
                        System.out.print(puyo.getColor().charAt(0) + " ");
                        isPuyo = true;
                        break;
                    }
                }
                if (!isPuyo) {
                    System.out.print((screen[x][y] != null ? screen[x][y].getColor().charAt(0) : ".") + " ");
                }
            }
            System.out.println();
        }
    }
}