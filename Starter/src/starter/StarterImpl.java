package starter;

import java.util.ArrayList;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;

import ggTCalculator.Coordinator;
import ggTCalculator.StarterPOA;

public class StarterImpl extends StarterPOA {
    Coordinator coordinator;
    POA rootPOA;
    Thread sdh;
    ORB orb;
    NamingContextExt nc;
    int nextID = 0;
    ArrayList<Process> processes = new ArrayList<Process>();
    public StarterImpl(POA rootPOA,ORB orb, NamingContextExt nc, Thread sdh, Coordinator coordinator){
        this.coordinator = coordinator;
        this.rootPOA = rootPOA;
        this.sdh = sdh;
        this.orb = orb;
        this.nc = nc;
    }

    @Override
    public void createProcess(int count) {
        for(int i = 0;i<count;i++){
            
            nextID++;
        }

    }

    @Override
    public void quitProcess() {
        // TODO Auto-generated method stub

    }

    @Override
    public void quit() {
        // remove hook
        Runtime.getRuntime().removeShutdownHook(sdh);
        
        // quit ggT processes
        quitProcess();
        
        // init shutdomn thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                orb.shutdown(true);
            }
        }).start();

    }

}
