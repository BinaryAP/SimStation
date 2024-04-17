package prisonerdilemma;

import mvc.*;

public class RandomlyCooperate extends Strategy {

    @Override
    public boolean cooperate() {
        int val = Utilities.rng.nextInt(2);
        if (val == 0)
            return true;
        else {
            return false;
        }
    }
}