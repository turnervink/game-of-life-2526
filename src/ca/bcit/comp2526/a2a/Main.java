package ca.bcit.comp2526.a2a;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Creates a World filled with Cells and
 * simulates life.
 *
 * @author BCIT COMP 2526
 * @author Turner Vink - A01012232
 * @version 0.1
 */
public final class Main {
    private static final Toolkit TOOLKIT;
    private static final int WORLD_DIMENSION = 25;
    private static final float MAX_PERCENT = 100f;
    private static final float SCREEN_PERCENT = 0.80f;


    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Private to prevent instantiation.
     */
    private Main() {
    }

    /**
     * Drives the program.
     *
     * @param argv command line arguments
     */
    public static void main(final String[] argv) {
        final GameFrame frame;
        final World world;

        RandomGenerator.reset();
        world = new World(WORLD_DIMENSION, WORLD_DIMENSION);
        world.init();
        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Positions a GameFrame.
     *
     * @param frame the GameFrame to position
     */
    private static void position(final GameFrame frame) {
        final Dimension size;

        size = calculateScreenArea(SCREEN_PERCENT, SCREEN_PERCENT);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Centers a GameFrame in a window.
     *
     * @param size the size of the GameFrame
     * @return the center point of the screen
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }

        screenSize = TOOLKIT.getScreenSize();

        return (new Point((screenSize.width - size.width) / 2,
                (screenSize.height - size.height) / 2));
    }

    /**
     * Calculates the area of the screen.
     *
     * @param widthPercent widthPercent
     * @param heightPercent widthPercent
     * @return the area of the screen
     */
    public static Dimension calculateScreenArea(
            final float widthPercent, final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > MAX_PERCENT)) {
            throw new IllegalArgumentException("widthPercent cannot be "
                    + "<= 0 or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > MAX_PERCENT)) {
            throw new IllegalArgumentException("heightPercent cannot be "
                    + "<= 0 or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
