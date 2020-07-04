package fr.leroideskiwis.piscine.utils;

import com.badlogic.gdx.graphics.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

    public static float map(float i, float oldMax, float newMax){
        return (newMax*i)/oldMax;
    }

    public static Color awtColorToGdxColor(java.awt.Color color){
        float red = map(color.getRed(), 255, 1);
        float green = map(color.getGreen(), 255, 1);
        float blue = map(color.getBlue(), 255, 1);

        return new Color(red, green, blue, 1);

    }

    public static float random(int min, int max, int scale){
        float random = min + (float)(Math.random() * ((max - min) + 1));
        BigDecimal bd = new BigDecimal(random);
        bd = bd.setScale(scale, RoundingMode.HALF_EVEN);
        return bd.floatValue();

    }

}
