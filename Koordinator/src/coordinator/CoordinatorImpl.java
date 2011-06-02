package coordinator;

import ggTCalculator.CoordinatorPOA;
import ggTCalculator.Log;
import ggTCalculator.Process;
import ggTCalculator.Starter;
import ggTCalculator.CoordinatorPackage.alreadyRunning;
import ggTCalculator.CoordinatorPackage.noStarter;

public class CoordinatorImpl extends CoordinatorPOA {

    @Override
    public void addStarter(String startername, Starter starter) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addProcess(String startername, int id, Process process) {
        // TODO Auto-generated method stub

    }

    @Override
    public void calculate(int timeout, int mindelay, int maxdelay,
            int minprocess, int maxprocess, int ggT, Log log) throws noStarter,
            alreadyRunning {
        // TODO Auto-generated method stub

    }

    @Override
    public void finished(int r) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean terminationStart() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean terminationStop() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub

    }

}
