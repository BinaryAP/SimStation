package prisonerdilemma;

import mvc.Utilities;
import simstation.*;

public class Prisoner extends Agent {
    private int fitness;
    private final PrisonerSimulation world;
    private boolean partnerCheated = false;
    private final Strategy strategy;

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
        Prisoner neighbor =  (Prisoner) world.getNeighbor(this, 10.00);
        System.out.println("before update thread = " + this + "fit = " + fitness + "stra = " + strategy.cooperate() + " nifgh = " + neighbor + " neighbor strat = " + neighbor.getStrategy().cooperate());
        updateFitness(neighbor.strategy.cooperate());
        System.out.println(" thread = " + this + "fit = " + fitness);
        System.out.println(" neighbor = " + neighbor + " neigh fit = " + neighbor.getFitness());
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
    public boolean getLastOpponentCooperated() {
        return partnerCheated;
    }
    public int getFitness() {
        return fitness;
    }

    public void updateFitness(boolean opponentChoice) {
        boolean selfChoice = this.strategy.cooperate();
        if (selfChoice && opponentChoice) {
            fitness += 3;
            partnerCheated = false;
        }
        else if (!selfChoice && opponentChoice) {
            fitness += 5;
            partnerCheated = false;
        }
        else if (selfChoice && !opponentChoice) {
            fitness += 0;
            partnerCheated = true;
        }
        else {
            fitness += 1;
            partnerCheated = true;
        }

    }

    public Strategy getStrategy() {
        return strategy;
    }
}
