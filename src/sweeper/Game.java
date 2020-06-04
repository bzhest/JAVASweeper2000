package sweeper;

public class Game {

    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public Box getBox(Coord coord) {
        if (flag.get(coord) == Box.OPENED) {
            return bomb.get(coord);
        } else {
            return flag.get(coord);
        }
    }

    public void start() {
        bomb.start();
        flag.start();
        gameState = GameState.PLAYED;
    }

    public void pressLeftButton(Coord coord) {
        if (gameOver()) return;
        openBox(coord);
        checkWinner();
    }

    private void checkWinner() {
        if (gameState == GameState.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                gameState = GameState.WINNER;
            }
        }
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED:
                setOpenedToClosedBoxesAroundNumbers(coord);
                return;
            case FLAGED:
                return;
            case CLOSED:
                switch (bomb.get(coord)) {
                    case ZERO:
                        openBoxesAround(coord);
                        return;
                    case BOMB:
                        openBombs(coord);
                        return;
                    default:
                        flag.setOpenedToBox(coord);
                        return;

                }

        }
    }

    private void openBombs(Coord bombed) {
        gameState = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord : Ranges.getAllCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBombBox(coord);
            } else {
                flag.setNoBombToFlagSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord); //open current cell
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
        }
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) return;
        flag.toggleFlaggedBox(coord);
    }

    private boolean gameOver() {
        if (gameState == GameState.PLAYED) {
            return false;
        }
        start();
        return true;
    }

    public void setOpenedToClosedBoxesAroundNumbers(Coord coord) {
        if (bomb.get(coord) != Box.BOMB) {
            if (flag.getCountOfFlaggedBoxesAround(coord) == bomb.get(coord).getNumber()) {
                for (Coord around : Ranges.getCoordsAround(coord)){
                    if(flag.get(around) == Box.CLOSED){
                        openBox(around);
                    }
                }
            }
        }


    }


}
