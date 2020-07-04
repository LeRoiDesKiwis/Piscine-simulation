package fr.leroideskiwis.piscine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.leroideskiwis.piscine.engines.Connection;
import fr.leroideskiwis.piscine.engines.Engine;
import fr.leroideskiwis.piscine.engines.Heater;
import fr.leroideskiwis.piscine.utils.ThermicalTransfer;
import fr.leroideskiwis.piscine.water.Water;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Piscine {

    private List<Case> cases = new ArrayList<>();
    private final int size;
    private List<Connection> connections = new ArrayList<>();

    public Piscine(int size){
        this.size = size;
        fill(90);
        connections.add(new Connection(cases, new Point(0, 5)));
        connections.add(new Connection(cases, new Point(0, 75)));
        connect(0, 1, new Heater());
    }

    private void fill(int height) {
        for(int y = 0; y < height; y++){
            for(int x = 0; x < size; x++){
                Water water = new Water(y+5);
                cases.add(new Case(water, new Point(x, y)));
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer){
        int windowWidth = Gdx.graphics.getWidth();
        cases.forEach(aCase -> aCase.render(shapeRenderer, windowWidth/size, windowWidth/size));
    }

    public void tick(){
        ThermicalTransfer thermicalTransfer = new ThermicalTransfer();
        thermicalTransfer.tick(cases);
        connections.forEach(Connection::tick);
    }

    public void connect(int in, int out, Engine engine){
        Connection input = connections.get(in);
        Connection output = connections.get(out);
        input.connect(engine, Connection.ConnectionMode.INPUT);
        output.connect(engine, Connection.ConnectionMode.OUTPUT);
    }
}
