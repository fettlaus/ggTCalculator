package starter;

import java.util.concurrent.Semaphore;

import ggTCalculator.Coordinator;
import ggTCalculator.Log;
import ggTCalculator.Process;
import ggTCalculator.ProcessPOA;

public class ProcessImpl extends ProcessPOA implements Runnable {

    String name;
    int id;
    Coordinator coordinator;
    Process left;
    Process right;
    int number;
    double delay;
    int timeout;
    Semaphore ready = new Semaphore(0);
    Log log;
    Thread this_thread = new Thread(this);
    
    public ProcessImpl(String name, int nextID, Coordinator coordinator) {
        this.name = name;
        this.id = nextID;
        this.coordinator = coordinator;
        // detach this object
        this_thread.start();
    }

    @Override
    public boolean terminate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void message(int number) {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        
            try {
                ready.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            while(true){
                
            }
    }

    @Override
    public void set_params(Process left, Process right, int number, Log log,
            double delay, int timeout) {
        this.left = left;
        this.right = right;
        this.number = number;
        this.log = log;
        this.delay = delay;
        this.timeout = timeout;
        log.log(name+Integer.toString(id), "got params!");
        ready.release();
    }

}
