package plague;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

public class PlagueAgent extends Agent {
    public static int infected = 0;// # of infected

    private boolean isInfected;

    public PlagueAgent(int i){
        super("plague agent " + i);
        //set the initial infected agents
        if(infected < PlagueSimulation.initialInfected) {
            isInfected = true;
            infected++;
        }
        else
            isInfected = false;
    }

    @Override
    public void update() {
        PlagueAgent partner = (PlagueAgent)world.getNeighbor(this, 15);
        int randomInfect = Utilities.rng.nextInt(100);
        if(partner != null && isInfected && !partner.isInfected){
            //if there is a nearby partner, youre infected, partner not infected
            //tries to infect them
            if(PlagueSimulation.VIRULENCE > randomInfect){
                //if can infect
                partner.isInfected = true;
                infected++;

                System.out.println(this.name + " infected " + partner.name);
            }
        }

        if(isInfected){
            //if infected, try to resist/recover
            int randomResist = Utilities.rng.nextInt(100);
            System.out.println(randomResist);
            if(PlagueSimulation.RESISTANCE > randomResist){
                System.out.println(name + " recovered");
                isInfected = false;
                infected--;
            }
        }

        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20);
        move(steps);
    }

    public boolean isInfected(){
        return isInfected;
    }
}
