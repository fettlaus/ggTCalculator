package ggTCalculator.CoordinatorPackage;


/**
* ggTCalculator/CoordinatorPackage/alreadyRunning.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 22:33 Uhr UTC
*/

public final class alreadyRunning extends org.omg.CORBA.UserException
{
  public String s = null;

  public alreadyRunning ()
  {
    super(alreadyRunningHelper.id());
  } // ctor

  public alreadyRunning (String _s)
  {
    super(alreadyRunningHelper.id());
    s = _s;
  } // ctor


  public alreadyRunning (String $reason, String _s)
  {
    super(alreadyRunningHelper.id() + "  " + $reason);
    s = _s;
  } // ctor

} // class alreadyRunning
