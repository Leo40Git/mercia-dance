package adudecalledleo.merciadance;

import java.awt.*;
import java.awt.event.*;
import java.util.random.RandomGenerator;

import javax.swing.*;

import adudecalledleo.merciadance.fennec.FennecPose;
import adudecalledleo.merciadance.fennec.FennecRenderer;

public final class DanceFrame extends JFrame implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    private final RandomGenerator rand;
    private final Timer refreshTimer;

    private FennecPose pose;
    private int baseX, baseY;

    public DanceFrame() {
        this.rand = RandomGenerator.getDefault();
        this.refreshTimer = new Timer(1000, this);
        // TODO default to lower-right corner of screen
        this.baseX = 0;
        this.baseY = 0;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        getRootPane().setOpaque(false);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void startDancing() {
        newPose();
        setVisible(true);
        refreshTimer.start();
    }

    public void newPose() {
        pose = FennecPose.nextRandom(rand);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // TODO figure out how to prevent Mercia from floating (calculate height offset)
        FennecRenderer.drawMercia((Graphics2D) g, pose, baseX, baseY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        newPose();
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        refreshTimer.stop();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO have mouse movement centered on Mercia
        this.baseX = e.getX();
        this.baseY = e.getY();
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        refreshTimer.restart();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
}
