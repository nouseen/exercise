package com.nouseen.exercise.efficetive_java;

import org.junit.Test;

/**
 * Created by nouseen on 2017/12/21.
 */
public class NutritionFactsTest {
    
    @Test
    public void testConstructor(){
        NutritionFacts_1 nutritionFacts1 = new NutritionFacts_1(240, 8, 100, 0, 35, 27);
        System.out.println(nutritionFacts1);
    }
    
    @Test
    public void testBuilderPattern(){
        NutritionFacts build = new NutritionFacts.Builder(240, 8).calories(1).sodium(10).build();
        System.out.println(build);
    }

}