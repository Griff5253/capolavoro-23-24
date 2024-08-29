import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;

public class frame extends JFrame {
    private pannel barra;
    private pannello2 param;
    private elimina eli;
    
    public frame() {
        super("Capolavoro");

        barra = new pannel();
        param = new pannello2();
        eli = new elimina();

        setLayout(new BorderLayout(20, 20));
        add(param, BorderLayout.SOUTH);
        add(barra, BorderLayout.NORTH);
        add(eli, BorderLayout.CENTER);

        // Imposta l'icona della finestra
        ImageIcon icon = new ImageIcon(getClass().getResource("logo.png"));
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(128, 128, Image.SCALE_SMOOTH); // Dimensioni desiderate
        setIconImage(scaledImage);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocation(0, 0);
        setBackground(Color.LIGHT_GRAY);
        setResizable(false);
    }
}
