package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    public Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void placeBomb() {
        Coord coord = Ranges.getRandomCoord();
        bombMap.set(coord, Box.BOMB);
        for(Coord around : Ranges.getCoordsAround(coord)){
            bombMap.set(around, Box.NUM1);
        }
    }

    public int getTotalBombs() {
        return this.totalBombs;
    }
}