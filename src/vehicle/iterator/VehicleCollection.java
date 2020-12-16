package vehicle.iterator;

import vehicle.nullobject.Vehicle;

public interface VehicleCollection {

    void add(Vehicle vehicle);

    void remove (Vehicle vehicle);

    public VehicleIterator iterator(VehicleType type);

}
