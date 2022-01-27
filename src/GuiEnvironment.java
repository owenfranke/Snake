import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class GuiEnvironment extends JPanel implements KeyListener {

    char direction = 'r'; // default direction
    int width;
    int height;
    Color snakeCol = new Color(51,155,44);
    Color bodyCol = new Color(93, 176, 115);


    public GuiEnvironment() {
        this.addKeyListener(this);
        refresher(); //Start refreshing
        width = Grid.getSize().width;
        height = Grid.getSize().height;
    }

    public void refresher(){
        TimerTask refresh = new TimerTask() {

            @Override
            public void run() {
                Grid.move(direction);
                repaint();
                //logGrid(Grid.getGrid());
                
            }
            
        };

        Timer timer = new Timer();
        timer.schedule(refresh,0, 250L);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setFocusable(true);
        this.requestFocus();
        //Paint food and snake:
        int length = Grid.getGrid().length;
        for (int i = 0;i<length;i++){
            for (int j = 0;j<length;j++){
                if (Grid.getGrid()[j][i]==9){
                    g.setColor(Color.RED);
                    g.fillRoundRect(j*50, i*(50), 50 , 50,25,25);
                    g.setColor(snakeCol);
                    g.fillRoundRect(j*50, i*(50), 20 , 20,25,25);

                } else if(Grid.getGrid()[j][i]==1){
                    g.setColor(snakeCol);
                    g.fillOval(j*50, i*(50), 50 , 50);
                } else if(Grid.getGrid()[j][i]==2){
                    g.setColor(Color.WHITE);
                    g.fillOval(j*50, i*(50), 50 , 50);
                } else if(Grid.getGrid()[j][i]>=10){
                    g.setColor(bodyCol);
                    g.fillOval(j*50, i*(50), 50 , 50);
                } 
            }
        }
        
    }

    // Player Controller
    @Override
    public void keyPressed(KeyEvent e) {
        // Player Movement:
        if (e.getKeyChar() == 'a' && direction!='r') {
            direction = 'l';

            //Grid.move(direction);
        } else if (e.getKeyChar() == 'd'  && direction!='l') {
            direction = 'r';

            //Grid.move(direction);
        } else if (e.getKeyChar() == 'w' && direction!='d') {
            direction = 'u';

            //Grid.move(direction);
        } else if (e.getKeyChar() == 's'  && direction!='u') {
            direction = 'd';

            //Grid.move(direction);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void logGrid(int[][] x){

        for (int i = 0;i<x.length;i++){
            for (int j = 0;j<x.length;j++){
                System.out.print(x[j][i]);
            }
            System.out.println();

        }
        
    }
}
