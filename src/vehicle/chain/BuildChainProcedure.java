package vehicle.chain;

import vehicle.nullobject.Vehicle;

public class BuildChainProcedure implements ChainProcedure {

    private ChainProcedure chainProcedure;

    @Override
    public void setNextChain(ChainProcedure nextChainProcedure) {
        this.chainProcedure = nextChainProcedure;
    }

    @Override
    public void performAction(Vehicle vehicle) {
        System.out.println("\n\t #Build link of ChainedProcedure is executed");
        System.out.println(vehicle + "will be build");
        vehicle.build();
        this.chainProcedure.performAction(vehicle);
    }
}
