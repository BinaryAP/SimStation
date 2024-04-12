package simstation;

import mvc.*;
import java.awt.*;

public class SimulationView extends View {

    protected Graphics gc;
    Simulation simulation;
    public SimulationView(Simulation simulation) {
        super(simulation);
        this.simulation = simulation;
    }

    public void update() {
        Simulation simulation = (Simulation) model;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics gc) {
        super.paintComponent(gc);

        for (Agent agent : simulation.getAgents()) {
            int x = agent.getxc();
            int y = agent.getyc();
            int size = agent.getSize();

            gc.drawOval(x, y, size, size);
            gc.fillOval(x, y, size, size);
        }
    }
}
