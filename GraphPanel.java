import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Shows the left side of the screen with the polygon
 */
public class GraphPanel extends JPanel implements MouseListener, KeyListener {
    ArrayList<Vertex> vertices;
    Vertex[] triangleVertices;
    static int pointerX,pointerY;

    public GraphPanel() {
        setBackground(Color.white);

        triangleVertices = new Vertex[6];

        addMouseListener(this);
        setFocusable(true);
        addKeyListener(this);

        vertices = new ArrayList<>();

        repaint();

        pointerX = pointerY = -10;
    }

    /**
     * Returns the coordinates of one of the triangles
     * @param upper whether we're doing the first or second triangle
     * @return a coords array used for painting
     */
    public int[][] triangleCoords(boolean upper) {
        int start = upper?3:0;
        int count = 0;

        for (int i =0; i < 3; i++) {
            if (triangleVertices[i+start]!=null) {
                count++;
            }
        }

        int[][] coords = new int[2][count];
        int countedAlready = 0;

        for (int i = 0; countedAlready < count; i++) {
            if (triangleVertices[i+start] != null) {
                coords[0][countedAlready] = triangleVertices[i+start].getX();
                coords[1][countedAlready] = triangleVertices[i+start].getY();

                countedAlready++;
            }
        }

        return coords;
    }

    public void safeIntersect() {
        for (Vertex vertex: triangleVertices) {
            if (vertex == null) {
                Main.intersecting = false;
                Main.erromsg = "Null Vertex attempted. \r\nMake sure there's 6 vertices";

                return;
            }
        }

        Main.intersecting = Predicate.TriTriIntersect(triangleVertices[0], triangleVertices[1],
                triangleVertices[2], triangleVertices[3],
                triangleVertices[4], triangleVertices[5]);
    }

    /**
     * Paints the triangles on screen
     * @param g
     */
    public void paintTriangles(Graphics g) {
        g.setColor(new Color(150,160,250));

        int[][] triangle1 = triangleCoords(false);
        int[][] triangle2 = triangleCoords(true);

        g.fillPolygon(triangle1[0],triangle1[1],triangle1[1].length);
        g.fillPolygon(triangle2[0],triangle2[1],triangle2[1].length);
        g.setColor(Color.BLACK);
        g.drawPolygon(triangle1[0],triangle1[1],triangle1[1].length);
        g.drawPolygon(triangle2[0],triangle2[1],triangle2[1].length);

        int i = 0;

        for (Vertex vertex : triangleVertices) {
            if (i > 5) {
                return;
            }

            if (vertex == null) {
                continue;
            }

            g.setColor(vertex.getColor());
            g.fillOval(vertex.getX()-5,vertex.getY()-5,10,10);
            g.drawString(""+i++, vertex.getX()+5, vertex.getY()+5);
        }
    }

    /**
     * Paints the left side of the screen
     * @param g Graphics object used by JPanel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintTriangles(g);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Determines what to do when mouse is pressed
     * @param e KeyEvent containing info on which mouse button was pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (Main.phase == Main.PhaseType.DRAW) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                int i = 0;

                while (i < 6 && triangleVertices[i] != null) {
                    i++;
                }

                if (i > 5) {
                    return;
                }

                triangleVertices[i] = new Vertex(e.getX(), e.getY());
            }

            if (e.getButton() == MouseEvent.BUTTON3) {
                int closestIndex = 0;
                Vertex closest = triangleVertices[0];
                double dist = Math.hypot(closest.getX() - e.getX(), closest.getY() - e.getY());

                for (int i = 1; i < 6; i++) {
                    Vertex vertex = triangleVertices[i];
                    if (vertex != null && Math.hypot(vertex.getX() - e.getX(), vertex.getY() - e.getY()) < dist) {
                        dist = Math.hypot(vertex.getX() - e.getX(), vertex.getY() - e.getY());
                        closest = vertex;
                        closestIndex = i;
                    }
                }

                triangleVertices[closestIndex] = null;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}