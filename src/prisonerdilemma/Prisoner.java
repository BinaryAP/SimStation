package prisonerdilemma;

import mvc.Utilities;
import simstation.*;

public class Prisoner extends Agent {
    private int fitness;
    private final PrisonerSimulation world;
    private boolean partnerCheated = false;
    private final Strategy strategy;

    private Prisoner opponent;

    public Prisoner(String name, Strategy strategy, PrisonerSimulation sim) {
        this.name = name;
        this.strategy = strategy;
        this.world = sim;
        this.fitness = 0;
    }

    public Prisoner(Strategy strategy, PrisonerSimulation sim) {
        this.strategy = strategy;
        this.world = sim;
        this.fitness = 0;
    }

    public boolean cooperate() {
        return this.strategy.cooperate();
    }

    public void update() {
        heading = Heading.random();
        opponent =  (Prisoner) world.getNeighbor(this, 50.00);
        //System.out.println("before update thread = " + this + "fit = " + fitness + "stra = " + strategy.cooperate() + " nifgh = " + neighbor + " neighbor strat = " + neighbor.getStrategy().cooperate());
        if (opponent != null) {
            updateFitness(opponent);
            //System.out.println(" thread = " + this + "fit = " + fitness);
            //System.out.println(" neighbor = " + neighbor + " neigh fit = " + neighbor.getFitness());
            int steps = Utilities.rng.nextInt(10) + 1;
            move(steps);
        }
    }
    public boolean getLastOpponentCooperated() {
        return !partnerCheated;
    }
    public int getFitness() {
        return fitness;
    }

    public synchronized void modifyFitness(int amt) {
        this.fitness += amt;
    }

    public synchronized void setLastOpponentCheated(boolean value) {
        this.partnerCheated = value;
    }

    public void updateFitness(Prisoner opponent) {
        boolean selfChoice = this.getStrategy().cooperate();
        boolean opponentChoice = opponent.getStrategy().cooperate();
        //System.out.println("self name = " + this.name + " strategy = " + selfChoice + "fit = " + this.getFitness());
        //System.out.println("opp name = " + opponent.name + " strategy = " + opponentChoice  + " fit = " + opponent.getFitness());
        if (selfChoice && opponentChoice) {
            modifyFitness(3);
            opponent.modifyFitness(3);
            setLastOpponentCheated(false);
            opponent.setLastOpponentCheated(false);
        }
        else if (!selfChoice && opponentChoice) {
            modifyFitness(5);
            opponent.modifyFitness(0);
            setLastOpponentCheated(false);
            opponent.setLastOpponentCheated(true);
        }
        else if (selfChoice && !opponentChoice) {
            modifyFitness(0);
            opponent.modifyFitness(5);
            setLastOpponentCheated(true);
            opponent.setLastOpponentCheated(false);
        }
        else {
            modifyFitness(1);
            opponent.modifyFitness(1);
            setLastOpponentCheated(true);
            opponent.setLastOpponentCheated(true);
        }
        //System.out.println("after self name = " + this.name + " fit = " + this.getFitness() + " cheted = " + this.partnerCheated);
        //System.out.println(" after opp name = " + opponent.name +  " fit = " + opponent.getFitness() + " cheted = " + opponent.partnerCheated);
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
