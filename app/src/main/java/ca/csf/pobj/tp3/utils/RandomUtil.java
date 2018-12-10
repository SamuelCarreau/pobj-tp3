package ca.csf.pobj.tp3.utils;

import java.util.Random;

public final class RandomUtil {

    private RandomUtil(){
        // private so it's a static class
    }

    public static int RandomRange(int min, int max){
        return (int)(Math.random()*((max-min)+1));
    }
}
