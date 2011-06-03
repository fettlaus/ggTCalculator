ggT-Berechnung
==============
Wir werden fuer Aufgabe 2 der Vorlesung _Verteilte Systeme_ eine verteilte ggT-Berechnung in Java implementieren. Die Verbindung der Komponenten erfolgt mithilfe von Corba.

IDL-Datei
=========
**Stand: 03.06.2011 02:50**

    module ggTCalculator
    {

        interface Starter
        {
            string getName();
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
            boolean terminate();
            void set_params(in Process left, in Process right, in long number, in Log log, in double delay, in long timeout);
            void message(in long number);
            void stop();
        };

        typedef sequence<string> StarterListe;
        interface Coordinator
        {
            exception noStarter{ string s;};
            exception alreadyRunning{string s;};

            void addStarter( in string startername, in Starter starter );
            void addProcess( in string startername, in long id, in Process process );

            StarterListe getStarterList();

            long calculate( in long timeout, in long mindelay, in long maxdelay, in long minprocess, in long maxprocess, in long ggT, in Log log )raises(noStarter, alreadyRunning);
            void finished(in long r);

            boolean terminationStart();
            boolean terminationStop();
            void quit();
        };
    };