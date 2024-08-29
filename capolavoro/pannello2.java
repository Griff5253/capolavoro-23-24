import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class pannello2 extends JPanel {

    public JTextArea testn;
    public JTextArea testr;
    public JTextArea testnum;
    public JTextField nome;
    public JTextField reparto;
    private JTextArea desc;
    private JTextField descrizione;
    public JTextField numero;
    private JButton bottonpiu;
    public String testpercop;

    public pannello2() {

        setLayout(new FlowLayout(FlowLayout.CENTER));

        bottonpiu = new JButton("                                                                                          aggiungi                                                                                        ");

        testn = new JTextArea(" NOME ");
        testr = new JTextArea(" REPARTO ");
        testnum = new JTextArea(" NUMERO ");
        desc = new JTextArea("DESCRIZIONE");
        testn.setEditable(false);
        testr.setEditable(false);
        testnum.setEditable(false);


        testn.setFont(new Font("impact", Font.ITALIC, 14));
        testn.setForeground(Color.black);
        testr.setFont(new Font("impact", Font.ITALIC, 14));
        testr.setForeground(Color.black);
        testnum.setFont(new Font("impact", Font.ITALIC, 14));
        testnum.setForeground(Color.black);
        desc.setFont(new Font("impact", Font.ITALIC, 14));
        desc.setForeground(Color.black);


        


        testn.setBorder(BorderFactory.createLineBorder(Color.black));
        testr.setBorder(BorderFactory.createLineBorder(Color.black));
        testnum.setBorder(BorderFactory.createLineBorder(Color.black));
        desc.setBorder(BorderFactory.createLineBorder(Color.black));
        

        nome = new JTextField(10);
        reparto = new JTextField(10);
        numero = new JTextField(10);
        descrizione = new JTextField(10);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.gray);

        setBorder(BorderFactory.createLineBorder(Color.black));

        add(bottonpiu);
        add(testn);
        add(nome);
        add(testr);
        add(reparto);
        add(testnum);
        add(numero);
        add(desc);
        add(descrizione);

        bottonpiu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                String testo = nome.getText() + ";" + reparto.getText() + ";" + numero.getText()+ ";"+descrizione.getText();
                
            
                nome.setText("");
                reparto.setText("");
                numero.setText("");
                descrizione.setText("");

                
                testpercop = testo;

                String percorsoFile = "output.txt"; 
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoFile, true))) {
                    writer.write(testpercop);
                    writer.newLine(); 
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
