package simstation;

import mvc.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class SimstationPanel extends AppPanel implements ActionListener {

    public SimstationPanel(AppFactory factory) {
        super(factory);
        JButton start = new JButton("Start");
        JButton suspend = new JButton("Suspend");
        JButton resume = new JButton("Resume");
        JButton stop = new JButton("Stop");
        JButton stats = new JButton("Stats");

        start.addActionListener(this);
        controlPanel.add(start);
        suspend.addActionListener(this);
        controlPanel.add(suspend);
        resume.addActionListener(this);
        controlPanel.add(resume);
        stop.addActionListener(this);
        controlPanel.add(stop);
        stats.addActionListener(this);
        controlPanel.add(stats);

    }
    public void setModel(Model m) {
        super.setModel(m); // calling AppPanel.setModel(m)
        Simulation s = (Simulation)m;
        Iterator<Agent> it = s.iterator();
        while(it.hasNext()) {
            Thread t = new Thread(it.next());
            t.start(); // this will call Agent.run (see below)
        }
    }
}

