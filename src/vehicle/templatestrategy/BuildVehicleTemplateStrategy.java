package vehicle.templatestrategy;

public abstract class BuildVehicleTemplateStrategy {

    public final void build(){

        buildFrame();
        addEngine();
        addWheels();
        addDashboard();
        addEnergySource();
        System.out.println("vehicle.nullobject.Vehicle is build");
    }

    public abstract void buildFrame();

    private void addEngine(){
        System.out.println("Adding engine to vehicle");
    }

    private void addWheels(){
        System.out.println("Adding front and back wheels");
    }

    private void addDashboard(){
        System.out.println("Adding a dashboard to vehicle");
    }

    public abstract void addEnergySource();



}
