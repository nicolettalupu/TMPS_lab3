import vehicle.chain.BuildChainProcedure;
import vehicle.chain.ChainProcedure;
import vehicle.chain.PaintChainedProcedure;
import vehicle.chain.TestChainProcedure;
import vehicle.iterator.VehicleCollection;
import vehicle.iterator.VehicleCollectionImpl;
import vehicle.iterator.VehicleIterator;
import vehicle.iterator.VehicleType;
import vehicle.nullobject.SportVehicle;
import vehicle.nullobject.Vehicle;
import vehicle.nullobject.VehicleFactory;
import vehicle.templatestrategy.BuildHatchbackVehicleTemplateStrategy;
import vehicle.templatestrategy.BuildSedanVehicleTemplateStrategy;

public class Main {


    public static void main(String[] args) {

        ChainProcedure chainProcedure = getChainedProcedure();
        VehicleCollection vehicleCollection = getVehicles();
        VehicleIterator iterator = vehicleCollection.iterator(VehicleType.ALL);

        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            System.out.println("\n\n---------------------------------------");
            System.out.println(vehicle.toString());
            chainProcedure.performAction(vehicle);
            System.out.println("---------------------------------------");
        }

    }


    public static ChainProcedure getChainedProcedure() {
        ChainProcedure c1 = new BuildChainProcedure();
        ChainProcedure c2 = new PaintChainedProcedure();
        ChainProcedure c3 = new TestChainProcedure();


        c1.setNextChain(c2);
        c2.setNextChain(c3);

        return c1;
    }

    public static VehicleCollection getVehicles() {
        VehicleCollection constructionCollection = new VehicleCollectionImpl();

        constructionCollection.add(VehicleFactory.getVehicle("sport_car", "FORD", new BuildHatchbackVehicleTemplateStrategy(), VehicleType.HATCHBACK));
        // the iterator will ignore CROSSOVER type as it will be a nullable object
        constructionCollection.add(VehicleFactory.getVehicle("sport_car", "TOYOTA", new BuildHatchbackVehicleTemplateStrategy(), VehicleType.CROSSOVER));
        constructionCollection.add(VehicleFactory.getVehicle("sport_car", "BMW", new BuildHatchbackVehicleTemplateStrategy(), VehicleType.SEDAN));


        return constructionCollection;
    }
}
