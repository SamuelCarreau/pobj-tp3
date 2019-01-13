package ca.csf.pobj.tp3.utils;

//BEN_REVIEW : Import inutile.
import java.util.Random;

public final class RandomUtil {

    private RandomUtil(){
        // private so it's a static class
    }

    //BEN_CORRECTION : Devrait être en "camelCase".
    public static int RandomRange(int min, int max){
        return (int)(Math.random()*((max-min)+1));
    }
}
