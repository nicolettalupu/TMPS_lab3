package vehicle.chain;

import vehicle.nullobject.Vehicle;

public interface ChainProcedure {


    void setNextChain(ChainProcedure nextChainProcedure);

    void performAction(Vehicle vehicle);
}
