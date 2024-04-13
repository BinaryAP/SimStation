package simstation;

import mvc.*;

public class StatsCommand extends Command {

    public StatsCommand(Model model) {
        super(model);
    }

    public void execute() {
        Simulation simulation = (Simulation) model;
        String[] items = simulation.stats();
        Utilities.inform(items);
    }
}
