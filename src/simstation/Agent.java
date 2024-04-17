package simstation;

import java.io.Serializable;
import mvc.Utilities;
abstract public class Agent implements Runnable, Serializable {
    transient protected Thread myThread;
    protected String name;
    public Heading heading;
    private int xc;
    private int yc;
    private boolean suspended;
    private boolean stopped;
    protected Simulation world;
    public Agent(){
        this.name = null;
        suspended=false;
        stopped=false;
        myThread=null;
        xc = Utilities.rng.nextInt(Simulation.WorldSize);
        yc= Utilities.rng.nextInt(Simulation.WorldSize);
    }
    public Agent(String name){
        this.name = name;
        suspended=false;
        stopped=false;
        myThread=null;
        xc = Utilities.rng.nextInt(Simulation.WorldSize);
        yc= Utilities.rng.nextInt(Simulation.WorldSize);

    }
    public void setWorld(Simulation s){
        world = s;
    }
    public int getxc(){
        return xc;
    }
    public int getyc(){
        return yc;
    }
    public void run(){
        myThread = Thread.currentThread();
        checkSuspended();
        while(!stopped){
            try{
                update();
                Thread.sleep(1000);
                checkSuspended();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void start(){
        if(myThread == null){
            myThread = new Thread(this);
            myThread.start();
        }
    }
    public synchronized void suspend(){
        suspended = true;
    }
    public synchronized void resume(){
        notify();
    }
    public synchronized void stop(){
        stopped = true;
    }
    public synchronized void move(int steps){
        if(xc < 0 ){
            xc = Simulation.WorldSize;
        }
        else if(xc> Simulation.WorldSize){
            xc=0;
        }
        else if(yc<0){
            yc=Simulation.WorldSize;
        }
        else if(yc > Simulation.WorldSize){
            yc=0;
        }

        if(heading == Heading.NORTH){
            yc+=steps;
        }
        if(heading == Heading.EAST){
            xc+=steps;
        }
        if(heading == Heading.SOUTH){
            yc-=steps;
        }
        if(heading == Heading.WEST){
            xc-=steps;
        }
        world.changed();
    }
    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    public abstract void update();
}
