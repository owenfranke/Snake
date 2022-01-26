import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.io.File;

public class Grid {

    static Random rand = new Random();
    static private Dimension size;
    static private int headX = 4;
    static private int headY = 4;
    static private ArrayList<Integer> queueID;
    static private ArrayList<Point2D> queuePoints;
    static boolean begin = false;

    static int[][] grid;
    // 0 = empty ; 1 = head ; 10... = body ; 9 = food

    public Grid(Dimension gridSize) {
        size = gridSize;
        grid = new int[(int) size.getWidth()][(int) size.getHeight()];
        queuePoints = new ArrayList<Point2D>();
        queueID = new ArrayList<Integer>();
    }

    public static void move(char direction) {

        int xOld = headX;
        int yOld = headY;

        if (direction == 'l') {
            // Move Left
            headY--;
        } else if (direction == 'r') {
            // Move Right
            headY++;
        } else if (direction == 'u') {
            // Move Upwards
            headX--;
        } else if (direction == 'd') {
            // Move Downwards
            headX++;
        }

        // remove old head
        grid[yOld][xOld] = 0;

        // Check for wall:
        if (headX < 0) {
            headX = 9;
        } else if (headX > 9) {
            headX = 0;
        } else if (headY > 9) {
            headY = 0;
        } else if (headY < 0) {
            headY = 9;
        }

        // Check for food:
        if (grid[headY][headX] == 9) {
            playAudio();
            placeFood();
            Snake.setLength(Snake.getLength() + 1);

            // grid[yOld][xOld] = Snake.getLength() + 10; // +10 to make unique id range.
            Point2D point = new Point2D.Double(xOld, yOld);

            // insert body part into queue
            queueID.add(Snake.getLength() + 10);
            queuePoints.add(point);

        }

        // Check for collision:
        if (grid[headY][headX] >= 10) {
            collision();
        }

        // Place new head
        grid[headY][headX] = 1;

        // Adjust the queue points:
        if (queueID.size() > 0) {

            for (int i = queueID.size() - 1; i > 0; i--) {
                queuePoints.set(i, queuePoints.get(i - 1));
            }
            queuePoints.set(0, new Point2D.Double(xOld, yOld));
        }

        // Place values:
        for (int i = 0; i < queueID.size(); i++) {
            int y = (int) queuePoints.get(i).getY();
            int x = (int) queuePoints.get(i).getX();
            int val = queueID.get(i);


            if (i == queueID.size() - 1 && begin) {
                grid[y][x] = 0;
                System.out.println("remove");
                

            } else {
                begin=true;
                grid[y][x] = val;
                System.out.println(val);

            }

        }

    }

    public static void collision() {
        JFrame frame = new JFrame("Collision");
        JOptionPane.showMessageDialog(frame, "Game Over");
        System.exit(0);
    }

    public static void playAudio() {
        try {

            File file = new File("C:\\Users\\owenf\\Desktop\\Snake\\src\\eat.wav");
            AudioInputStream as = AudioSystem.getAudioInputStream(file);
            AudioFormat format = as.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(as);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void placeFood() {

        int x = rand.nextInt((int) size.getWidth());
        int y = rand.nextInt((int) size.getHeight());

        while (grid[y][x] != 0) {
            x = rand.nextInt((int) size.getWidth());
            y = rand.nextInt((int) size.getHeight());
        }
        grid[y][x] = 9;
    }

    public static void placeHead() {

        grid[4][4] = 1;
    }

    public void clear() {
        grid = new int[(int) size.getWidth()][(int) size.getHeight()];
    }

    public static void log(String x) {
        System.out.println(x);
    }

    public static Dimension getSize() {
        return size;
    }

    public static int[][] getGrid() {
        return grid;
    }
}