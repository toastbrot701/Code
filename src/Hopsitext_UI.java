import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Hopsitext_UI  {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gib einen Text ein:");
        String text = scanner.nextLine().toLowerCase(); // Um Text in Kleinbuchstaben zu konvertieren
        System.out.println(Texthopsen.create_Hopsitext(text, 0));

        Hopsitext_UI hopsitext = new Hopsitext_UI();


    }



    public void Text_erstellen(String neuer_text){
        System.out.println(neuer_text);

    }
    public static String texteinlesen(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            scanner.nextLine().toLowerCase();

        }


        return texteinlesen();



    }
}
