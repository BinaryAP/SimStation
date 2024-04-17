package prisonerdilemma;

import mvc.*;
import simstation.*;

public class PrisonerFactory extends SimstationFactory {

    public Model makeModel() {
        return new PrisonerSimulation();
    }
    public String getTitle() {
        return "Prisoner's Dilemma Tournament";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Start: populate simulation world and start program \n" +
                        "Suspend: suspend movement of agents and pause program \n" +
                        "Resume: resume program \n" + "Stop: stop movement of agents \n" +
                        "Stats: to show average prisoner score by type of strategy used by one quarter of population"
        };
    }
}