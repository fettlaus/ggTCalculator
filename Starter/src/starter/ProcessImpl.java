package starter;

import ggTCalculator.Coordinator;
import ggTCalculator.Log;
import ggTCalculator.Process;
import ggTCalculator.ProcessPOA;

public class ProcessImpl extends ProcessPOA {

    String name;
    int id;
    Coordinator coordinator;
    Process left;
    Process right;
    
    public ProcessImpl(String name, int nextID, Coordinator coordinator) {
        this.name = name;
        this.id = nextID;
        this.coordinator = coordinator;
    }

    @Override
    public Process left() {
        return left;
    }

    @Override
    public void left(Process newLeft) {
        left = newLeft;

    }

    @Override
    public Process right() {
        return right;
    }

    @Override
    public void right(Process newRight) {
        right = newRight;

    }

    @Override
    public int number() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void number(int newNumber) {
        // TODO Auto-generated method stub

    }

    @Override
    public Log log() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void log(Log newLog) {
        // TODO Auto-generated method stub

    }

    @Override
    public double delay() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delay(double newDelay) {
        // TODO Auto-generated method stub

    }

    @Override
    public int timeout() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void timeout(int newTimeout) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean ready() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void ready(boolean newReady) {
        // TODO Auto-generated method stub

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

}
