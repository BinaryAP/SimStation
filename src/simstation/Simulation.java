package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    transient private Timer timer; // timers aren't serializable
    public static int WorldSize = 500;
    private int clock;
    private ArrayList<Agent> agents;

    public Simulation(){
        clock = 0;
        agents = new ArrayList<Agent>();
    }

    public void start(){
        clock = 0;
        startTimer();
        for(Agent agent : agents)
            agent.start();
        changed();
    }

    public synchronized void suspend(){
        stopTimer();
        for(Agent agent : agents)
            agent.suspend();
        changed();
    }

    public synchronized void resume(){
        startTimer();
        for(Agent agent : agents)
            agent.resume();
        changed();
    }

    public synchronized void stop(){
        stopTimer();
        for(Agent agent : agents)
            agent.stop();
        changed();
    }

    public synchronized Agent getNeighbor(Agent agent, double radius){
        int i = Utilities.rng.nextInt(agents.size());
        int start = i;

        while(true) {
            Agent potentialPartner = agents.get(i);
            if (potentialPartner != agent && getDistanceAB(agent, potentialPartner) < radius) {
                return potentialPartner;
            }
            i = (i + 1) % agents.size();
            if (i == start)
                return null;
        }
    }
    public String getStats(){
        return String.format("agents: %d\nclock: %d", agents.size(), clock);
    }

    private double getDistanceAB(Agent agentA, Agent agentB) {
        //distance formula
        return Math.sqrt(Math.pow(agentA.getxc() - agentB.getxc(), 2) + Math.pow(agentA.getyc() - agentB.getyc(), 2));
    }

    public void addAgent(Agent agent){
        agents.add(agent);
        agent.setWorld(this);
    }


    public void populate(){}//empty by default, specify in subclasses

    //PRE_DEFINED METHODS
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
//            for(Agent agent : agents)
//                agent.run();
        }
    }
    public ArrayList<Agent> getAgents(){
        return agents;
    }
    // etc.

}