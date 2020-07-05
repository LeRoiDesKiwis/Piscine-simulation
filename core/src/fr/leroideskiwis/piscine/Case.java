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
    private final int height;

    public Case(Water water, Point point, int height) {
        this.water = water;
        this.point = point;
        this.height = height;
    }

    private Case pickRandomNeighBoor(){
        return Util.pick(neighboors);
    }

    public void move(Water newWater){
        if(water != null && !isInSurface()) {
            Case newCase = pickRandomNeighBoor();
            newCase.move(water);
        }
        this.water = newWater;
    }

    private boolean isInSurface(){
        return point.y == height-1;
    }

    public Water poll(){
        Water oldWater = water;
        if(water != null && !isInSurface()) {
            this.water = pickRandomNeighBoor().poll();
        } else this.water = null;
        return oldWater;
    }

    public void render(ShapeRenderer shapeRenderer, int width, int height){
        if(water != null) water.render(shapeRenderer, point.x, point.y, width, height);
        else shapeRenderer.setColor(0, 0, 0, 1);
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

    public List<Case> getNeighbours(List<Case> cases){
        return (neighboors == null ? neighboors = loadNeighbours(cases) : neighboors);
    }

    public void transferTemp(Case aCase2, float temp) {
        if(water.canCool(temp) && aCase2.water.canHeat(temp)) {
            water.cool(temp);
            aCase2.water.heat(temp);
        }
    }

    public boolean canTransfer(Case aCase2){
        return water != null && aCase2.water != null && aCase2.water.isCooler(water);
    }

    public boolean isLocatedAt(Point point) {
        return this.point.equals(point);
    }
}
