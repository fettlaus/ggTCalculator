package ggTCalculator.CoordinatorPackage;


/**
* ggTCalculator/CoordinatorPackage/noStarter.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 18:26 Uhr UTC
*/

public final class noStarter extends org.omg.CORBA.UserException
{
  public String s = null;

  public noStarter ()
  {
    super(noStarterHelper.id());
  } // ctor

  public noStarter (String _s)
  {
    super(noStarterHelper.id());
    s = _s;
  } // ctor


  public noStarter (String $reason, String _s)
  {
    super(noStarterHelper.id() + "  " + $reason);
    s = _s;
  } // ctor

} // class noStarter
