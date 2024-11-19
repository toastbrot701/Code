import java.util.*;

public class Wandertag {
    public static class Mitglied {

        private final int max_streckenlänge;
        private final int min_streckenlänge;

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

        @Override
        public String toString() {
            return "Min: " + min_streckenlänge + ", Max: " + max_streckenlänge;
        }

    }

    private final List<Mitglied> Mitgliederliste;

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
                wandertag.neues_Mitglied(new Mitglied(0,0));

            }

        }
        //wandertag.add_Zahlen();
        scanner.close();


        List<Long> finaleStrecken = wandertag.optimale_Strecke(Zahlen);
        Set<Wandertag.Mitglied> Gesamtanzahl_Mitglieder = new HashSet<>();
        System.out.println("Die optimalen Strecken mit den Teilnehmeranzahlen sind:");

        for (Long strecke : finaleStrecken) {
            int teilnehmer = wandertag.Anzahl_Teilnehmer(strecke);
            List <Wandertag.Mitglied> Mitglieder_Teilnahme = wandertag.Mitglieder_die_Teilnehmen(strecke);
            System.out.println("Strecke: " + strecke + " km, Anzahl Teilnehmer: " + teilnehmer);
            System.out.println("Teilnehmende Mitglieder: " + Mitglieder_Teilnahme);
            Gesamtanzahl_Mitglieder.addAll(Mitglieder_Teilnahme);
        }

        System.out.println("Die Gesamtanzahl Mitglieder ist: "+Gesamtanzahl_Mitglieder.size());


    }

    public List<Long> optimale_Strecke(List<Long> Zahl) {
        Set<Long> alle_strecken = new HashSet<>(Zahl);
        //long max_strecke = Collections.max(Zahl);

        //List<Long> Optimale_Strecken = new ArrayList<>();
        List<Long> End_Strecken = new ArrayList<>();
        Set<Mitglied> Gleiche_Mitglieder = new HashSet<>();


        while (End_Strecken.size() < 3 && !alle_strecken.isEmpty()) {
            long optimale_strecke = 0;
            long max_Teilnehmer = 0;
            List<Long> Müll = new ArrayList<>();
            for (long best_strecke : alle_strecken) {
                int anzahlteilnehmer = 0;
                for (Mitglied mitglied : Mitgliederliste) {
                    if (mitglied.nimmtteil(best_strecke) && !Gleiche_Mitglieder.contains(mitglied)) {
                        anzahlteilnehmer++;

                    }
                }

                if(anzahlteilnehmer > max_Teilnehmer) {
                    max_Teilnehmer = anzahlteilnehmer;
                    optimale_strecke = best_strecke;
                    //optimale_Strecke(alle_strecken.remove(optimale_strecke));
                }

            }


            End_Strecken.add(optimale_strecke);
            alle_strecken.remove(optimale_strecke);
            Müll.add(optimale_strecke);

            for (Wandertag.Mitglied mitglied : Mitgliederliste) {
                if (mitglied.nimmtteil(optimale_strecke)) {
                    Gleiche_Mitglieder.add(mitglied);
                }
            }


            alle_strecken.removeAll(Müll);

        }
        return End_Strecken;

    }



    public List<Mitglied> Mitglieder_die_Teilnehmen(long Strecken){
        List<Mitglied> Teilnehmer = new ArrayList<>();

        for (Mitglied mitglied : Mitgliederliste) {
            if (mitglied.nimmtteil(Strecken)) {
                Teilnehmer.add(mitglied);

            }
        }

        return Teilnehmer;

    }



    public int Anzahl_Teilnehmer(long Strecken){
        int Teilnehmer_Anzahl = 0;
        for (Mitglied mitglied : Mitgliederliste) {
            if (mitglied.nimmtteil(Strecken)){
                Teilnehmer_Anzahl++;
            }
        }
        return Teilnehmer_Anzahl;
    }




}