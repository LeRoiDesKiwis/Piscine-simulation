package fr.leroideskiwis.piscine.engines;

import fr.leroideskiwis.piscine.Case;
import fr.leroideskiwis.piscine.engines.Engine;
import fr.leroideskiwis.piscine.water.Water;

import java.awt.*;
import java.util.List;

public class Connection {

    private Engine engine;
    private Case aCase;

    public Connection(List<Case> cases, Point point){
        this.aCase = cases.stream().filter(aCase -> aCase.isLocatedAt(point)).findAny().get();
    }

    public void connect(Engine engine, ConnectionMode connectionMode){
        this.engine = engine;
        engine.connect(connectionMode, this);
    }

    public void tick(){
        if(engine != null && engine.canAccept(aCase)) {
            engine.accept(aCase.poll());
            engine.tick();
        };
    }

    public void release(Water water){
        aCase.move(water);
    }

    public enum ConnectionMode {
        INPUT, OUTPUT;
    }

}
