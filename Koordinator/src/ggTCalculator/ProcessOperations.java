package ggTCalculator;


/**
* ggTCalculator/ProcessOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from ggTCalculator.idl
* Donnerstag, 2. Juni 2011 17:35 Uhr UTC
*/

public interface ProcessOperations 
{
  ggTCalculator.Process left ();
  void left (ggTCalculator.Process newLeft);
  ggTCalculator.Process right ();
  void right (ggTCalculator.Process newRight);
  int number ();
  void number (int newNumber);
  ggTCalculator.Log log ();
  void log (ggTCalculator.Log newLog);
  double delay ();
  void delay (double newDelay);
  int timeout ();
  void timeout (int newTimeout);
  boolean ready ();
  void ready (boolean newReady);
  boolean terminate ();
  void message (int number);
  void stop ();
} // interface ProcessOperations
