package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
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
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBombs(coord);
            break;
        }
    }

    private void incNumbersAroundBombs(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
        }
    }

    private void fixBombsCount(){
        int maxBombsCount = Ranges.getSize().x * Ranges.getSize().y / 2;
        if(totalBombs > maxBombsCount){
            totalBombs = maxBombsCount;
        }
    }

    public int getTotalBombs() {
        return totalBombs;
    }
}
