package vehicle.nullobject;

import vehicle.iterator.VehicleType;
import vehicle.templatestrategy.BuildVehicleTemplateStrategy;

public class SportVehicle extends Vehicle {

    public SportVehicle(String name, BuildVehicleTemplateStrategy strategy, VehicleType type) {
        super(name, strategy, type);
    }

    @Override
    public void build() {
        strategy.build();
    }

}
