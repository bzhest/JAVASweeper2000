import javax.swing.*;
import java.awt.*;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame {

    private JPanel panel;
    private Game game;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;



    public static void main(String[] args) {
        new JavaSweeper().setVisible(true);
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
        setIconImage(getImage("icon"));
    }

    private void initPanel() {
        panel = new JPanel(){ // while panel creation anonim class is created
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Coord coord: Ranges.getAllCoords()){
                    g.drawImage((Image)game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//after 'x' will be clicked - program will be closed
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);// frame will be open in center
        setResizable(false);
        setVisible(true);
        pack(); //set up min frame size to show all needed components
    }

    private Image getImage(String name){
        String fileName = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }

    private void setImages(){
        for(Box box : Box.values()){
            box.image = getImage(box.name());
        }
    }
}
