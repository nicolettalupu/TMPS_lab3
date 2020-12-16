package vehicle.templatestrategy;

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
