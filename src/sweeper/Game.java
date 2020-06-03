package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public Box getBox(Coord coord) {
        return flag.get(coord);
    }

    public void start() {
        bomb.start();
        flag.start();
    }
}
