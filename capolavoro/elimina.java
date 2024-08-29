import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class elimina extends JPanel {
    private JTextField ogg;
    private JButton butt;
    private JTextArea art;
    private JTextField num;
    private JTextArea numart;
    
    public elimina() {
        ogg = new JTextField(15);
        butt = new JButton("rimuovi");
        art = new JTextArea("ARTICOLO  ");
        num = new JTextField(15);
        numart = new JTextArea("NUMERO  ");

        art.setFont(new Font("impact", Font.ITALIC, 14));
        art.setForeground(Color.black);
        numart.setFont(new Font("impact", Font.ITALIC, 14));
        numart.setForeground(Color.black);

        
        butt.setFont(new Font("arial", Font.ROMAN_BASELINE, 14));
        butt.setForeground(Color.black);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(art);
        add(ogg);
        add(numart);
        add(num);
        add(butt);

        butt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ottieni il nome del prodotto e la quantità
                String nomeProdotto = ogg.getText().trim();
                String numeroStringa = num.getText().trim();
                ogg.setText("");
                num.setText("");

                // Converti la quantità in un numero intero
                int quantitaRichiesta;
                try {
                    quantitaRichiesta = Integer.parseInt(numeroStringa);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Inserisci un numero valido per la quantità.");
                    return;
                }

                // Chiama il metodo per aggiornare il file
                aggiornaFile(nomeProdotto, quantitaRichiesta);
            }
        });
    }

    private void aggiornaFile(String nomeProdotto, int quantitaRichiesta) {
        String percorsoFile = "output.txt"; // Nome del file di input/output
        File file = new File(percorsoFile);
        List<String> righeAggiornate = new ArrayList<>();
        boolean prodottoTrovato = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;

            // Leggi il file riga per riga
            while ((linea = br.readLine()) != null) {
                String[] parti = linea.split(";");
                if (parti.length == 4) {
                    String nome = parti[0];
                    String reparto = parti[1];
                    int numero = Integer.parseInt(parti[2]);
                    String descrizione = parti[3];

                    // Verifica se il nome corrisponde al prodotto cercato
                    if (nome.equalsIgnoreCase(nomeProdotto)) {
                        prodottoTrovato = true;
                        if (numero < quantitaRichiesta) {
                            // Se la quantità nel file è inferiore a quella richiesta, non aggiungere la riga (effettivamente cancellandola)
                            JOptionPane.showMessageDialog(null, "La quantità richiesta supera quella disponibile. La riga sarà rimossa.");
                            continue; // Salta questa riga
                        } else {
                            // Altrimenti, sottrai la quantità richiesta
                            numero -= quantitaRichiesta;
                            if (numero > 0) {
                                // Aggiungi la riga aggiornata alla lista
                                righeAggiornate.add(nome + ";" + reparto + ";" + numero + ";" + descrizione);
                            } else {
                                JOptionPane.showMessageDialog(null, "Quantità esaurita, il prodotto sarà rimosso.");
                            }
                        }
                    } else {
                        // Aggiungi la riga originale alla lista se non è quella da modificare
                        righeAggiornate.add(linea);
                    }
                } else {
                    righeAggiornate.add(linea);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Scrivi le righe aggiornate nel file
        if (prodottoTrovato) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String riga : righeAggiornate) {
                    bw.write(riga);
                    bw.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Prodotto non trovato nel file.");
        }
    }
}
