package ggTCalculator.CoordinatorPackage;

/**
* ggTCalculator/CoordinatorPackage/noStarterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 17:35 Uhr UTC
*/

public final class noStarterHolder implements org.omg.CORBA.portable.Streamable
{
  public ggTCalculator.CoordinatorPackage.noStarter value = null;

  public noStarterHolder ()
  {
  }

  public noStarterHolder (ggTCalculator.CoordinatorPackage.noStarter initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggTCalculator.CoordinatorPackage.noStarterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggTCalculator.CoordinatorPackage.noStarterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggTCalculator.CoordinatorPackage.noStarterHelper.type ();
  }

}
