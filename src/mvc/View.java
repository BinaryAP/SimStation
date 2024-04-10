package mvc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class View extends JPanel implements Subscriber {

    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        this.setBorder(blackLine);
    }

    public void setModel(Model nModel) {
        if (this.model != null) {
            this.model.unsubscribe(this);
        }
        this.model = nModel;
        if (nModel != null) {
            model.subscribe(this);
            update();
        }
    }

    @Override
    public void update() {
        repaint();
    }
}