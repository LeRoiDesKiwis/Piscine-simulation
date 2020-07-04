package fr.leroideskiwis.piscine;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.leroideskiwis.piscine.utils.Interval;
import fr.leroideskiwis.piscine.utils.Util;
import fr.leroideskiwis.piscine.water.Water;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

public class Case {

    private Water water;
    private final Point point;
    private List<Case> neighboors;

    public Case(Water water, Point point) {
        this.water = water;
        this.point = point;
    }

    private Case pickRandomNeighBoor(){
        return Util.pick(neighboors);
    }

    public void move(Water newWater){
        if(water != null && !hasEmptyNeighBoors()) {
            Case newCase = pickRandomNeighBoor();
            newCase.move(water);
        }
        this.water = newWater;
    }

    private boolean hasEmptyNeighBoors(){
        return neighboors.size() < 8;
    }

    public Water poll(){
        Water oldWater = water;
        if(water != null && !hasEmptyNeighBoors()) {
            this.water = pickRandomNeighBoor().poll();
        }
        return oldWater;
    }

    public void render(ShapeRenderer shapeRenderer, int width, int height){
        if(water != null) water.render(shapeRenderer, point.x, point.y, width, height);
        else shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(point.x*width, point.y*height, width, height);
    }

    private List<Case> loadNeighbours(List<Case> cases){
        Interval xInterval = new Interval(point.x-1, point.x+1);
        Interval yInterval = new Interval(point.y-1, point.y+1);
        return cases.stream()
                .filter(aCase -> xInterval.isBetween(aCase.point.x) && yInterval.isBetween(aCase.point.y))
                .filter(aCase -> !aCase.equals(this))
                .collect(Collectors.toList());
    }

    public List<Case> getNeighbours(List<Case> cases, List<Case> blacklist){
        return (neighboors == null ? neighboors = loadNeighbours(cases) : neighboors).stream().filter(aCase -> !blacklist.contains(aCase)).collect(Collectors.toList());
    }

    public void transferTemp(Case aCase2, float temp) {
        water.cool(temp);
        aCase2.water.heat(temp);
    }

    public boolean canTransfer(Case aCase2){
        return aCase2.water.isCooler(water);
    }

    public boolean isLocatedAt(Point point) {
        return this.point.equals(point);
    }
}
