public class Screen {
    private Puyo[][] screen;

    public Screen(int width, int height){
        screen = new Puyo[width][height];
    }

    public boolean canPlace(int x, int y){
        return screen[x][y] == null;
    }

    public void placePuyo(Puyo puyo, int x, int y) {
        screen[x][y] = puyo;
    }
}