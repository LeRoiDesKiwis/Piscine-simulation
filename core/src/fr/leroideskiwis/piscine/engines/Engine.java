package fr.leroideskiwis.piscine.engines;

import fr.leroideskiwis.piscine.Case;
import fr.leroideskiwis.piscine.water.Water;

import java.util.concurrent.ArrayBlockingQueue;

public abstract class Engine {

    private Connection out;
    ArrayBlockingQueue<Water> waters = new ArrayBlockingQueue<>(50);

    public void connectOutput(Connection connection){
        this.out = connection;
    }

    public void accept(Water water){
        if(water == null) return;
        waters.offer(water);
        if(waters.remainingCapacity() <= 1){
            release(waters.poll());
        }
    }

    public abstract boolean canAccept(Case aCase);

    public void release(Water water){
        out.release(water);
    }

    abstract void tick();
}
