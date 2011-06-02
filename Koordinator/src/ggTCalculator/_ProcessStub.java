package ggTCalculator;


/**
* ggTCalculator/_ProcessStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 20:35 Uhr UTC
*/

public class _ProcessStub extends org.omg.CORBA.portable.ObjectImpl implements ggTCalculator.Process
{

  public boolean terminate ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("terminate", true);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return terminate (        );
            } finally {
                _releaseReply ($in);
            }
  } // terminate

  public void set_params (ggTCalculator.Process left, ggTCalculator.Process right, int number, ggTCalculator.Log log, double delay, int timeout)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("set_params", true);
                ggTCalculator.ProcessHelper.write ($out, left);
                ggTCalculator.ProcessHelper.write ($out, right);
                $out.write_long (number);
                ggTCalculator.LogHelper.write ($out, log);
                $out.write_double (delay);
                $out.write_long (timeout);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                set_params (left, right, number, log, delay, timeout        );
            } finally {
                _releaseReply ($in);
            }
  } // set_params

  public void message (int number)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("message", true);
                $out.write_long (number);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                message (number        );
            } finally {
                _releaseReply ($in);
            }
  } // message

  public void stop ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("stop", true);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                stop (        );
            } finally {
                _releaseReply ($in);
            }
  } // stop

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:ggTCalculator/Process:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _ProcessStub
