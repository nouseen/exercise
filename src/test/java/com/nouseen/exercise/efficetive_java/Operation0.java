package com.nouseen.exercise.efficetive_java;

import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;

/**
 * Created by nouseen on 2018/1/7.
 */
public enum Operation0 {

    PLUS,MINUS,TIMES,DIVIDE;

    double apply(double x,double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
                
        }
        throw new AssertionError("Unknown op:" + this);
    }
}
