package fr.leroideskiwis.piscine.engines;

import fr.leroideskiwis.piscine.utils.Util;
import fr.leroideskiwis.piscine.water.Water;

public class Heater extends Engine {

    @Override
    void tick() {
        for (Water water : waters) {
            float temp = Util.random(0.01f, 0.1f, 1);
            water.heat(temp);
        }
    }

}
