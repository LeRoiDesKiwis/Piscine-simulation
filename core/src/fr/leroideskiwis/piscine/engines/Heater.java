package fr.leroideskiwis.piscine.engines;

import fr.leroideskiwis.piscine.Case;
import fr.leroideskiwis.piscine.utils.Util;
import fr.leroideskiwis.piscine.water.Water;

public class Heater extends Engine {

    @Override
    public boolean canAccept(Case aCase) {
        return Math.random() < 0.1;
    }

    @Override
    void tick() {
        for (Water water : waters) {
            float temp = Util.random(0.01f, 1f, 1);
            water.heat(temp);
        }
    }

}
