import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;

public class Schwierigkeitsabstufungen {

    private Map<String, List<String>> adjazenzliste;




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wählen sie die Aufgaben aus die sie verwenden wollen.");
        System.out.println("Folgende Aufgaben stehen zu Verfügung: A,B,C,D,E,F,G");
        String g = scanner.nextLine().toLowerCase();
        List<String> Aufgaben2 = new ArrayList();
        Aufgaben2.add(g);
        List<Character> Aufgaben = new ArrayList();
        for (String s : Aufgaben2)
            for (char c : s.toCharArray())
                Aufgaben.add(c);
        {

        }
        //adjazenzliste
        Schwierigkeitsabstufungen Graf = new Schwierigkeitsabstufungen();

        for(String Eingaben : Aufgaben2) {
            String[] Knoten = Eingaben.split(" < ");
            for (int i = 0; i < Knoten.length-1 ; i++) {
                String eingang = Knoten[i].trim();
                String Ausgang = Knoten[i + 1].trim();
                Graf.addKanten(eingang, Ausgang);
                Graf.addKnoten(eingang);
                Graf.addKnoten(Ausgang);
            }
        }


        System.out.println("Graf"+Graf);

        //String[] teile = Aufgaben.split(", ");
        //String[] Kanten = Arrays.copyOfRange(teile, 0, teile.length - 1);
        //String[] Knoten = teile [teile.length - 1].split(" ");




        //dgree bestimmen
        int n = Aufgaben.size();
        double []in_dgree = new double[n]; // leere in_degree liste
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                in_dgree[i] = Aufgaben.get(i);

            }


        //Aufgaben.replaceAll("\\s+","");
        // Zuordnung von char zu int
        //indexwerte.put('b', 0);


        //Ordnen der Aufgaben mithilfe einer Hash Map
        List<Character> geordnete_Aufgaben = new ArrayList<>();
        List<Character> zuSortieren = new ArrayList<>(Aufgaben);
        HashMap<Character, Integer> indexwerte = new HashMap<>();
        indexwerte.put(Aufgaben.get(0), 0);


        for (Character c : Aufgaben) {
            for (int i = 0; i < Aufgaben.size(); i++) {
                if (Aufgaben.contains('<')){
                    for (char buchstabe : Aufgaben) {
                        char nextbuchstabe = nächsterbuchstabe(buchstabe);
//                        indexwerte.put(nextbuchstabe, indexwerte.get(nextbuchstabe) + 1);
                        System.out.println(indexwerte);

                    }

                }//else if (Aufgaben.contains('')){}
            }

        }

        zuSortieren.sort((c1, c2) -> {
            int index1 = indexwerte.getOrDefault(c1, Integer.MAX_VALUE);
            int index2 = indexwerte.getOrDefault(c2, Integer.MAX_VALUE);
            return Integer.compare(index1, index2);
        });


        for (char c : zuSortieren) {
            if (c >= 'a' && c <= 'g'){
                if (indexwerte.containsKey(c)) {
                    geordnete_Aufgaben.add(c);
                }

            }else {
                System.out.println("Fehlercode: Eine der von ihnen angegebenen Aufgaben exsistiert nicht");
            }


        }


        System.out.println("Aufgabe: " + Aufgaben);
        System.out.println("Aufgabe: " + geordnete_Aufgaben);
        //Schwierigkeitsabstufungen(Aufgaben);

    }
    public void nix(){



    }

    public static char nächsterbuchstabe(char buchstabe){
        return (char) (buchstabe + 1);

    }

    public Schwierigkeitsabstufungen() {
        adjazenzliste = new HashMap<>();

    }
    public void addKnoten(String knoten) {
        adjazenzliste.putIfAbsent(knoten, new ArrayList<>());
    }

    // Kante zum Graphen hinzufügen
    public void addKanten(String Eingang, String Ausgang) {
        adjazenzliste.putIfAbsent(Eingang, new ArrayList<>());
        adjazenzliste.putIfAbsent(Ausgang, new ArrayList<>());
        adjazenzliste.get(Eingang).add(Ausgang);
    }


}

