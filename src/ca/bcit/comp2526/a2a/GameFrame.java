package ca.bcit.comp2526.a2a;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/**
 * A JFrame containing a World filled with Cells.
 * Clicking the mouse advances the World one turn
 * and repaints the frame.
 *
 * @author BCIT COMP 2526
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public class GameFrame extends JFrame {
    /* The World displayed in the GameFrame. */
    private final World world;

    /**
     * Creates a GameFrame.
     *
     * @param w the World in the GameFrame
     */
    public GameFrame(final World w) {
        world = w;
    }

    /**
     * Initializes the GameFrame by filling it with
     * Cells from the World.
     */
    public void init() {
        setTitle("Assignment 2a");
        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
                world.getCellAt(row, col).init();
            }
        }

        addMouseListener(new MouseAdapter() {
            /**
             * Advances the GameFrame one turn when the mouse is clicked.
             *
             * @param e the MouseEvent that occurred
             */
            public void mouseClicked(final MouseEvent e) {
                takeTurn();
            }
        });
    }

    /**
     * Advances the World one turn and repaints
     * the GameFrame.
     */
    public void takeTurn() {
        world.takeTurn();
        repaint();
    }
}
