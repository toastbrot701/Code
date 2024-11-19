import java.util.*;

import static java.lang.System.*;

public class KlausurSortierung {
    private static final Map<String, List<String>> adjazenzliste = new HashMap<>();
    private static int length;
    private static List<String> knotenliste = new ArrayList<>();
    private static ArrayList<Integer> ls = new ArrayList<>();
    private static boolean[] besucht;
    private static Map<String, Integer> umwandler2 = new HashMap<>();



    public static void main(String[] args) {


        Scanner scanner = new Scanner(in);
        StringBuilder Aufgabee = new StringBuilder();
        //int V = 0;

        out.println("Eingabe der Aufgaben:");
        while (scanner.hasNextLine()) {
            String scann = scanner.nextLine().toUpperCase();
            if (scann.isEmpty()) {
                break;
            }
            Graph(scann);

        }




        besucht = new boolean[length];
        length = adjazenzliste.size();

        Set<String> Besuchte_Knoten = new HashSet<>();
        System.out.println("Geben sie bitte die einfachste Aufgabe ein:");
        String scanner2 = scanner.nextLine().toUpperCase();
        out.println("Lädt...");
        visitNode(scanner2, Besuchte_Knoten);
        //printAdjazenzliste();
        out.println("Geben sie bitte die schwerste Aufgabe ein");
        String scanner3 = scanner.nextLine().toUpperCase();
        List<String> Lösung = Topological_Sort();
        VerschiebeLetztenKnoten(Lösung,scanner3);
        out.println("Geben sie ein welche Aufgaben einen neue Anordnung bekommen sollen");
        String scanner4 = scanner.nextLine().toUpperCase();
        //Neue_Anordnung(Lösung,scanner4);

        System.out.println("Lösung" + Lösung);






        //out.println(adjazenzliste);
        //int index= 0;
        //for (String knoten : knotenliste) {
          //  umwandler2.put(knoten, index++);
        //}

        //knotenliste.addAll(adjazenzliste.keySet());


            //throw new RuntimeException("Zyklus entdeckt" );
            //System.out.println("Zykluserkennung");
            //System.out.println(Topological_Sort());

    }

    private static void Graph(String aufgaben){
        String[] knoten = aufgaben.split(" < "); // alle Knoten also z.B A,B,C etc. werden in ein Arry gepackt
        for (int i = 0; i < knoten.length - 1; i++) { //für jeden knoten wird ein ein und Ausgnag definiert
            String eingang = knoten[i].trim();
            String ausgang = knoten[i + 1].trim();

                adjazenzliste.putIfAbsent(eingang,new ArrayList<>());
                adjazenzliste.putIfAbsent(ausgang, new ArrayList<>());
                adjazenzliste.get(eingang).add(ausgang);

        }
    }



    //Topologische Sortierung nach Kahns Algortihmus
    public static List<String> Topological_Sort() {
        int[] indegree;
        indegree = new int[length];
        HashMap<String, Integer> umwandler = new HashMap<>(length);
        String[] rückwandler = new String[length];


        Set<String> keySet = adjazenzliste.keySet();
        int index = 0;


        for (String it : keySet) {
            umwandler.put(it, index);
            rückwandler[index] = it;
            index++;
        }


        for (String j : adjazenzliste.keySet()) {
            List<String> nachbarn = adjazenzliste.get(j);
            if (nachbarn != null) {
                for (String nachber: nachbarn) {
                    indegree[umwandler.get(nachber)]++; //umwandler.get(nachbar) damit wird sich auch den knoten bezogen

                }

            }

        }


        Queue<String > Queue = new LinkedList<String>(); ;
        for (int i = 0; i < length; i++) {
            if(indegree[i] == 0) {
                Queue.add(rückwandler[i]);
            }
        }



        int i = 0;
        List<String> sortierung = new ArrayList<>();
        while(!Queue.isEmpty()){
            String knoten  = Queue.poll();
            sortierung.add(knoten);
            List<String> nachbar_knoten = adjazenzliste.get(knoten);
            if (nachbar_knoten != null) {
                for (String nachber: nachbar_knoten) {
                    indegree[umwandler.get(nachber)]--;
                    if (indegree[umwandler.get(nachber)] == 0) {
                        Queue.add(nachber);
                    }
                }
            }

        }


        return sortierung;

    }

    private static final Map<String, String> vorheriger_Knoten = new HashMap<>();
    //Alle Knoten werden besucht und es wird geschaut ob ein Knoten doppelt vorkommt von diesem soll
    private static void visitNode(String Knoten, Set<String> besuchte_knoten) {
        if (besuchte_knoten.contains(Knoten)) {
            String vorheriger_knoten = vorheriger_Knoten.get(Knoten);

            if(vorheriger_knoten != null) {
                List<String> nachbarn = adjazenzliste.getOrDefault(vorheriger_knoten, new ArrayList<>()); //Nachbarnliste des vorherigen Knotens

                nachbarn.remove(Knoten);//entferne den derzeitigen Knoten aus der Nachbarsliste des vorherigen Knotens

            }
            if (Knoten != null && vorheriger_knoten != null) {
                // Hole die Nachbarn des aktuellen Knotens
                List<String> nachbarnAktueller = adjazenzliste.getOrDefault(Knoten, new ArrayList<>());

                // Entferne den vorherigen Knoten aus der Nachbarnliste des aktuellen Knotens
                nachbarnAktueller.remove(vorheriger_knoten);
            }
            return;

        }
        else {
            besuchte_knoten.add(Knoten); //Knoten wurde noch noch nicht beuscht also wird er in die Besucht Liste getan
            List<String> nachbarn = new ArrayList<>(adjazenzliste.getOrDefault(Knoten, new ArrayList<>()));//Nachbarknoten
            for (String nachber : nachbarn) { //wiederhole den Vorgang für die Nachbarn
                vorheriger_Knoten.put(nachber, Knoten); // der jetzige Knoten wird als der vorherige Knoten das Nachbarn gespeichert
                visitNode(nachber, besuchte_knoten);
            }
        }




    }

    //finde vorherigen Knoten
    public String getvorherigerKnoten(String knoten){//eingabe des jetzigen Knotens
        return vorheriger_Knoten.get(knoten);

    }
    private static void VerschiebeLetztenKnoten(List<String> lösung, String knoten) {
        if (lösung.contains(knoten)) {
            lösung.remove(knoten);  // Entferne den Knoten, falls er in der Liste ist
            lösung.add(knoten);      // Füge ihn ans Ende hinzu
        }
    }
    private static List<String> Neue_Anordnung(List<String> lösung, String knoten) {
        List<List<String>> alte_anordnung = Arrays.asList(lösung);
        List<String> neue_anordnung = new ArrayList<>();
        if (alte_anordnung.contains(knoten)) {
            neue_anordnung.add(knoten);      // Füge ihn ans Ende hinzu
        }
        return neue_anordnung;
    }





}


