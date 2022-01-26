import java.awt.Color;
import java.awt.Dimension;

public class Snake {

    private static int lengthID = -1;
    private static Dimension position;
    private static Color color = Color.BLACK;

    Snake() {
    }

    public void reset() {
        lengthID = 0;
    }

    public void grow() {
        lengthID++;
    }

    public static int getLength() {
        return lengthID;
    }

    public static void setLength(int x) {
        lengthID=x;
    }

    public void setColor(Color new_Col) {
        color = new_Col;
    }

}
