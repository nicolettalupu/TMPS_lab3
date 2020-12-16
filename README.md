# Laboratory work nr.3 (Behavioral Design Patterns)
## Task: create a program in which you will use 5 Behavioral Design Patterns
#### During this laboratory work I used 5 Behavioral Design Pattern:
- Template
- Strategy
- Null Object pattern
- Iterator
- Chain

## Template Design Pattern

#### About Template : 
Template method defines the steps to execute an algorithm and it can provide default implementation that might be common for all or some of the subclasses.

In my example I have a **BuildVehicleTemplateStrategy.java** where is the template method in which is defined the order of steps execution.

```java
public abstract class BuildVehicleTemplateStrategy {

    public final void build() {
        buildFrame();
        addEngine();
        addWheels();
        addDashboard();
        addEnergySource();
        System.out.println("vehicle.Vehicle is build");
    }

    public abstract void buildFrame();

    private void addEngine() {
        System.out.println("Adding engine to vehicle");
    }

    private void addWheels() {
        System.out.println("Adding front and back wheels");
    }

    private void addDashboard() {
        System.out.println("Adding a dashboard to vehicle");
    }

    public abstract void addEnergySource();
}
```

I have different type of Vehicles where some methods are overridden. For example let's see the implementation of **SEDAN** Vehicle.

```java
public class BuildSedanVehicleTemplateStrategy extends BuildVehicleTemplateStrategy {

    @Override
    public void buildFrame() {
        System.out.println("Build a frame for Sedan vehicle");
    }

    @Override
    public void addEnergySource() {
        System.out.println("Adding a powerful battery");
    }
}
```

### Template Method Design Pattern Important Points

1.  Template method should consists of certain steps whose order is fixed and for some of the methods, implementation differs from base class to subclass. Template method should be final.
2.  Most of the times, subclasses calls methods from super class but in template pattern, superclass template method calls methods from subclasses, this is known as  Hollywood Principle  – “don’t call us, we’ll call you.”.
3.  Methods in base class with default implementation are referred as  **Hooks**  and they are intended to be overridden by subclasses, if you want some of the methods to be not overridden, you can make them final, for example in our case we can make buildFoundation() method final because if we don’t want subclasses to override it.

## Strategy Design Pattern


#### About Strategy: 
Strategy pattern is also known as **Policy Pattern**. We define multiple algorithms and let client application pass the algorithm to be used as a parameter.

One of the best example of strategy pattern is `Collections.sort()` method that takes Comparator parameter. Based on the different implementations of Comparator interfaces, the Objects are getting sorted in different ways.

In my example I have **BuildVehicleTemplateStrategy.java** which is passed as argument to the Vehicle's constructor.


```java
public abstract class BuildVehicleTemplateStrategy {

    public final void build() {
        buildFrame();
        addEngine();
        addWheels();
        addDashboard();
        addEnergySource();
        System.out.println("vehicle.Vehicle is build");
    }

    public abstract void buildFrame();

    private void addEngine() {
        System.out.println("Adding engine to vehicle");
    }

    private void addWheels() {
        System.out.println("Adding front and back wheels");
    }

    private void addDashboard() {
        System.out.println("Adding a dashboard to vehicle");
    }

    public abstract void addEnergySource();
}
```

Also there is concrete implementation like **BuildHatchbackVehicleTemplateStrategy** of **BuildVehicleTemplateStrategy.java**.


```java
public class BuildHatchbackVehicleTemplateStrategy extends BuildVehicleTemplateStrategy {  
    @Override  
  public void buildFrame() {  
        System.out.println("Build a frame for Hatchback vehicle");  
  }  
  
    @Override  
  public void addEnergySource() {  
        System.out.println("Adding a small battery");  
  }  
}
```
### Strategy Design Pattern Important Points

-   We could have used composition to create instance variable for strategies but we should avoid that as we want the specific strategy to be applied for a particular task. Same is followed in Collections.sort() and Arrays.sort() method that take comparator as argument.
-   Strategy pattern is useful when we have multiple algorithms for specific task and we want our application to be flexible to chose any of the algorithm at runtime for specific task.


## Iterator Design Pattern


#### About Iterator  : 
Iterator design pattern in one of the behavioral pattern. Iterator pattern is used to provide a standard way to traverse through a group of Objects. Iterator pattern is widely used in Java Collection Framework "Java Collections Framework Tutorial". Iterator interface provides methods for traversing through a collection.

According to GoF, iterator design pattern intent is:

> Provides a way to access the elements of an aggregate object without exposing its underlying represenation.

Iterator pattern is not only about traversing through a collection, we can provide different kind of iterators based on our requirements.
Iterator design pattern hides the actual implementation of traversal through the collection and client programs just use iterator methods.



In my example I implemented an iterator which can traverse a Collection of Vehicles.

**VehicleType.java** defines different types of Vehicles.
```java
public enum VehicleType {  
    SEDAN,HATCHBACK,CROSSOVER,ALL  
}
```

**Vehicle.java** abstract class.
```java
public abstract class Vehicle {  
  
 protected String name;  
 protected BuildVehicleTemplateStrategy strategy;  
 private VehicleType type;  
  
 public Vehicle(String name, BuildVehicleTemplateStrategy strategy, VehicleType type) {  
        this.name = name;  
		this.strategy = strategy;  
		this.type = type;  
  }  
  
  public String getName() {  
        return name;  
  }  
  public VehicleType getType() {  
        return type;  
  }  
  
  public abstract void build();  
  
  @Override  
  public String toString() {  
        return "vehicle.Vehicle{" +  
                "name='" + name + '\'' +  
                ", strategy=" + strategy +  
                ", type=" + type +  
                '}';  
  }  
}
```

**VehicleCollection** interface defines the contract for our collection class implementation. Notice that there are methods to add and remove a vehicle but there is no method that returns the list of vehicles. **VehicleCollection** has a method that returns the iterator for traversal.

```java
public interface VehicleCollection {

    void add(Vehicle vehicle);

    void remove(Vehicle vehicle);

    public VehicleIterator iterator(VehicleType type);

}
```

**VehicleIterator.java** interface defines following methods.

```java
public interface VehicleIterator {

    public boolean hasNext();

    public Vehicle next();
}
```

Notice the **inner class** implementation of iterator interface so that the implementation can’t be used by any other collection. Same approach is followed by collection classes also and all of them have inner class implementation of Iterator interface.
**VehicleCollectionImpl.java**

```java
public class VehicleCollectionImpl implements VehicleCollection {

    private List < Vehicle > vehicleList;

    public VehicleCollectionImpl() {
        this.vehicleList = new ArrayList < > ();
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    @Override
    public void remove(Vehicle vehicle) {
        vehicleList.remove(vehicle);
    }

    @Override
    public VehicleIterator iterator(VehicleType type) {
        return new VehicleIteratorImpl(type, this.vehicleList);
    }
    
    private class VehicleIteratorImpl implements VehicleIterator {
        private VehicleType type;
        private List < Vehicle > vehicleList;
        private int position;

        public VehicleIteratorImpl(VehicleType vehicleType, List < Vehicle > vehicleList) {
            this.type = vehicleType;
            this.vehicleList = vehicleList;
        }

        @Override
        public boolean hasNext() {
            while (position < vehicleList.size()) {
                Vehicle vehicle = vehicleList.get(position);
                if (vehicle.getType().equals(type) || type.equals(VehicleType.ALL)) {
                    return true;
                } else
                    position++;
            }
            return false;
        }

        @Override
        public Vehicle next() {
            Vehicle vehicle = vehicleList.get(position);
            position++;
            return vehicle;
        }
    }
}
```

### Iterator Design Pattern Important Points

-   Iterator pattern is useful when you want to provide a standard way to iterate over a collection and hide the implementation logic from client program.
-   The logic for iteration is embedded in the collection itself and it helps client program to iterate over them easily.





## Chain of Responsibility Design Pattern


#### About Chain of Responsibility  : 
Chain of responsibility pattern is used to achieve loose coupling in software design where a request from client is passed to a chain of objects to process them. Then the object in the chain will decide themselves who will be processing the request and whether the request is required to be sent to the next object in the chain or not.

In my example I used Chain of Responsibility pattern as a bunch of actions which has to be performed with an object in a certain order.
So an vehicle has to be buil, paint and tested (test the functionality of the vehicle).

The base interface should have a method to define the next processor in the chain and the method that will process the request.
So here is the base interface **ChainedProcedure.java**

```java
public interface ChainProcedure {
    void setNextChain(ChainProcedure nextChainProcedure);
    void performAction(Vehicle vehicle);
}
```

Below is an example of the processor class  which implements **ChainedProcedure.java** base interface.

```java
public class BuildChainProcedure implements ChainProcedure {

    private ChainProcedure chainProcedure;

    @Override
    public void setNextChain(ChainProcedure nextChainProcedure) {
        this.chainProcedure = nextChainProcedure;
    }

    @Override
    public void performAction(Vehicle vehicle) {
        System.out.println("\n\t #Build link of ChainedProcedure is executed");
        System.out.println(vehicle + "will be build");
        vehicle.build();
        this.chainProcedure.performAction(vehicle);
    }
}
```

### Chain of Responsibility Design Pattern Important Points

-   Client doesn’t know which part of the chain will be processing the request and it will send the request to the first object in the chain. 
-   Each object in the chain will have it’s own implementation to process the request, either full or partial or to send it to the next object in the chain.
-   Every object in the chain should have reference to the next object in chain to forward the request to, its achieved by  java composition.
-   Creating the chain carefully is very important otherwise there might be a case that the request will never be forwarded to a particular processor or there are no objects in the chain who are able to handle the request.
-   Chain of Responsibility design pattern is good to achieve lose coupling but it comes with the trade-off of having a lot of implementation classes and maintenance problems if most of the code is common in all the implementations.


## Null Object Design Pattern


#### About Null Object pattern  : 

In Null Object pattern, a null object replaces check of NULL object instance. Instead of putting if check for a null value, Null Object reflects a do nothing relationship. Such Null object can also be used to provide default behaviour in case data is not available.

In Null Object pattern, we create an abstract class specifying various operations to be done (in my case it is **VehicleIterator.java** ), concrete classes extending this class and a null object class providing do nothing implemention of this class and will be used seemlessly where we need to check null value.

So here is the null object class **NullVehicle.java**

```java
public class NullVehicle extends Vehicle {  
  
    public NullVehicle(String name) {  
        super(name);  
  }  
    @Override  
  public void build() {  
        System.out.println("Null object can't be build");  
  }  
    @Override  
  public boolean isNull() {  
        return true;  
  }  
}
```

