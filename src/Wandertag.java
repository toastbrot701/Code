import com.sun.jdi.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wandertag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Maximale und Minimale Längen der Streckén für die Schüler angeben:");
        List<int[]> Daten = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] words = line.split(" ");
            if(words.length == 4) {
                int minimum = Integer.parseInt(words[0]);
                int maximum = Integer.parseInt(words[1]);
                Daten.add(new int[]{minimum, maximum});
            }
            if(line.isEmpty()) {
                break;
            }



        }
        for (int[] pair : Daten) {
            System.out.println(pair[0] + " " + pair[1]);
        }


    }
    public static int längen(int n1, int n2) {
        int differenz = n1 -n2;
        return differenz;
    }
}
