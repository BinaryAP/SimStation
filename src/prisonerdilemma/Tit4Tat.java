package prisonerdilemma;

public class Tit4Tat extends Strategy {
    @Override
    public boolean cooperate() {
        return myPrisoner.getLastOpponentCooperated();
    }

}
