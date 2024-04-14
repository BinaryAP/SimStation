package prisonerdilemma;

public abstract class Strategy {
    Prisoner myPrisoner;
    public void setPrisoner(Prisoner p) {
        myPrisoner = p;
    }

    public abstract boolean cooperate();
}
