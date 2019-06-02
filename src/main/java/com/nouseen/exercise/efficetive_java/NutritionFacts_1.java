package com.nouseen.exercise.efficetive_java;

/**
 * Created by nouseen on 2017/12/21.
 */
public class NutritionFacts_1 {

    private final int servingSize;
    private final int serving;
    private final int calorries;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public NutritionFacts_1(int servingSize, int serving) {
        this(servingSize, serving, 0);
    }

    public NutritionFacts_1(int servingSize, int serving, int calorries) {
        this(servingSize, serving, calorries, 0);
    }

    public NutritionFacts_1(int servingSize, int serving, int calorries, int fat) {
        this(servingSize, serving, calorries, fat, 0);
    }

    public NutritionFacts_1(int servingSize, int serving, int calorries, int fat, int sodium) {
        this(servingSize, serving, calorries, fat, sodium, 0);
    }

    public NutritionFacts_1(int servingSize, int serving, int calorries, int fat, int sodium, int carbohydrate) {
        this.servingSize = servingSize;
        this.serving = serving;
        this.calorries = calorries;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }
}
