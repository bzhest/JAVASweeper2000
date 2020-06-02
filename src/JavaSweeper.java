import javax.swing.*;
import java.awt.*;
import sweeper.Box;

public class JavaSweeper extends JFrame {

    private JPanel panel;
    private final int CALS = 15;
    private final int ROWS = 1;
    private final int IMAGE_SIZE = 50;



    public static void main(String[] args) {
        new JavaSweeper().setVisible(true);
    }

    private JavaSweeper() {
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel(){ // while panel creation anonim class is created
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for(Box box: Box.values()){
                    g.drawImage((Image)box.image, box.ordinal() * IMAGE_SIZE, 0, this);

                }
            }
        };
        panel.setPreferredSize(new Dimension(
                CALS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        pack(); //set up min frame size to show all needed components
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//after 'x' will be clicked - program will be closed
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);// frame will be open in center
        setResizable(false);
        setVisible(true);
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
