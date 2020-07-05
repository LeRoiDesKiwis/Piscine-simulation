package fr.leroideskiwis.piscine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.leroideskiwis.piscine.utils.ThermicalTransfer;
import fr.leroideskiwis.piscine.water.Water;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Piscine {

    private List<Case> cases = new ArrayList<>();
    private final int size;
    private final ThermicalTransfer thermicalTransfer = new ThermicalTransfer();

    public Piscine(int size){
        this.size = size;
        fill(40);
    }

    private void fill(int height) {
        for(int y = 0; y < height/2; y++){
            for(int x = 0; x < size; x++){
                Water water = new Water(10);
                cases.add(new Case(water, new Point(x, y)));
            }
        }
        for(int y = height/2; y < height; y++){
            for(int x = 0; x < size; x++){
                Water water = new Water(80);
                cases.add(new Case(water, new Point(x, y)));
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer){
        int windowWidth = Gdx.graphics.getWidth();
        cases.forEach(aCase -> aCase.render(shapeRenderer, windowWidth/size, windowWidth/size));
    }

    public void tick(){
        thermicalTransfer.tick(cases);
    }

}
