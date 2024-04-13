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

    @Override
    public void update() {
        repaint();
    }

    public void setModel(Model newModel) {
//        if (this.model != null) {
//            this.model.unsubscribe(this);
//        }
//        this.model = newModel;
//        if (newModel != null) {
//            model.subscribe(this);
//            update();
        model.unsubscribe(this); //me
        model=newModel;
        model.subscribe(this);//me
        }
}
