package vehicle.nullobject;


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
