package ggTCalculator;


/**
* ggTCalculator/CoordinatorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 22:33 Uhr UTC
*/

public abstract class CoordinatorPOA extends org.omg.PortableServer.Servant
 implements ggTCalculator.CoordinatorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addStarter", new java.lang.Integer (0));
    _methods.put ("addProcess", new java.lang.Integer (1));
    _methods.put ("getStarterList", new java.lang.Integer (2));
    _methods.put ("calculate", new java.lang.Integer (3));
    _methods.put ("finished", new java.lang.Integer (4));
    _methods.put ("terminationStart", new java.lang.Integer (5));
    _methods.put ("terminationStop", new java.lang.Integer (6));
    _methods.put ("quit", new java.lang.Integer (7));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // ggTCalculator/Coordinator/addStarter
       {
         String startername = in.read_string ();
         ggTCalculator.Starter starter = ggTCalculator.StarterHelper.read (in);
         this.addStarter (startername, starter);
         out = $rh.createReply();
         break;
       }

       case 1:  // ggTCalculator/Coordinator/addProcess
       {
         String startername = in.read_string ();
         int id = in.read_long ();
         ggTCalculator.Process process = ggTCalculator.ProcessHelper.read (in);
         this.addProcess (startername, id, process);
         out = $rh.createReply();
         break;
       }

       case 2:  // ggTCalculator/Coordinator/getStarterList
       {
         String $result[] = null;
         $result = this.getStarterList ();
         out = $rh.createReply();
         ggTCalculator.StarterListeHelper.write (out, $result);
         break;
       }

       case 3:  // ggTCalculator/Coordinator/calculate
       {
         try {
           int timeout = in.read_long ();
           int mindelay = in.read_long ();
           int maxdelay = in.read_long ();
           int minprocess = in.read_long ();
           int maxprocess = in.read_long ();
           int ggT = in.read_long ();
           ggTCalculator.Log log = ggTCalculator.LogHelper.read (in);
           int $result = (int)0;
           $result = this.calculate (timeout, mindelay, maxdelay, minprocess, maxprocess, ggT, log);
           out = $rh.createReply();
           out.write_long ($result);
         } catch (ggTCalculator.CoordinatorPackage.noStarter $ex) {
           out = $rh.createExceptionReply ();
           ggTCalculator.CoordinatorPackage.noStarterHelper.write (out, $ex);
         } catch (ggTCalculator.CoordinatorPackage.alreadyRunning $ex) {
           out = $rh.createExceptionReply ();
           ggTCalculator.CoordinatorPackage.alreadyRunningHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // ggTCalculator/Coordinator/finished
       {
         int r = in.read_long ();
         this.finished (r);
         out = $rh.createReply();
         break;
       }

       case 5:  // ggTCalculator/Coordinator/terminationStart
       {
         boolean $result = false;
         $result = this.terminationStart ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 6:  // ggTCalculator/Coordinator/terminationStop
       {
         boolean $result = false;
         $result = this.terminationStop ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 7:  // ggTCalculator/Coordinator/quit
       {
         this.quit ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ggTCalculator/Coordinator:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Coordinator _this() 
  {
    return CoordinatorHelper.narrow(
    super._this_object());
  }

  public Coordinator _this(org.omg.CORBA.ORB orb) 
  {
    return CoordinatorHelper.narrow(
    super._this_object(orb));
  }


} // class CoordinatorPOA
