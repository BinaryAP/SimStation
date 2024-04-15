package plague;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;

public class PlagueView extends SimulationView {
    public PlagueView(PlagueSimulation sim){
        super(sim);
    }
    protected void paintComponent(Graphics gc) {
        PlagueSimulation sim = (PlagueSimulation)model;
        for (Agent agent : sim.getAgents()) {
            PlagueAgent plagueAgent = (PlagueAgent)agent;
            int x = agent.getxc();
            int y = agent.getyc();

            if(plagueAgent.isInfected())
                gc.setColor(Color.RED);
            else gc.setColor(Color.GREEN);

            gc.drawRect(x, y, 5, 5);
            gc.fillRect(x, y, 5, 5);
        }
    }
}
