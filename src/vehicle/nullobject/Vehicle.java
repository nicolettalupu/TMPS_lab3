package vehicle.nullobject;

import vehicle.iterator.VehicleType;
import vehicle.templatestrategy.BuildVehicleTemplateStrategy;

public abstract class Vehicle {

    protected String name;
    protected BuildVehicleTemplateStrategy strategy;
    private VehicleType type;

    public Vehicle(String name, BuildVehicleTemplateStrategy strategy, VehicleType type) {
        this.name = name;
        this.strategy = strategy;
        this.type = type;
    }

    public Vehicle(String name) {
        this.name = name;
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
        return "vehicle.nullobject.Vehicle{" +
                "name='" + name + '\'' +
                ", strategy=" + strategy +
                ", type=" + type +
                '}';
    }

    public boolean isNull() {
        return false;
    }
}
