import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Texthopsen {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder text = new StringBuilder();
        //System.out.println("Markiere das Ende deines Textes bitte mit einem END");
        System.out.println("Text in Hopsitext umwandeln:");
        while(scanner.hasNextLine()){
            String scann = scanner.nextLine().toLowerCase();
                //System.out.println(scann);
            text.append(scann);
            if (scann.isEmpty()) {
                break;
            }


        }
        String fertiger_text = text.toString();
        String a = fertiger_text.replaceAll("\\s+","");
        String b = a.replaceAll(",", "");
        String c = b.replaceAll("\\.", "");
        String d = c.replaceAll("\n", ""); //Text wird optimiert damit er von der Methode besser gelesn werden kann
        String e = d.replaceAll("‘", ""); // Kommas, Zeilenumbrüche und Leerzeichen sollen dabei nicht mitgezählt werden
        String f = e.replaceAll("‚", "");
        String Hopsitext = f.replaceAll("''", "");
        //System.out.println(Hopsitext);
        System.out.print("Der Hopsitext lautet: " + create_Hopsitext(Hopsitext, 0));// Position 1


    }

    // Methode zur Erstellung eines Hopsitextes
    public static String create_Hopsitext(String text, int position) {

        StringBuilder new_text = new StringBuilder();
        while (position >= 0 && position < text.length()) {//

            //Position der Buchstaben
            //System.out.println(" Buchstabe an Position " + position + ": " + text.charAt(position));
            char aktueller_buchstabe = text.charAt(position);

            //Sprungweite und currentposition
            char currentposition  = text.charAt(position);
            int Jumpdistance = Texthopsen(currentposition);
            //Sprungweite ausgeben
            //System.out.println("Jump distance: " + Jumpdistance);
            //neue Position
            int new_position = position + Jumpdistance;

            if (new_position >= text.length()) {
                //System.out.println("Ende des Textes erreicht oder außerhalb des Textes gesprungen.");
                break; // Rückgabe der Position, wenn das Ende des Textes erreicht ist
            }

            //Aktualisieren der Position bzw. Textes
            position = new_position;
            new_text.append(aktueller_buchstabe);



        }
        return new_text.toString();

    }


    // Funktion zur Berechnung der Sprungweite eines Buchstabens
    public static int Texthopsen(char c) {

        if (c >= 'a' && c <= 'z') {
            int Sprungweite = c - 'a' + 1; //Sprungweite wird über Asci Code berechnet
            // (jeder Buchstabe hat dabei betimmten Wert sodass die Differnez zwischen den Werten berechnet werden kann)
            return Sprungweite; //

        } //else if (c == ' ') {
            //return ;
        //}

        switch (c) {
            case 'ä': return 26; //ausnahmen für Sonderzeichen diesen wird ein Extra Wert zugewiesen
            case 'ö': return 27;
            case 'ü': return 28;
            case 'ß': return 29;
            default: return 0; // alles andere wird übersprungen
        }
    }



}





