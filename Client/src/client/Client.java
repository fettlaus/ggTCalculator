package client;

import ggTCalculator.Coordinator;
import ggTCalculator.CoordinatorHelper;
import ggTCalculator.Log;
import ggTCalculator.LogHelper;

import java.util.Properties;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Client {
    private static Log log;
    static ORB orb;

    public static void main(String[] args) {

        String host = "localhost";
        String port = "1080";
        String coordinator_name = "Coordinator";

        // digest commandline args
        // check if we have enough arguments
        if (args.length >= 4) {
            host = args[0];
            port = args[1];
            coordinator_name = args[2];

            try {
                // set properties
                Properties props = new Properties();
                props.put("org.omg.CORBA.ORBInitialPort", port);
                props.put("org.omg.CORBA.ORBInitialHost", host);
                orb = ORB.init(args, props);

                // get rootPOa and activate POAManager
                POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                rootPoa.the_POAManager().activate();

                // get NamingService
                NamingContextExt nc = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));

                // get Coordinator
                Coordinator coord = CoordinatorHelper.narrow(nc.resolve_str(coordinator_name));

                // create new Log object
                LogImpl logI = new LogImpl();
                log = LogHelper.narrow(rootPoa.servant_to_reference(logI));

                // calculate ggT
                if (args[3].equalsIgnoreCase("calc") && (args.length >= 10)) {
                    int minggT = Integer.parseInt(args[4]);
                    int maxggT = Integer.parseInt(args[5]);
                    int minDe = Integer.parseInt(args[6]);
                    int maxDe = Integer.parseInt(args[7]);
                    int to = Integer.parseInt(args[8]);
                    int ggt = Integer.parseInt(args[9]);
                    int result = coord.calculate(to, minDe, maxDe, minggT, maxggT, ggt, log);
                    System.out.println("Ergebnis: " + result);
                    // list Starter
                } else if (args[3].equalsIgnoreCase("list")) {
                    String[] s = coord.getStarterList();
                    log.log("Client", "StarterList requested");
                    for (String element : s) {
                        System.out.println(element);
                    }
                    // quit Starter
                } else if (args[3].equalsIgnoreCase("quit")) {
                    log.log("Client", "quit Starter");
                    coord.quit();
                    // give help
                } else if (args[3].equalsIgnoreCase("help")) {
                    System.out.println("Folgendes Format ist möglich:");
                    System.out
                            .println("Client NameServiceHost NameServicePort KoordinatorName <list | quit | calc minProcess maxProcess mindelay maxDelay timeout ggT>");
                } else {
                    System.out.println("unbekannter Befehl");
                    log.log("Client", "unknown command");
                }

            } catch (Exception ex) {
                System.err.println(ex);
                System.exit(1);
            }
        } else {
            System.out.println("Nicht ausreichend Parameter");
        }
    }

}
