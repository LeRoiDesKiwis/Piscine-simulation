package fr.leroideskiwis.piscine.water;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.leroideskiwis.piscine.utils.Util;

import java.awt.*;

public class Water {

    private float temperature;

    public Water(float temperature){
        this.temperature = temperature;
    }

    public Water(){
        this(21);
    }

    public void render(ShapeRenderer shapeRenderer, int x, int y, int width, int height) {

        float colorTemp = 0.5f-Util.map(temperature, 100, 0.5f);


        Color color = Color.getHSBColor(colorTemp, 0.70f, 0.70f);

        shapeRenderer.setColor(Util.awtColorToGdxColor(color));
        shapeRenderer.rect(x*width,y*height, width, height);

    }

    public void heat(float temp){
        this.temperature+=temp;
    }

    public void cool(float temp){
        this.temperature-=temp;
    }

    public boolean isCooler(Water water){
        return this.temperature-water.temperature <= 1;
    }

    public boolean canCool(float temp){
        return temperature-temp > 0;
    }

    public boolean canHeat(float temp){
        return temperature+temp < 100;
    }
}
