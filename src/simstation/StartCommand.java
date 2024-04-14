package simstation;

import mvc.*;

public class StartCommand extends Command {

    public StartCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;
        if (!((Simulation) model).getAgents().isEmpty()) {
            ((Simulation) model).getAgents().clear();
        }
        simulation.populate();
        simulation.start();
    }
}
