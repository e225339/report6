public class Screen {
    private Puyo[][] screen;
    private int currentX = 2, currentY = 0; // 初期位置
    private Puyo currentPuyo;

    public Screen(int width, int height) {
        screen = new Puyo[width][height];
        spawnNewPuyo();
    }

    public void spawnNewPuyo() {
        currentPuyo = new Puyo(currentX, currentY, "red");
    }

    public void update() {
        if (!canMove(currentX, currentY + 1)) {
            screen[currentX][currentY] = currentPuyo;
            clearMatches();
            spawnNewPuyo();
        }
    }

    public void movePuyoLeft() {
        if (canMove(currentX - 1, currentY)) currentX--;
    }

    public void movePuyoRight() {
        if (canMove(currentX + 1, currentY)) currentX++;
    }

    public void movePuyoDown() {
        if (canMove(currentX, currentY + 1)) currentY++;
    }

    public void rotatePuyo() {
        // Simplified for single Puyo
    }

    public boolean canMove(int x, int y) {
        return x >= 0 && x < screen.length && y >= 0 && y < screen[0].length && screen[x][y] == null;
    }

    public void clearMatches() {
    
    }

    public boolean isGameOver() {
        return !canMove(currentX, currentY);
    }

    public void printField() {
        for (int y = 0; y < screen[0].length; y++) {
            for (int x = 0; x < screen.length; x++) {
                if (x == currentX && y == currentY) {
                    System.out.print("P ");
                } else if (screen[x][y] != null) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}