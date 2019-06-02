package com.nouseen.exercise.efficetive_java;

/**
 * Created by nouseen on 2018/1/7.
 */
public class WeightTable {
    public static void main(String[] args) {
        double earthWeight = 20;
        
        double mass = earthWeight / Planet.EARTH.surfaceGravity();

        for (Planet planet : Planet.values()) {

            System.out.printf("Weight on %s is %f%n\n", planet, planet.surfaceWeight(mass));

        }
    }
}
