import java.util.Scanner;


public class Krocket {
    public static int[][]koordinates;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String zeile = in.nextLine();
        String s[] = zeile.split(" ");
        int tore = Integer.parseInt(s[0]);
        int radius = Integer.parseInt(s[1]);
        int koordinaten[][] = new int[4][tore];

        for (int i=0; i<tore; i++){
            zeile = in.nextLine();
            String l[] = zeile.split(" ");
            koordinaten[0][i] = Integer.parseInt(l[0]);
            koordinaten[1][i] = Integer.parseInt(l[1]);
            koordinaten[2][i] = Integer.parseInt(l[2]);
            koordinaten[3][i] = Integer.parseInt(l[3]);
        }
        int index = 0;
        for (int[] koord: koordinaten ){
            koordinates[index++] = koord.clone();

        }

        System.out.println("Es gibt " + tore + " Tore und der Ball hat den Radius " + radius);
        //Durchquerung(koordinaten);
    }
    class Punkt_im_Koordinatensystem{

        public double x,y;
        public Punkt_im_Koordinatensystem(double x, double y){
            this.x=x;
            this.y=y;
        }

    }
    class Bewegung{
        public double dx,dy;
        public Bewegung(double dx,double dy){
            this.dx=dx;
            this.dy=dy;

        }
    }
    class lineare_Funktion{
        double m,t;
        public lineare_Funktion(double m,double t){
            this.m=m;
            this.t=t;
        }
        public double y_Werte (double x){
            return m*x+t;
        }
        public double x_Werte (double y_Werte){
            return (y_Werte-m)*t;
        }

    }
    public static int finde_radius(){
        int max = koordinates[0][0];
        for (int i=0; i<koordinates.length; i++){
            for (int j=0; j<koordinates[i].length; j++) {
                if (koordinates[j][i] > max) {
                    max = koordinates[j][i];
                }
            }
        }
        return max;
    }

    public Punkt_im_Koordinatensystem Parameter_Darstellung(double x, double y, double radius){
        radius = finde_radius();
        double winkel;
        int i = 0
                ;
        for (winkel = 0; winkel <360.00;winkel++){
            double bogenmass = Math.toRadians(winkel);
            y = y+Math.sin(i)*radius;
            x = x+Math.cos(i)*radius;
            i++;
        }

        return new Punkt_im_Koordinatensystem(x,y);


    }

    public void linie_Tore(int[][] koordinates, double y, double x){
        double infiniteValue = Double.POSITIVE_INFINITY;
        for (int i=0; i<koordinates.length; i++){
            for (int j=0; j<koordinates[i].length; j++){
                //int largest = findLargestInRow(matrix, rowIndex);
                if (koordinates[i][0] >  koordinates[i][j]){
                    x = koordinates[i][0]-koordinates[j][0];
                    y = koordinates[i][1]-koordinates[j][1];
                    double m = y/x;
                    new lineare_Funktion( m,1);

                }

            }
        }




    }
    public Punkt_im_Koordinatensystem Differenz_zwischen_Punkten(int[][]koordinates){
        for (int i = 0; i < koordinates.length; i++) {
            for (int j = 0; j < koordinates.length; i++){
                int[] Richtung = new int[]{koordinates[j][0] - koordinates[i][0], koordinates[j][1] - koordinates[i][1]};

            }



        }
        return null;



    }

    public void Durchquerung(int[][] koordinaten){

        for (int i = 0; i < koordinaten.length; i++) {
            for (int j = 0; j < koordinates.length; i++){
                koordinaten[i][j] = koordinates[i][j];

            }



        }




    }
}
