import javax.swing.*;
import java.awt.*;

public class Sol extends JPanel {
    private final int diametro;

    public Sol(){
        this.diametro = (int)(ComponentesSistemaSolar.TERRA.getDiametroKm() * 109) / 1000; // Calculo de escala,
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int positionX = super.getHeight() / 2;
        int positioY = super.getHeight() / 2;
        g.setColor(Color.YELLOW);
        g.fillRect(positionX, positioY, diametro, diametro);
    }




}
