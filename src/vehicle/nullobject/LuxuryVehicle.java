package vehicle.nullobject;

import vehicle.iterator.VehicleType;
import vehicle.templatestrategy.BuildVehicleTemplateStrategy;

public class LuxuryVehicle extends Vehicle {

    public LuxuryVehicle(String name, BuildVehicleTemplateStrategy strategy, VehicleType type) {
        super(name, strategy, type);
    }

    @Override
    public void build() {
        strategy.build();
    }
}
