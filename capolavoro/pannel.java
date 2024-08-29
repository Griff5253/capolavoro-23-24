import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class pannel extends JPanel {
    private JTextArea testoare;
    private JButton cerca;
    private JTextField casella;
    public String testdacerc;

    public pannel() {

        cerca = new JButton(" cerca ");

        cerca.setFont(new Font("arial", Font.ROMAN_BASELINE, 14));
        cerca.setForeground(Color.black);

        casella = new JTextField(10);
        testoare = new JTextArea(22, 50);
        casella.setBorder(BorderFactory.createLineBorder(Color.black));
        casella.setBackground(Color.lightGray);
        testoare.setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(cerca);
        add(casella);
        add(testoare);

        setBackground(Color.gray);

        cerca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                testdacerc = casella.getText();
                casella.setText("");

                
                cercaNelFile(testdacerc);
            }
        });
    }

    private void cercaNelFile(String testdacerc) {
        String percorsoFile = "output.txt"; 

       
        testoare.setText("");

        try (BufferedReader br = new BufferedReader(new FileReader(percorsoFile))) {
            String linea;
            boolean trovato = false;

       
            while ((linea = br.readLine()) != null) {
                
                String[] parti = linea.split(";");

                
                if (parti.length == 4) {
                    String articolo = parti[0];
                    String posizione = parti[1];
                    String numero = parti[2];
                    String descrizione = parti[3];

            
                    if (articolo.equalsIgnoreCase(testdacerc)) {
                       
                        testoare.append("ARTICOLO: " + articolo + "\n");
                        testoare.append("POSIZIONE: " + posizione + "\n");
                        testoare.append("NUMERO PRODOTTI: " + numero + "\n");
                        testoare.append("DESCRIZIONE ARTICOLO: " + descrizione + "\n");
                        testoare.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"+"\n");
                        trovato = true;
                    }
                } 
            }

            
            if (!trovato) {
                testoare.append("Nessun risultato trovato per: " + testdacerc + "\n");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            testoare.append("Errore durante la lettura del file.\n");
        }
    }
}
