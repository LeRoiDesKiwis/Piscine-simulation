package fr.leroideskiwis.piscine.engines;

import fr.leroideskiwis.piscine.Case;

public class Melanger extends Engine{
    @Override
    public boolean canAccept(Case aCase) {
        return Math.random() < 0.05;
    }

    @Override
    void tick() {

    }
}
