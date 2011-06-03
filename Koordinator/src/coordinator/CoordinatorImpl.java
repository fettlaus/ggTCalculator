package coordinator;

import ggTCalculator.CoordinatorPOA;
import ggTCalculator.Log;
import ggTCalculator.Process;
import ggTCalculator.Starter;
import ggTCalculator.CoordinatorPackage.alreadyRunning;
import ggTCalculator.CoordinatorPackage.noStarter;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class CoordinatorImpl extends CoordinatorPOA {

    ArrayList<Starter> starterlist = new ArrayList<Starter>();
    ArrayList<String> starter_names = new ArrayList<String>();
    ArrayList<Process> processlist = new ArrayList<Process>();
    Semaphore finished = new Semaphore(0);
    boolean termination_in_process = false;
    int result = 0;
    int def_numproc_max = 5;
    int def_numproc_min = 2;
    String name;

    public CoordinatorImpl(String name) {
        this.name = name;
    }

    @Override
    public void addProcess(String startername, int id, Process process) {
        processlist.add(process);
        System.out.println("Process " + startername + "-" + Integer.toString(id) + " connected");
    }

    @Override
    public void addStarter(String startername, Starter starter) {
        System.out.println("added " + startername + " to list");
        starterlist.add(starter);
        starter_names.add(startername);
    }

    @Override
    public int calculate(int timeout, int mindelay, int maxdelay, int minprocess, int maxprocess, int ggT, Log log)
            throws noStarter, alreadyRunning {
        Random rnd = new Random();
        int procnum = 0;
        // start random amount of processes
        for (Starter starter : starterlist) {
            int cnt = rnd.nextInt(maxprocess - minprocess) + minprocess;
            starter.createProcess(cnt);
            log.log(name, "added " + cnt + " processes to Starter" + starter);
            // TODO: use this?
            procnum += cnt;
        }
        try {
            // sleep to wait for client registration
            Thread.sleep(400);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        Process[] procs = processlist.toArray(new Process[0]);
        for (int i = 0; i < procs.length; i++) {
            Process tmp_left = procs[i - 1];
            Process tmp_right = procs[i + 1];
            // build ring
            if (i == 0) {
                tmp_left = procs[procs.length - 1];
            } else if (i == procs.length - 1) {
                tmp_right = procs[0];
            }
            // give parameters to processes
            procs[i].set_params(tmp_left, tmp_right, (ggT * (rnd.nextInt(100) + 1) * (rnd.nextInt(100) + 1)), log,
                    (rnd.nextInt(maxdelay - mindelay) + mindelay), timeout);
        }
        log.log(name, "params set for each process");
        // choose three processes randomly
        int p1 = 0, p2 = 0, p3 = 0;
        while ((p1 == p2) || (p2 == p3) || (p1 == p3)) {
            p1 = rnd.nextInt(procs.length - 1);
            p2 = rnd.nextInt(procs.length - 1);
            p3 = rnd.nextInt(procs.length - 1);
        }
        // hand random large numbers to processes
        procs[p1].message(ggT * rnd.nextInt(9900) + 100);
        procs[p2].message(ggT * rnd.nextInt(9900) + 100);
        procs[p3].message(ggT * rnd.nextInt(9900) + 100);
        log.log(name, "three processes started");
        try {
            // wait for a process to finish
            finished.acquire();
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        log.log(name, "finished with " + result);
        return result;
    }

    @Override
    public void finished(int r) {
        result = r;
        for (Starter starter : starterlist) {
            starter.quitProcess();
        }
        termination_in_process = false;
        finished.release();
    }

    @Override
    public String[] getStarterList() {
        return (String[]) starter_names.toArray();
    }

    @Override
    public void quit() {
        for (Starter starter : starterlist) {
            starter.quit();
        }
    }

    @Override
    public synchronized boolean terminationStart() {
        if (!termination_in_process) {
            termination_in_process = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean terminationStop() {
        termination_in_process = false;
        return false;
    }

}
