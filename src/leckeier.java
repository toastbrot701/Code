import java.util.*;

import static java.lang.System.*;

public class leckeier {

    private static final Map<String, List<String>> adjazenzliste = new HashMap<>();
    private static int length;
    private static boolean[] besucht;
    private static Map<String, Integer> umwandler2 = new HashMap<>();
    private static final Map<String, String> vorheriger_Knoten = new HashMap<>();
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        StringBuilder Aufgabee = new StringBuilder();
        out.println("Eingabe der Aufgaben:");

        while (scanner.hasNextLine()) {
            String scann = scanner.nextLine().toUpperCase();
            if (scann.isEmpty()) {
                break;
            }
            Graph(scann);
        }

        visited = new boolean[length];
        length = adjazenzliste.size();

        Set<String> Besuchte_Knoten = new HashSet<>();
        System.out.println("Geben sie bitte die einfachste Aufgabe ein:");
        String scanner2 = scanner.nextLine().toUpperCase();
        out.println("Lädt...");
        visitNode(scanner2, Besuchte_Knoten);

        out.println("Geben sie bitte die schwerste Aufgabe ein");
        String scanner3 = scanner.nextLine().toUpperCase();
        List<String> Lösung = Topological_Sort();
        VerschiebeLetztenKnoten(Lösung, scanner3);
        out.println("Lösung" + Lösung);
    }

    private static void Graph(String aufgaben){
        String[] knoten = aufgaben.split(" < ");
        for (int i = 0; i < knoten.length - 1; i++) {
            String eingang = knoten[i].trim();
            String ausgang = knoten[i + 1].trim();

            adjazenzliste.putIfAbsent(eingang, new ArrayList<>());
            adjazenzliste.putIfAbsent(ausgang, new ArrayList<>());
            adjazenzliste.get(eingang).add(ausgang);
        }
    }

    // DFS mit Zyklusprüfung
    private static boolean dfs(String knoten, Set<String> besuchte_knoten, Set<String> aktuellerPfad) {
        if (aktuellerPfad.contains(knoten)) {
            // Zyklus erkannt, Kante entfernen
            String vorheriger_knoten = vorheriger_Knoten.get(knoten);
            if (vorheriger_knoten != null) {
                List<String> nachbarn = adjazenzliste.getOrDefault(vorheriger_knoten, new ArrayList<>());
                nachbarn.remove(knoten); // Entfernt die Kante, die den Zyklus verursacht hat
            }
            return true; // Zyklus gefunden
        }

        if (besuchte_knoten.contains(knoten)) {
            return false; // Knoten wurde bereits bearbeitet
        }

        aktuellerPfad.add(knoten); // Knoten zum aktuellen Pfad hinzufügen
        List<String> nachbarn = adjazenzliste.getOrDefault(knoten, new ArrayList<>());
        besuchte_knoten.add(knoten);

        // Rekursive DFS-Aufrufe
        for (String nachbar : nachbarn) {
            if (dfs(nachbar, besuchte_knoten, aktuellerPfad)) {
                return true; // Wenn Zyklus gefunden, abbrechen
            }
        }

        aktuellerPfad.remove(knoten); // Knoten aus dem aktuellen Pfad entfernen, wenn alle Nachbarn bearbeitet sind
        return false;
    }

    private static void visitNode(String Knoten, Set<String> besuchte_knoten) {
        Set<String> aktuellerPfad = new HashSet<>(); // Hilfsstruktur für DFS
        dfs(Knoten, besuchte_knoten, aktuellerPfad); // Zyklusentfernung mittels DFS
    }

    // Topologische Sortierung (bereits im Originalcode)
    public static List<String> Topological_Sort() {
        int[] indegree = new int[length];
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
                for (String nachber : nachbarn) {
                    indegree[umwandler.get(nachber)]++;
                }
            }
        }

        Queue<String> Queue = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            if (indegree[i] == 0) {
                Queue.add(rückwandler[i]);
            }
        }

        List<String> sortierung = new ArrayList<>();
        while (!Queue.isEmpty()) {
            String knoten = Queue.poll();
            sortierung.add(knoten);
            List<String> nachbar_knoten = adjazenzliste.get(knoten);
            if (nachbar_knoten != null) {
                for (String nachber : nachbar_knoten) {
                    indegree[umwandler.get(nachber)]--;
                    if (indegree[umwandler.get(nachber)] == 0) {
                        Queue.add(nachber);
                    }
                }
            }
        }

        return sortierung;
    }

    private static void VerschiebeLetztenKnoten(List<String> lösung, String knoten) {
        if (lösung.contains(knoten)) {
            lösung.remove(knoten);
            lösung.add(knoten);
        }
    }

    // Weitere Hilfsmethoden (falls benötigt)
}
