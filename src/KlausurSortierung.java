import java.util.*;

import static java.lang.System.*;

public class KlausurSortierung {
    private static final Map<String, List<String>> adjazenzliste = new HashMap<>();
    private static final Map<String, Integer> nodeIndex = new HashMap<>();


    public static void main(String[] args) {


        Scanner scanner = new Scanner(in);
        StringBuilder Aufgabee = new StringBuilder();
        int V = 0;

        out.println("Eingabe der Aufgaben:");
        while (scanner.hasNextLine()) {
            String scann = scanner.nextLine().toUpperCase();
            if (scann.isEmpty()) {
                break;
            }
            Graph(scann);

        }

        int length = adjazenzliste.size();
        //printAdjazenzliste();
        out.println(adjazenzliste);

        System.out.println("Lösung:"+ Topological_Sort(length, adjazenzliste));

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


    public static List<String> Topological_Sort(int length, Map<String, List<String>> adjazenzliste) {

        int indegree[] = new int[length];
        HashMap<String, Integer> umwandler = new HashMap<>(adjazenzliste.size());
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
                    indegree[umwandler.get(nachber)]++;
                }
            }

        }


        Queue<String > Queue = new LinkedList<String>(); ;
            for (int i = 0; i < length; i++) {
                if(indegree[i] == 0) {
                    Queue.add(rückwandler[i]);
                }
            }


        List<String> sortierung = new ArrayList<>(length);
        int i = 0;
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

        //if (sortierung.size() != length) {
          //  throw new IllegalStateException("Es gibt einen Zyklus im Graphen.");
        //}


        return sortierung;


    }
}


