package simstation;

import mvc.*;

public abstract class SimstationFactory implements AppFactory {

    public Model makeModel() {
        System.out.println("make a new model");
        return new Simulation();
    }

    public View makeView(Model model) {

        return new SimulationView((Simulation)model);
    }

    public String[] getEditCommands() {

        return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
    }
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("Start")) {
            return new StartCommand(model);
        }
        else if (type.equals("Suspend")) {
            return new SuspendCommand(model);
        }
        else if (type.equals("Resume")) {
            return new ResumeCommand(model);
        }
        else if (type.equals("Stop")) {
            return new StopCommand(model);
        }
        else if (type.equals("Stats")) {
            return new StatsCommand(model);
        }
        else {
            return null;
        }
    }

    public String getTitle() {
        return "Simulator";
    }

    public abstract String[] getHelp();

    public String about() {
        return "SimStation. Justin Lee, Luan Nguyen, Arav Panchmatia, 2024";
    }
}
