package fr.leroideskiwis.piscine.utils;

import fr.leroideskiwis.piscine.Case;

import java.util.ArrayList;
import java.util.List;

public class ThermicalTransfer {

    private List<Case> transferedList = new ArrayList<>();

    public void tick(List<Case> cases){

        for (Case aCase : cases) {
            List<Case> neighbours = aCase.getNeighbours(cases, transferedList);
            for (Case neighbour : neighbours) {
                if(!aCase.canTransfer(neighbour)) continue;
                transferedList.add(neighbour);
                transfer(aCase, neighbour);
            }
        }

    }

    private void transfer(Case aCase1, Case aCase2){
        float temp = Util.random(0, 1, 1);
        aCase2.transferTemp(aCase1, temp);
    }

}
