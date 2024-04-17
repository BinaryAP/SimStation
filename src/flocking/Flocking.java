package flocking;

import mvc.*;
import simstation.*;
import java.util.ArrayList;
import java.util.HashMap;

class Flock extends Agent {
    public int steps;

    public Flock() {
        super();
        heading = Heading.random();
        steps = Utilities.rng.nextInt(5) + 1;
    }

    public void update() {
        Agent neighbor =  world.getNeighbor(this, 40);
        if(neighbor != null){
            steps = ((Flock) neighbor).steps;
            heading = neighbor.heading;
        }
        move(steps);
    }

}


class FlockFactory extends SimstationFactory {
    public Model makeModel() {
        return new Flocking();
    }

    public String getTitle() {
        return "Flocking Simulator";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"""
Start: starts the simulation
Suspend: suspends the simulation
Resume: resumes the simulation
Stop: terminates/stops the simulation
Stats: shows simulation stats(all bird speeds)"""};
    }
}
public class Flocking extends Simulation {

    public void populate() {
        for(int i = 0; i < 15; i++)
            addAgent(new Flock());
    }
    public String getStats(){
        ArrayList<Agent> agents = getAgents();
        HashMap<Integer, Integer> speed = new HashMap<Integer,Integer>();

        for (Agent agent : agents) {
            Flock flock = (Flock) agent;

            if (!speed.containsKey(flock.steps)) {
                speed.put(flock.steps, 1);
            } else {
                speed.put(flock.steps, speed.get(flock.steps) + 1);
            }
        }
        final String[] result = {""};
        speed.forEach((key,value)->{
            result[0] +=("#birds @ speed " + key.toString() + " = " + value + "\n");
        });
        return result[0];
    }

    public static void main(String[] args) {
        AppPanel panel = new SimstationPanel(new FlockFactory());
        panel.display();
    }

}
