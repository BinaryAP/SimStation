package plague;

import mvc.Model;
import mvc.View;
import randomwalk.RandomWalks;
import simstation.SimstationFactory;
import simstation.Simulation;
import simstation.SimulationView;

public class PlagueFactory extends SimstationFactory {
    public Model makeModel() {return new PlagueSimulation(); }
    public View makeView(Model model){return new PlagueView((PlagueSimulation)model);}
    public String getTitle() { return "Plague";}
    @Override
    public String[] getHelp() {
        return new String[]{"""
Start: starts the simulation
Suspend: suspends the simulation
Resume: resumes the simulation
Stop: terminates/stops the simulation
Stats: shows simulation stats(#agents, clock, infected, recovered"""};
    }
}
