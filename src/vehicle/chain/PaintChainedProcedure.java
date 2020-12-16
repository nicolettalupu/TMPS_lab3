package vehicle.chain;

import vehicle.nullobject.Vehicle;

public class PaintChainedProcedure implements ChainProcedure {

    private ChainProcedure chainProcedure;

    @Override
    public void setNextChain(ChainProcedure nextChainProcedure) {
        this.chainProcedure = nextChainProcedure;
    }

    @Override
    public void performAction(Vehicle vehicle) {
        System.out.println("\n\t #Paint link of ChainedProcedure is executed");
        System.out.println(vehicle + "will be painted in a random color");
        this.chainProcedure.performAction(vehicle);
    }
}
