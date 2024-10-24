import java.util.*;

public class Wandertag {
    public static class Mitglied {

        private int max_streckenlänge;
        private int min_streckenlänge;

        public Mitglied(int min_streckenlänge, int max_streckenlänge) {
            this.min_streckenlänge = min_streckenlänge;
            this.max_streckenlänge = max_streckenlänge;
        }

        public int getMax_strecke() {
            return max_streckenlänge;

        }

        public int getMin_strecken() {
            return min_streckenlänge;
        }

        public boolean nimmtteil(long strecke) {
            return strecke >= min_streckenlänge && strecke <= max_streckenlänge;
        }

    }

    private List<Mitglied> Mitgliederliste;

    public Wandertag() {
        this.Mitgliederliste = new ArrayList<>();
    }

    public void neues_Mitglied(Mitglied mitglied) {
        Mitgliederliste.add(mitglied);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Maximale und Minimale Längen der Strecken für die Schüler angeben:");
        Wandertag wandertag = new Wandertag();
        List<Long> Zahlen = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            String[] words = line.split(" ");
            if (words.length == 2) {
                int min = Integer.parseInt(words[0]);
                int max = Integer.parseInt(words[1]);

                wandertag.neues_Mitglied(new Mitglied(min, max));
                Zahlen.add(Long.parseLong(words[0]));
                Zahlen.add(Long.parseLong(words[1]));

            } else if (words.length == 1) {
                int min = Integer.parseInt(words[0]);
                wandertag.neues_Mitglied(new Mitglied(min,Integer.MAX_VALUE));
                Zahlen.add(Long.parseLong(words[0]));


            }

        }
        //wandertag.add_Zahlen();
        scanner.close();
        wandertag.optimale_Strecke(Zahlen);

    }

    public void optimale_Strecke(List<Long> Zahl){
        Set<Long> alle_strecken = new HashSet<>(Zahl);
        //long max_strecke = Collections.max(Zahl);
        long optimale_strecke = 0;
        long max_Teilnehmer = 0;
        List<Long> Optimale_Strecken = new ArrayList<>();
        int j = 0;


        for(int i = 0; i < 3; i++){
            for(long best_strecke : alle_strecken){
                long anzahlteilnehmer = 0;
                for (Mitglied mitglied : Mitgliederliste) {
                    if (mitglied.nimmtteil(best_strecke)) {
                        anzahlteilnehmer++;
                    }
                    if (anzahlteilnehmer > max_Teilnehmer) {
                        max_Teilnehmer = anzahlteilnehmer;
                        optimale_strecke = best_strecke;
                        alle_strecken.clear();
                        optimale_Strecke(alle_strecken.remove(optimale_strecke));

                    }

                }

            }
            Optimale_Strecken.add(optimale_strecke);

            System.out.println("Die optiamel Streckenlänge ist "+Optimale_Strecken+" mit "+max_Teilnehmer+" Teilnehmern");
        }



    }


}