package sweeper;

public class Game {

    private Matrix bombMap;

    public Game(int cols, int rows) {
        Ranges.setSize(new Coord(cols, rows));
    }
    public Box getBox(Coord coord){
        return bombMap.get(coord);
    }

    public  void start(){
        bombMap = new Matrix(Box.BOMB);
    }
}
