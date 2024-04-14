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
        return new String[] {"click Start to populate simulation world and start program, click Suspend to suspend movement of agents and pause program, click Resume to resume program, click Stop to stop movement of agents and terminate program, and click Stats to show average prisoner score by type of strategy used by one quarter of population"};
    }
}
