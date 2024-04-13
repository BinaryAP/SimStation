package flocking;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class Flock extends Agent {

    public Flock() {
        super();

    }

    public void update() {

    }

}


class FlockFactory extends SimstationFactory {
    public Model makeModel() { return new Flocking(); }
    public String getTitle() { return "Flocking Simulator";}

    @Override
    public String[] getHelp() {
        return new String[0];
    }
}

public class Flocking extends Simulation {

    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Flock());
    }

    public static void main(String[] args) {
        AppPanel panel = new SimstationPanel(new FlockFactory());
        panel.display();
    }

}
