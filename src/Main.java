import javax.swing.*;
import java.awt.*;

void main() {
    // Espaço vazio
    JFrame framePrincipal  = new JFrame();
    framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePrincipal.setBackground(Color.BLACK);
    framePrincipal.setSize(1200, 800);
    framePrincipal.setVisible(true);

    JPanel sistemaSolar = new SistemaSolar();

    framePrincipal.add(sistemaSolar);

}
