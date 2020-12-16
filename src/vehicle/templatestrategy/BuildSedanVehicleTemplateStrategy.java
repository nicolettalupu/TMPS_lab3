package vehicle.templatestrategy;

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
