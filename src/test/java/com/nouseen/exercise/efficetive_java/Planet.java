package com.nouseen.exercise.efficetive_java;

/**
 * Created by nouseen on 2018/1/7.
 */
public enum Planet {
    MERCURY(3.302E+23, 2.439E6),
    VENUS(4.869E+24, 6.052E6),
    EARTH(5.975E+24, 6.378E6)
    ;

    private final double mass;

    private final double radius;

    private final double surfaceGravity;

    private static final double G = 6.67300E-11;

    Planet(double mass,double radius) {
        this.mass = mass;
        this.radius = radius;
        
        surfaceGravity = G * mass / (radius * radius);
    }
    
    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return  surfaceGravity;
    }
    public double surfaceWeight(double mass) {
        return mass * surfaceGravity;
    }
    
}

