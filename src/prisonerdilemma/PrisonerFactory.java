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
        return null;
    }
}
