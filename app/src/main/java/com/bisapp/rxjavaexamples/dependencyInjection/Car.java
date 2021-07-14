package com.bisapp.rxjavaexamples.dependencyInjection;

public class Car {

    private Engine engine;


    public Car(Engine engine) {
        this.engine = ServiceLocator.createEngine();
    }

    public Engine getEngine() {
        return engine;
    }

    public void start() {
        engine.start();
    }
}
