package fr.leroideskiwis.piscine.utils;

import fr.leroideskiwis.piscine.Case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThermicalTransfer {

    private final List<Case> transferedList = new ArrayList<>();
    private final Map<Case, Integer> coldedCases = new HashMap<>();

    public void tick(List<Case> cases){
        transferedList.clear();

        for (Case aCase : cases) {
            List<Case> neighbours = aCase.getNeighbours(cases, transferedList);
            if(coldedCases.containsKey(aCase)){
                coldedCases.put(aCase, coldedCases.get(aCase)+1);
                if(coldedCases.get(aCase) > 2) coldedCases.remove(aCase);
                continue;
            } else coldedCases.put(aCase, 1);
            for (Case neighbour : neighbours) {
                if(!aCase.canTransfer(neighbour)) continue;
                transferedList.add(neighbour);
                transfer(aCase, neighbour);
            }
        }

    }

    private void transfer(Case aCase1, Case aCase2){
        float temp = Util.random(0, 1, 1);
        aCase1.transferTemp(aCase2, temp);
    }

}
