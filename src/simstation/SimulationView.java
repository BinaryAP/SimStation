package simstation;

import mvc.*;
import java.awt.*;

public class SimulationView extends View {

    protected Graphics gc;
    public SimulationView(Simulation simulation) {
        super(simulation);
    }

    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics gc) {
        Simulation simulation = (Simulation) model;
        super.paintComponent(gc);
        for (Agent agent : simulation.getAgents()) {
            int x = agent.getxc();
            int y = agent.getyc();
            gc.setColor(Color.WHITE);
            gc.drawOval(x, y, 10, 5);
            gc.fillOval(x, y, 10, 5);
        }
        repaint();
    }

    public void propertyChanged(int oldX, int oldY, int newX, int newY) {
        Simulation simulation = (Simulation) model;
        int width = Math.abs(newX-oldX) + 10;
        int height = Math.abs(newY-oldY) + 10;
        int x, y;
        if (oldX < newX) {
            x = oldX;
        }
        else {
            x = newX;
        }
        if (oldY < newY) {
            y = oldY;
        }
        else {
            y = newY;
        }
        repaint(x, y, width, height);
    }
}
