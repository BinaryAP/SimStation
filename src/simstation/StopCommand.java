package simstation;

import mvc.*;

public class StopCommand extends Command {

    public StopCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;
        simulation.stop();
    }
}
