package fr.leroideskiwis.piscine;

import java.awt.*;
import java.util.List;

public class Connection {

    private Engine engine;
    private Point point;

    public void connect(Engine engine){
        this.engine = engine;
    }

    public void tick(List<Case> cases){
        if(engine != null) engine.tick(cases);
    }

}
