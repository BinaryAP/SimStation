package prisonerdilemma;


import mvc.*;
import simstation.*;

public class PrisonerSimulation extends Simulation {

    public Strategy getStrategy(int val) {
        if (val == 0)
            return new Cooperate();
        else if (val == 1)
            return new RandomlyCooperate();
        else if (val == 2)
            return new Tit4Tat();
        else
            return new Cheat();
    }

    public String getStats(){
        int avgCooperateScore = 0;
        int avgRandomScore = 0;
        int avgT4TScore = 0;
        int avgCheatScore = 0;
        int length = getAgents().size()/4;

        for (Agent agent : getAgents()) {
            Prisoner prisoner = (Prisoner) agent;
            if (prisoner.getStrategy() instanceof Cooperate) {
                avgCooperateScore += prisoner.getFitness();
            }
            else if (prisoner.getStrategy() instanceof RandomlyCooperate) {
                avgRandomScore += prisoner.getFitness();

            }
            else if (prisoner.getStrategy() instanceof Tit4Tat) {
                avgT4TScore += prisoner.getFitness();

            }
            else {
                avgCheatScore += prisoner.getFitness();
            }
        }
        avgCooperateScore /= length;
        avgCheatScore /= length;
        avgT4TScore /= length;
        avgRandomScore /= length;
        return "Cooperate Avg = " + avgCooperateScore + "\n" +
                "Random Cooperate Avg = " + avgRandomScore + "\n" +
                "Tit4Tat Avg = " + avgT4TScore + "\n" +
                "Cheat Avg = " + avgCheatScore;
    }

    public void populate() {
        Prisoner p;
        Strategy s;
        if (!getAgents().isEmpty()) return;
        for(int i = 0; i < 40; i++) {
            int mod = i % 4;
            s = getStrategy(mod);
            p = new Prisoner(s, this);
            s.setPrisoner(p);
            addAgent(p);
        }
    }


    public static void main(String[] args) {
        AppPanel panel = new SimstationPanel(new PrisonerFactory());
        panel.display();
    }

}
