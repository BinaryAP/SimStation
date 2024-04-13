package simstation;

abstract class Agent implements Runnable, Serializable{
    transient protected Thread myThread;
    private String name;
    private Heading heading;
    private int xc;
    private int yc:
    private boolean suspended;
    private boolean stopped;
    private Simulation world;
    public Agent(String name){
        this.name = name;
        suspended=false;
        stopped=false;
        myThread=null;
    }
    public void setSimulation(Simulation s){
        world = s;
    }
    public void getxc(){
        return xc;
    }
    public void getyc(){
        return yc;
    }
    public void run(){
        myThread = Thread.currentThread();
        while(!stopped){
            try{
                update();
                Thread.sleep(1000);
                while(!stopped && suspended) {
                    wait();
                    suspended = false;
                }
            }catch(Exception e){
                System.out.println(e);
            }
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
        xc+=steps;
        yc+=steps;
        world.changed();
    }

    public abstract void update();
}
