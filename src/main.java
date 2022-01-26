import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;




public class main {
    private static Grid grid;
    public static Snake snake;

    private static int width;
    private static int height;

    //GUI Setup:
    private static GuiEnvironment gui;
    private static JFrame frame;
    

    public main() {
    }

    public static void main(String[] args){
        Dimension gridSize = new Dimension(10,10);
        grid = new Grid(gridSize);
        snake = new Snake();

        setupGui();

        
    }

    public static void setupGui(){
        Grid.placeFood();
        Grid.placeHead();
        
        width = 500;
        height = 500;
        frame = new JFrame("Snakey Jakey");
        frame.setSize(width+15,height+38);
        frame.setVisible(true);

        gui = new GuiEnvironment();
        gui.setBackground(new Color(96,203,225));
        gui.setSize(width,height);


        frame.add(gui);

        //Close on exit
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
