package ggTCalculator;


/**
* ggTCalculator/StarterListeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 20:35 Uhr UTC
*/

public final class StarterListeHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public StarterListeHolder ()
  {
  }

  public StarterListeHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggTCalculator.StarterListeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggTCalculator.StarterListeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggTCalculator.StarterListeHelper.type ();
  }

}
