 package com.bisapp.rxjavaexamples.dependencyInjection;

public class ServiceLocator {

    public static Engine createEngine(){
        return new Engine();
    }
}
