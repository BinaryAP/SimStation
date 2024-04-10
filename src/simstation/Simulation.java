package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    transient private Timer timer; // timers aren't serializable
    private int clock;
    private ArrayList<Agent> agents;

    public Simulation(){
        clock = 0;
        agents = new ArrayList<Agent>();
    }

    public void start(){
        startTimer();
        for(Agent agent : agents)
            agent.start();
        changed();
    }

    public void suspend(){
        stopTimer();
        for(Agent agent : agents)
            agent.suspend();
        changed();
    }

    public void resume(){
        startTimer();
        for(Agent agent : agents)
            agent.resume();
        changed();
    }

    public void stop(){
        stopTimer();
        for(Agent agent : agents)
            agent.stop();
        changed();
    }

    public Agent getNeighbor(Agent agent, double radius){

        for(Agent potentialNeighbor : agents)
            if(getDistanceAB(agent, potentialNeighbor) < radius)//if an agent is within the accepted radius
                return potentialNeighbor;

        return null;
    }

    private double getDistanceAB(Agent agentA, Agent agentB) {
        //distance formula
        return Math.sqrt(Math.pow(agentA.getxC - agentB.getxC, 2) + Math.pow(agentA.getyC - agentB.getyC, 2));
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
        }
    }

    // etc.

}