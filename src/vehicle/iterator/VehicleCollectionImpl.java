package vehicle.iterator;

import vehicle.nullobject.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleCollectionImpl implements VehicleCollection {

    private List<Vehicle> vehicleList;

    public VehicleCollectionImpl() {
        this.vehicleList = new ArrayList<>();
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
        private List<Vehicle> vehicleList;
        private int position;

        public VehicleIteratorImpl(VehicleType vehicleType, List<Vehicle> vehicleList) {
            this.type = vehicleType;
            this.vehicleList = vehicleList;
        }


        @Override
        public boolean hasNext() {
            while (position < vehicleList.size()) {
                Vehicle vehicle = vehicleList.get(position);
                if (vehicle.getType() != null && (vehicle.getType().equals(type) || type.equals(VehicleType.ALL))) {
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
