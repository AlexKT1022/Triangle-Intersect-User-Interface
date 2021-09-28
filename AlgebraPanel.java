import javax.swing.*;
import java.awt.*;

/**
 * Shows information about the process of the algorithm
 */
public class AlgebraPanel extends JPanel {
    public AlgebraPanel() {
        setBackground(new Color(250,210,250));
    }

    /**
     * Paints the info about the next clip on the right side of the screen
     * @param g Graphics object used by JPanel
     */
    public void paint(Graphics g) {
        super.paintComponent(g);

        if (Main.phase == Main.PhaseType.DRAW) {
            return;
        }

        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("Result from predicate: " +Main.intersecting,20,70);

        if (Main.erromsg.length() > 22) {
            g.drawString(Main.erromsg.substring(0,22),20,130);
            g.drawString(Main.erromsg.substring(22),20,150);
        } else {
            g.drawString(Main.erromsg,20,130);
        }
    }
}
