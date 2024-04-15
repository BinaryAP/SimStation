package plague;

import mvc.AppPanel;
import simstation.Agent;
import simstation.SimstationPanel;
import simstation.Simulation;

import java.util.ArrayList;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; // % chance of infection
    public static int initialInfected = 40; // # of agent initially infected
    public static int totalAgents = 50;// # of agent in the simulation
    public static int RESISTANCE = 2;//chance to resist/recover an infection

    @Override
    public void populate() {
        for(int i = 0; i < totalAgents; i++)
            addAgent(new PlagueAgent(i));

    }

    public String getStats() {
        float infected = covertToPercent(PlagueAgent.infected);
        float recovered = covertToPercent(PlagueAgent.recovered);
        return String.format("%s\ninfected: %.2f%%", super.getStats(), infected);
    }
    public float covertToPercent(int i){
        return ((float) i /totalAgents)*100;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimstationPanel(new PlagueFactory());
        panel.display();
    }
}
