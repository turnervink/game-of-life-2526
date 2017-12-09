package ca.bcit.comp2526.a2c;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    /* Preferred width and height of the frame. */
    private static final int FRAME_DIMENSION = 720;
    /* Number of rows in the control panel. */
    private static final int CTRL_PANEL_ROWS = 1;
    /* Number of columns in the control panel. */
    private static final int CTRL_PANEL_COLUMNS = 3;
    /* The World displayed in the GameFrame. */
    private World world;
    /* Panel containing Cells in the World. */
    private JPanel cellPanel;
    /* Panel containing UI controls. */
    private JPanel controlPanel;
    /* Starts the World timer. */
    private JButton startBtn;
    /* Stops the World timer. */
    private JButton stopBtn;
    /* Prompts the user to serialize the World. */
    private JButton saveBtn;
    /* Prompts the user to choose a serialized World. */
    private JButton loadBtn;
    /* Resets the World to its default state. */
    private JButton resetBtn;
    /* File chooser for saving. */
    private JFileChooser saveFileChooser;
    /* File chooser for loading. */
    private JFileChooser loadFileChooser;

    /**
     * Creates a GameFrame.
     *
     * @param world the World in the GameFrame
     */
    public GameFrame(final World world) {
        this.world = world;
        this.cellPanel = new JPanel();
        this.controlPanel = new JPanel();
        this.startBtn = new JButton("Start");
        this.stopBtn = new JButton("Stop");
        this.saveBtn = new JButton("Save");
        this.loadBtn = new JButton("Load");
        this.resetBtn = new JButton("Reset");

        this.saveFileChooser = new JFileChooser();
        this.loadFileChooser = new JFileChooser();

        saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveFileChooser.setMultiSelectionEnabled(false);
        saveFileChooser.setSelectedFile(new File("gameoflife.ser"));

        loadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        loadFileChooser.setMultiSelectionEnabled(false);

        FileNameExtensionFilter fileFilter =
                new FileNameExtensionFilter("Serial file", "ser");
        saveFileChooser.setFileFilter(fileFilter);
        loadFileChooser.setFileFilter(fileFilter);
    }

    /**
     * Initializes the GameFrame by filling it with
     * Cells from the World.
     */
    public void init() {
        this.setTitle("Assignment 2a");
        cellPanel.setLayout(new GridLayout(world.getRowCount(),
                                           world.getColumnCount()));
        controlPanel.setLayout(new GridLayout(CTRL_PANEL_ROWS,
                                              CTRL_PANEL_COLUMNS));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                cellPanel.add(world.getCellAt(row, col));
                world.getCellAt(row, col).init();
            }
        }

        this.addActionListeners();
        controlPanel.add(startBtn);
        controlPanel.add(stopBtn);
        controlPanel.add(saveBtn);
        controlPanel.add(loadBtn);
        controlPanel.add(resetBtn);
        stopBtn.setEnabled(false);

        cellPanel.addMouseListener(new MouseAdapter() {
            /**
             * Advances the GameFrame one turn when the mouse is clicked.
             *
             * @param e the MouseEvent that occurred
             */
            public void mouseClicked(final MouseEvent e) {
                takeTurn();
            }
        });

        this.add(cellPanel);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(FRAME_DIMENSION, FRAME_DIMENSION));
        this.pack();
    }

    /**
     * Reinitializes the cellPanel with Cells from the loaded World.
     */
    private void reinit() {
        cellPanel.setLayout(new GridLayout(world.getRowCount(),
                world.getColumnCount()));
        controlPanel.setLayout(new GridLayout(CTRL_PANEL_ROWS,
                CTRL_PANEL_COLUMNS));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                cellPanel.add(world.getCellAt(row, col));
                world.getCellAt(row, col).init();
            }
        }

        this.add(cellPanel);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(FRAME_DIMENSION, FRAME_DIMENSION));
        this.pack();
        this.repaint();
    }


    /**
     * Sets up ActionListeners for control buttons.
     */
    private void addActionListeners() {
        startBtn.addActionListener(e -> {
            setButtonStatus(true);
            world.start();
        });

        stopBtn.addActionListener(e -> {
            setButtonStatus(false);
            world.stop();
        });

        saveBtn.addActionListener(e -> {
            try {
                save();
            } catch (IOException i) {
                JOptionPane.showMessageDialog(null,
                        "Could not save world: "
                                + i.getClass().getSimpleName());
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        loadBtn.addActionListener(e -> {
            try {
                load();
            } catch (ClassNotFoundException
                    | CouldNotAddException
                    | IOException i) {

                JOptionPane.showMessageDialog(null,
                        "Could not load world: "
                                + i.getClass().getSimpleName());
            }
        });

        resetBtn.addActionListener(e -> {
            try {
                world = new World(world.getRowCount(), world.getColumnCount());
                world.init();
                cellPanel.removeAll();
                this.reinit();
            } catch (CouldNotAddException i) {
                JOptionPane.showMessageDialog(null,
                        "Could not reset world: "
                                + i.getClass().getSimpleName());
            }
        });
    }

    /**
     * Advances the World one turn and repaints
     * the GameFrame.
     */
    private void takeTurn() {
        world.takeTurn();
        this.repaint();
    }

    /**
     * Sets the enabled/disabled status of the control buttons.
     *
     * @param isRunning boolean true if the World timer is running
     */
    private void setButtonStatus(boolean isRunning) {
        startBtn.setEnabled(!isRunning);
        stopBtn.setEnabled(isRunning);
        resetBtn.setEnabled(!isRunning);
        saveBtn.setEnabled(!isRunning);
        loadBtn.setEnabled(!isRunning);
    }

    /**
     * Serializes the World and saves it.
     *
     * @throws IOException if the World could not be serialized
     */
    private void save() throws IOException {
        int returnVal = saveFileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            FileOutputStream fileOut
                    = new FileOutputStream(saveFileChooser.getSelectedFile());
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(world);
            objOut.close();
            fileOut.close();
        }
    }

    /**
     * Prompts for a file containing a serialized World and
     * loads it. Sets transient data and returns the loaded
     * World.
     *
     * @throws ClassNotFoundException if the World class could not be found
     * @throws IOException if the World could not be loaded
     * @throws CouldNotAddException if the neighbours of the loaded Cells
     *                              could not be added to the linked list
     */
    public void load() throws ClassNotFoundException,
                              IOException,
                              CouldNotAddException {

        int returnVal = loadFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            World loadedWorld;

            FileInputStream fileIn =
                        new FileInputStream(loadFileChooser.getSelectedFile());
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            loadedWorld = (World) objIn.readObject();
            objIn.close();
            fileIn.close();

            for (int i = 0; i < loadedWorld.getRowCount(); i++) {
                for (int j = 0; j < loadedWorld.getColumnCount(); j++) {
                    loadedWorld.getCellAt(i, j).setWorld(loadedWorld);
                    loadedWorld.getCellAt(i, j).initNeighbours();
                    loadedWorld
                            .getCellAt(i, j).getContents()
                                            .setLocation(
                                            loadedWorld.getCellAt(i, j));
                    loadedWorld.getCellAt(i, j).init();
                }
            }

            cellPanel.removeAll();
            world = loadedWorld;
            this.reinit();
        }
    }
}
