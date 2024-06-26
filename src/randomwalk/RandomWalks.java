package randomwalk;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class Drunk extends Agent {

    public Drunk() {
        super();
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }

}


class RandomWalkFactory extends SimstationFactory {
    public Model makeModel() { System.out.println("make new walking model");return new RandomWalks(); }
    public String getTitle() { return "Random Walks";}

    @Override
    public String[] getHelp() {
        return new String[0];
    }
}

public class RandomWalks extends Simulation {

    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Drunk());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimstationPanel(new RandomWalkFactory());
        panel.display();
    }

}
