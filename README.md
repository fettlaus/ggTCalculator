ggT-Berechnung
==============
Wir werden fuer Aufgabe 2 der Vorlesung _Verteilte Systeme_ eine verteilte ggT-Berechnung in Java implementieren. Die Verbindung der Komponenten erfolgt mithilfe von Corba.

    Starter [NameServiceHost [NameServicePort [KoordinatorName [StarterName]]]]
    Coordinator [NameServiceHost [NameServicePort [KoordinatorName]]]
    Client NameServiceHost NameServicePort KoordinatorName <list | quit | calc minProcess maxProcess mindelay maxDelay timeout ggT>

Hintergrundwissen
=================

Verwendet wird einer der &auml;ltesten, bekannten Algorithmen: Euklids Algorithmus zur Bestimmung des ggT, beschrieben im 7. Buch der Elemente um ca. 300 v.Chr. Dies erm&ouml;glicht uns, den Fokus auf die Koordinierung eines verteilten Algorithmus zu lenken, weil der Algorithmus selbst recht leicht zu verstehen ist.

> Satz von Euklid:
> ----------------
> Der gr&ouml;&szlig;te gemeinsame Teiler (ggT) zweier positiver ganzer Zahlen x, y (mit x >=y > 0) ist gleich dem ggT von y und dem Rest, der bei ganzzahliger Division von x durch y entsteht.

Bemerkung
---------

* Offenbar ist ggT(x,x) = x f&uuml;r alle x
* Man setzt nun noch ggT(x,0) := x f&uuml;r alle x
* Rekursive Realisierung: ggT(x,y) := ggT(y,mod(x,y))
* Erweiterung: mod*(x,y) := mod(x-1,y)+1
* Die Rekursion wird durch das Versenden von Nachrichten realisiert: es wird der gleiche Code aufgerufen!

Weitere Infos finden Sie z. B. [hier.](http://www.vs.inf.ethz.ch/edu/WS0405/VA/slides/VA0405_01.pdf)

Verteilter Algorithmus
----------------------

1. Es werden n Prozesse verwendet. Die Prozesse werden in einem Ring organisiert, so dass der Prozess Pi die zwei Nachbarn Pi+1 und Pi-1 hat.
2. Jeder Prozess Pi hat seine eigene Variable Mi mit Wert #Mi
3. ggT aller am Anfang bestehender Mi wird berechnet:

        {Eine Nachricht mit der Zahl #y ist eingetroffen}
        if y < Mi
           then Mi := mod(Mi-1,y) + 1;
                send #Mi to all neighbours;
        fi
    
Aufgabenstellung
================
Das System f&uuml;r den verteilten Algorithmus ist so ausgelegt, dass es f&uuml;r eine l&auml;ngere Zeit installiert wird und dann f&uuml;r mehrere Aufgaben (also ggT-Berechnungen) zur Verf&uuml;gung steht. F&uuml;r die Implementierung werden im Wesentlichen vier Module ben&ouml;tigt:

1. Der **Koordinator**, der den verteilten Algorithmus verwaltet. Dazu geh&ouml;ren das "Hochfahren" des Systems, die Koordinierung einer ggT-Berechnung, die Feststellung der Terminierung einer ggT-Berechnung und das "Herunterfahren" des Systems.
2. Den **Starter**, der im Wesentlichen das Starten von Prozessen auf f&uuml;r den Koordinator entfernten Rechnern erm&ouml;glicht.
3. Der **ggT-Prozess**, der die eigentliche Arbeit leistet, also die Berechnung des ggT.
4. Der **Client**, der die ben&ouml;tigten steuernden Werte setzt, die Berechnung des ggT startet, den Ablauf beobachtet und protokolliert und das Ergebnis anzeigt.

Am Anfang wird das System hochgefahren. Dazu ist als erstes ein Namensdienst zu starten. Dann wird der Koordinator gestartet und anschlie&szlig;end auf verschiedenen Rechnern die Starter. Die Starter melden sich beim Koordinator an und warten nun auf Kommandos vom Koordinator. Der Koordinator selber wartet auf Befehle vom Client.

Das System geht in die **Arbeitsphase**: Der Client wird gestartet und &uuml;bergibt dem Koordinator die gew&uuml;nschten ggT-Parameter. Daraufhin informiert der Koordinator die Starter &uuml;ber die Anzahl der zu startenden ggT-Prozesse. Die Prozesse werden vom Starter gestartet. W&auml;hrend der Initialisierung der Prozesse melden sie sich beim Koordinator an. Dieser baut einen Ring auf und informiert die ggT-Prozesse &uuml;ber ihre Nachbarn und ihre ihre Werte #Mi. Damit wechseln die ggT-Prozesse in den Zustand "bereit".

Der Koordinator w&auml;hlt 3 ggT-Prozesse per Zufall aus, die mit der Berechnung beginnen sollen. Erh&auml;lt ein ggT-Prozess im Zustand "bereit" eine Zahl, so versetzt er sich in den Zustand "rechnen". Meldet ein ggT-Prozess die Terminierung der aktuellen Berechnung, so erh&auml;lt der Koordinator gleichzeitig von ihm das Endergebnis der Berechnung. Der ggT-Prozess versetzt sich in den Zustand "beendet".

Erh&auml;lt w&auml;hrend eines Laufs des Algorithmus (Zustand "rechnen") ein ggT-Prozess \*\* Sekunden lang keine Zahl y, startet er eine **Terminierungsabstimmung**: Dazu befragt er seinen rechten Nachbarn, ob dieser bereit ist, zu terminieren. Antwortet dieser mit Ja, sendet er dem Koordinator eine entsprechende Mitteilung &uuml;ber die Terminierung des Algorithmus und versetzt sich in den Zustand "beendet". Wird er selbst in diesem Stadium, also als Initiator einer Abstimmung, von seinem linken Nachbarn gefragt, ob er terminieren kann, beantwortet er dies mit Ja. Wird er w&auml;hrend seiner normalen Arbeitsphase, also wenn er nicht Initiator einer Abstimmung ist, nach seiner Bereitschaft zur Terminierung gefragt, antwortet er mit Nein, wenn seit der letzten Zusendung einer Zahl weniger als **/2 Sekunden vergangen sind. Ansonsten leitet er die Frage weiter an seinen rechten Nachbarn und leitet die zugeh&ouml;rige Antwort zur&uuml;ck an seinen linken Nachbarn. Ist die erhaltene Antwort Ja versetzt er sich in den Zustand "beendet". Wenn alle Prozesse die Terminierung an den Koordinator gemeldet haben, soll der Koordinator die Beendigung der ggT-Prozesse veranlassen und das Endergebnis an den Client zur&uuml;ckgeben. Der Client kann sich nun beenden. Das System ist jetzt bereit, eine neue Berechnung durchzuf&uuml;hren.

Der Client kann &uuml;ber den Koordinator die Beendigung des Systems anstossen. Die Starter werden vom Koordinator &uuml;ber die Beendigung des Systems informiert. Falls noch ggT-Prozesse laufen, sollen diese unabh&auml;ngig von ihrem momentanen Zustand unverz&uuml;glich beendet werden. Anschlie&szlig;end beenden sich die Starter und zum Schluss der Koordinator.

Funktionalit&auml;t
--------------

**Client**

1. Der Client kann beim Koordinator drei Aktionen ausf&uuml;hren: Abfrage der bekannten Starter, Ausf&uuml;hren der ggT-Berechnung und Beendigung des Systems
2. Die f&uuml;r die ggT-Berechnung ben&ouml;tigten steuernden Werte sollen &uuml;ber die Kommandozeile eingegeben werden. Die Werte sind:
    * der Name des Koordinators
    * ein Intervall f&uuml;r die Anzahl der ggT-Prozesse auf einem Rechner,
    * ein Intervall f&uuml;r die Verz&ouml;gerungszeit der ggT-Prozesse,
    * ein Wert f&uuml;r den ** timeout und
    * der gew&uuml;nschte ggT.
3. Der Client ermittelt den Koordinator &uuml;ber den Corba-Namensdienst unter dem per Kommandozeile vorgegebenen Namen.
4. Der Client soll die Abl&auml;ufe w&auml;hrend der Berechnung protokollieren. Hierzu ist dem Koordinator eine entsprechende Referenz zu &uuml;bergeben, die dieser an die einzelnen Prozesse weiterleiten muss.

**Koordinator**

1. Der Koordinator ist unter einem &uuml;ber die Kommandozeile vorgebbaren Namen beim CORBA-Namensdienst registriert.
2. Die ben&ouml;tigten steuernden Werte erh&auml;lt der Koordinator mit dem Aufruf durch den Client.
3. Nach dem Starten des Koordinators wartet er auf Kommandos vom Client.
4. Verlangt der Client eine ggT-Berechnung, so beauftragt der Koordinator seine Starter mit dem Anlegen der ggT-Prozesse. Die Anzahl der Prozesse wird f&uuml;r jeden Starter mit Hilfe einer Zufallsfunktion aus dem vom Client vorgegebenen Intervall bestimmt.
5. Die Prozesse melden sich beim Koordinator an. Sie identifizieren sich mit dem Namen des Starters und durch eine fortlaufende Nummer, die durch den Starter vergeben wird. Der Koordinator erstellt daraus eine Liste.
6. Nachdem die Prozesse angelegt worden sind, baut der Koordinator den Ring auf, indem er per Zufall die ggT-Prozesse ausw&auml;hlt. Jedem ggT-Prozess wird &uuml;bermittelt, welche Nachbarn er besitzt. Gleichzeitig bekommen sie die f&uuml;r die Berechnungen notwendigen Informationen:
    * Startwert f&uuml;r die ggT-Berechnung:
    * gew&uuml;nschter_ggT * Zufallszahl_1_bis_100 * Zufallszahl_1_bis_100.
    * Verz&ouml;gerungszeit des ggT-Prozesses, ermittelt als Zufallszahl aus dem vom Client &uuml;bergebenen Intervall.
    * \*\*-timeout.
    * Referenz des Log-Objektes vom Client zum Protokollieren des Ablaufs.
7. Anschlie&szlig;end startet der Koordinator die Berechnung. Dazu w&auml;hlt er per Zufall drei Prozesse aus, denen er zum Starten der Berechnung eine Zahl sendet, die sich wie folgt berechnet: 
   gew&uuml;nschter_ggT * Zufallszahl_100_bis_10000.
8. Nach Terminierung der Berechnung &uuml;bermittelt der Koordinator das Ergebnis an den Clienten und beauftragt die Starter mit der Beendigung der Prozesse.
9. Per Aufruf vom Client kann der Koordinator beauftragt werden, dass System zu beenden. Es werden zun&auml;chst die Starter gestoppt, anschlie&szlig;end beendet sich der Koordinator selber.

**Starter**

1. Dem Starter wird per Kommandozeile einen Namen zugewiesen (z.B. Name des Rechners).
2. Der Starter registriert sich beim Koordinator und &uuml;bergibt ihn dabei seinen Namen.
3. Anschlie&szlig;end wartet der Starter auf Kommandos vom Koordinator. F&uuml;r eine Berechnung wird der Starter beauftragt, eine bestimmte Anzahl von ggT-Prozessen zu starten. Die Prozesse bekommen eine laufende Nummer zugeordnet, unter der sie sich beim Koordinator bekannt machen k&ouml;nnen.
4. Bekommt der Starter den Auftrag, sich zu beenden, so muss er zun&auml;chst seine eventuell noch vorhandenen Prozesse stoppen und kann sich danach selber beenden.

**ggT-Prozess**

1. Der ggT-Prozess wird vom Starter gestartet und muss sich als erstes mit dem Namen des Starters und der vom Starter vergebenen fortlaufenden Nummer beim Koordinator registrieren.
2. Danach wartet der Prozess auf die Parametrisierung durch den Koordinator. Anschlie&szlig;end ist der Prozess "bereit" und kann auf Berechnungsanforderungen reagieren. Diese k&ouml;nnen initial vom Koordinator oder von den Nachbarn eintreffen.
3. &Auml;ndert sich seine Zahl dadurch (also hat er echt etwas berechnet), informiert er zus&auml;tzlich das Log-Objekt des Client dar&uuml;ber, indem er diesem seine Identifizierung, seine neue Zahl und die aktuelle Systemzeit &uuml;bertr&auml;gt.
4. F&uuml;r eine Berechnung braucht er jedoch eine gewisse Zeit (die Verz&ouml;gerungszeit), die ihm vom Koordinator bei der Parametrisierung mitgegeben wurde. Dies simuliert eine gr&ouml;&szlig;ere, zeitintensivere Aufgabe. Der ggT-Prozess kann z.B. in dieser Zeit einfach nichts tun.
5. Der ggT-Prozess beobachtet die Zeit seit dem letzten Empfang einer Zahl. Hat diese ** Sekunden &uuml;berschritten, startet er eine Terminierungsanfrage.
6. Die Abstimmung arbeitet wie folgt:
    1. Ein ggT-Prozess erh&auml;lt die Anfrage nach der Terminierung: ist seit dem letzten Empfang einer Zahl mehr als **/2 (** halbe) Sekunden vergangen, dann leitet er die Anfrage an seinen rechten Nachbarn weiter und gibt dessen Antwort zur&uuml;ck an seinen linken Nachbarn. Ist die zugeh&ouml;rige Antwort Ja, versetzt er sich in den Zustand "bereit". Sonst antwortet er seinem linken Nachbarn direkt mit Nein. 
    2. Erh&auml;lt ein initiierende Prozess von seinem linken Nachbarn die Anfrage nach der Terminierung, antwortet er diesem direkt mit Ja.
7. Ist Terminierungsanfrage erfolgreich durchgef&uuml;hrt, sendet der Prozess dem Koordinator eine Mitteilung &uuml;ber die Terminierung der aktuellen Berechnung. Zus&auml;tzlich ist das Log-Objekt des Client &uuml;ber die Terminierung zu informieren. Dabei ist die Identifizierung, der errechnete ggT und die aktuelle Systemzeit zu &uuml;bergeben.

**Fehlerbehandlung**

1. Alle auftretenden Fehler sollen nach M&ouml;glichkeit dem Log-Objekt des Clienten &uuml;bermittelt werden

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