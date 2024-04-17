package prisonerdilemma;

import java.io.Serializable;

public abstract class Strategy implements Serializable {
    Prisoner myPrisoner;
    public void setPrisoner(Prisoner p) {
        myPrisoner = p;
    }

    public abstract boolean cooperate();
}