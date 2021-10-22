package com.example.myrxjava.ordinal.StackAndQueue;

import java.util.LinkedList;

/**
 * This is a problem of queue where two animal basically, Cats and Dogs are sheltered
 * and the people can adopt based on the oldest or can prefer cat or dog which is oldest
 **/
public class AnimalShelter {

    //have a separate LinkedList for both animal
    LinkedList<Dog> dogs = new LinkedList<>();
    LinkedList<Cat> cats = new LinkedList<>();
    int order = 0;


    //enqueue the animal
    public void enqueueAnimal(Animal animal){
        if (animal instanceof Cat){
            Cat cat = new Cat(order++, animal);
            cats.addLast(cat);
        }else{
            Dog dog = new Dog(order++,animal);
            dogs.addLast(dog);
        }
    }

    //dequeue any animal that is oldest
    public Animal dequeueAny(){
        //return dog when cat is empty
        if (!cats.isEmpty() && !dogs.isEmpty()){
            Cat cat = cats.peek();
            Dog dog = dogs.peek();
            if (cat.order < dog.order){
                return dequeueCat();
            }else{
                return dequeueDog();
            }
        }else if (cats.isEmpty() && !dogs.isEmpty()){
            return dequeueDog();
        }else if (!cats.isEmpty()){
            return dequeueCat();
        }else{
            return null;
        }

    }

    //dequeue cat
    public Cat dequeueCat(){
        return cats.poll();
    }

    //dequeue dog
    public Dog dequeueDog(){
        return dogs.poll();
    }

    abstract class Animal {
        int order;
        Animal name;

        Animal(int ord, Animal name) {
            this.order = ord;
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public Animal getName() {
            return name;
        }

        public void setName(Animal name) {
            this.name = name;
        }
    }

    class Dog extends Animal {

        Dog(int ord, Animal name) {
            super(ord, name);
        }
    }

    class Cat extends Animal {

        Cat(int ord, Animal name) {
            super(ord, name);
        }
    }
}


