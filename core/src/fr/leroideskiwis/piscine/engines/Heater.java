package fr.leroideskiwis.piscine.engines;

import fr.leroideskiwis.piscine.Case;
import fr.leroideskiwis.piscine.utils.Util;
import fr.leroideskiwis.piscine.water.Water;

public class Heater extends Engine {

    @Override
    public boolean canAccept(Case aCase) {
        return Math.random() < 0.2;
    }

    @Override
    void tick() {
        float temp = Util.random(0.5f, 2f, 1);
        Water water = waters.peek();
        if(water != null && water.canHeat(temp)) water.heat(temp);
    }

}
