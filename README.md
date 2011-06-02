ggT-Berechnung
==============
Wir werden fuer Aufgabe 2 der Vorlesung _Verteilte Systeme_ eine verteilte ggT-Berechnung in Java implementieren. Die Verbindung der Komponenten erfolgt mithilfe von Corba.

IDL-Datei
=========
**Stand: 02.06.2011 18:56**

module ggTCalculator
{

    interface Starter
    {
        void createProcess(in long count);
        void quitProcess();
        void quit();
    };

    interface Log
    {
        void log( in string user, in string msg );
    };

    interface Process
    {
        attribute Process left;
        attribute Process right;
        attribute long id;
        attribute long number;
        attribute Log log;
        attribute double delay;
        attribute long timeout;
        attribute boolean ready;
        boolean terminate();
        void message(in long number);
        void stop();
    };

    interface Coordinator
    {
        exception noStarter{ string s;};
        exception alreadyRunning{string s;};

        void addStarter( in string startername, in Starter starter );
        void addProcess( in string startername, in long id, in Process process );

        void calculate( in long timeout, in long mindelay, in long maxdelay, in long minprocess, in long maxprocess, in long ggT, in Log log ) raises(noStarter,
        alreadyRunning);
        void finished(in long r);

        boolean terminationStart();
        boolean terminationStop();
        void quit();
    };
};