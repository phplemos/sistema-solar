import javax.swing.*;
import java.awt.*;

public class SistemaSolar extends JPanel {


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int centerX = super.getSize().width/2;
        int centerY = super.getSize().height/2;

        paintComponente(ComponentesSistemaSolar.TERRA, g,calculoPosicao());
        paintComponente(ComponentesSistemaSolar.SOL, g);
    }

    public int getDiametroComponente(ComponentesSistemaSolar componentesSistemaSolar){
        return (int) componentesSistemaSolar.getDiametroKm() / 10000;
    }

    public int getDiametroOrbitaComponente(ComponentesSistemaSolar componentesSistemaSolar) {
        return 2 * (int) componentesSistemaSolar.getDistanciaSolMilhoesKm();
    }

    private void paintComponente(ComponentesSistemaSolar componentesSistemaSolar, Graphics g, int positionX, int positionY, int centerX, int centerY, int angulo){
        // Orbita
        g.setColor(Color.red);
        g.drawOval(centerX,centerY, getDiametroOrbitaComponente(componentesSistemaSolar), getDiametroOrbitaComponente(componentesSistemaSolar));

        // Componente
        g.setColor(componentesSistemaSolar.getCor());
        g.fillOval(calculoPosicao(centerX, componentesSistemaSolar.getDistanciaSolMilhoesKm(),angulo),positionY,getDiametroComponente(componentesSistemaSolar),getDiametroComponente(componentesSistemaSolar));
    }

    private int calculoPosicao(int centro, double raioOrbita, double angulo){
        return (int) ((int) centro + raioOrbita * Math.cos(angulo));
    }
}
