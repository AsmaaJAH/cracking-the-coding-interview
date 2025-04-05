package ch3stack_queue;

import java.util.LinkedList;

abstract class Animal {
	private int order; 
	protected String name;
	public Animal(String animalName) {
		name = animalName;
	}
	
	public abstract String name();
	
	public void setOrder(int ord) {
		order = ord;
	}
	
	public int getOrder() {
		return order;
	}
	
	public boolean isOlderThan(Animal a) {
		return this.order < a.getOrder();
	}
}

class Cat extends Animal {
	public Cat(String catName) {
		super(catName);
	}
	
	public String name() {
		return "Cat: " + name;
	}
}
class Dog extends Animal {
	public Dog(String dogName) {
		super(dogName);
	}
	
	public String name() {
		return "Dog: " + name;
	}
}
public class AnimalShelter6 {
    LinkedList<Dog> dogs = new LinkedList<Dog>();
	LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order = 0;
	
	public void enqueue(Animal a) {
		a.setOrder(order);
		order++;
		if (a instanceof Dog) {
			dogs.addLast((Dog) a);
		} else if (a instanceof Cat) {
			cats.addLast((Cat)a);
		}
	}
	
	public Animal dequeueAny() {
		if (cats.size() == 0 && dogs.size() == 0) {
            return null;
		}else if (dogs.size() == 0) {
			return dequeueCats();
		}else if (cats.size() == 0) {
			return dequeueDogs();
		}
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if (dog.isOlderThan(cat)) {
			return dogs.poll(); //Retrieves and removes the head of this list.

		} else {
			return cats.poll();
		}
	}
	
	public Animal peek() {
		if (dogs.size() == 0) {
			return cats.peek();
		} else if (cats.size() == 0) {
			return dogs.peek();
		}
		Dog dog = dogs.peek();
		Cat cat = cats.peek();
		if (dog.isOlderThan(cat)) {
			return dog;
		} else {
			return cat;
		}
	}
	
	public int size() {
		return dogs.size() + cats.size();
	}
	
	public Dog dequeueDogs() {
		return dogs.poll();
	}
	
	public Dog peekDogs() {
		return dogs.peek();
	}
	
	public Cat dequeueCats() {
		return cats.poll();
	}
	
	public Cat peekCats() {
		return cats.peek();
	}

    public static void main(String[] args) {
        AnimalShelter6 shelter = new AnimalShelter6();

        shelter.enqueue(new Dog("dogggy"));
        shelter.enqueue(new Cat("cattyyyy"));
        shelter.enqueue(new Dog("hwhw dog"));
        shelter.enqueue(new Cat("bosy cat"));

        System.out.println("Dequeue Any: " + shelter.dequeueAny().name());

        System.out.println("Dequeue Cat: " + shelter.dequeueCats().name());


        System.out.println("Peek Any: " + shelter.peek().name());

        System.out.println("Peek Dog: " + shelter.peekDogs().name());

        System.out.println("Peek Cat: " + shelter.peekCats().name());


        System.out.println("Shelter size: " + shelter.size());
    }
}
