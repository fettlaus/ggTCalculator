package ggTCalculator;

/**
* ggTCalculator/StarterHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 22:33 Uhr UTC
*/

public final class StarterHolder implements org.omg.CORBA.portable.Streamable
{
  public ggTCalculator.Starter value = null;

  public StarterHolder ()
  {
  }

  public StarterHolder (ggTCalculator.Starter initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = ggTCalculator.StarterHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    ggTCalculator.StarterHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return ggTCalculator.StarterHelper.type ();
  }

}
