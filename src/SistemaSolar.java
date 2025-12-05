import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class SistemaSolar extends JPanel implements MouseWheelListener, ActionListener {
    private boolean primeiraRenderizacao = true;
    private final AffineTransform at;
    private double angulo = 0;
    private final double FATOR_ESCALA_TAMANHO = 0.0002; // Ajusta o tamanho das bolinhas
    private final double FATOR_ESCALA_ORBITA = 10;   // Espalha as órbitas
    private double FATOR_VELOCIDADE_TRANSLACAO = 0.1;
    private final int TAMANHO_MINIMO_PLANETA = 5;    // Para ninguém sumir da tela
    private final int TAMANHO_MAXIMO_SOL = 80;       // Limita o Sol para não engolir Mercúrio
    private final JButton buttonRapido = new JButton("Mais rapido");
    private final JButton buttonDevagar = new JButton("Mais devagar");

    public SistemaSolar() {
        this.at = new AffineTransform();
        final Timer timer = new Timer(10, this);
        at.translate(super.getSize().width, super.getSize().height);
        setBackground(Color.BLACK);
        addMouseWheelListener(this);
        timer.start();
        add(buttonRapido);
        add(buttonDevagar);
        buttonRapido.addActionListener(e -> {
            this.FATOR_VELOCIDADE_TRANSLACAO /= 0.1;
        });
        buttonDevagar.addActionListener(e-> {
            this.FATOR_VELOCIDADE_TRANSLACAO *= 0.1;
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.angulo += 0.1;
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        Graphics g = graphics.create();
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g2D);

        if (primeiraRenderizacao) {
            at.translate(getWidth() / 2.0, getHeight() / 2.0);
            primeiraRenderizacao = false;
        }
        g2D.setTransform(at);
        final int UNIVERSO_X = 0;
        final int UNIVERSO_Y = 0;
        int diametroSol = getDiametroComponente(ComponentesSistemaSolar.SOL);
        int raioSol = diametroSol / 2;

        g.setColor(Color.YELLOW);
        g.fillOval(UNIVERSO_X - raioSol, UNIVERSO_Y - raioSol, diametroSol, diametroSol);

        drawComponente(ComponentesSistemaSolar.MERCURIO, g,UNIVERSO_X , UNIVERSO_Y, ComponentesSistemaSolar.MERCURIO.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.VENUS, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.VENUS.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.TERRA, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.TERRA.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.MARTE, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.MARTE.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.JUPITER, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.JUPITER.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.SATURNO, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.SATURNO.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.URANO, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.URANO.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);
        drawComponente(ComponentesSistemaSolar.NETUNO, g,UNIVERSO_X , UNIVERSO_Y , ComponentesSistemaSolar.NETUNO.getVelocidadeTranslacao() * angulo * FATOR_VELOCIDADE_TRANSLACAO);

        g2D.dispose();
    }

    private int getDiametroComponente(ComponentesSistemaSolar componentesSistemaSolar){
        if (componentesSistemaSolar == ComponentesSistemaSolar.SOL) {
            return TAMANHO_MAXIMO_SOL;
        }
        double tamanhoCalculado = componentesSistemaSolar.getDiametroKm() * FATOR_ESCALA_TAMANHO;
        // Se o tamanho ficar menor que o minimo, ele pega o valor do minimo
        return (int) Math.max(tamanhoCalculado, TAMANHO_MINIMO_PLANETA);
    }

    private int getDiametroOrbitaComponente(ComponentesSistemaSolar componentesSistemaSolar) {
        double distanciaReal = componentesSistemaSolar.getDistanciaSolMilhoesKm();
        // Raiz da distancia pra ficar visivel na tela, "+ Tamanho maximo do sol" para garantir que vai ficar dps da borda do sol
        double raioOrbita = (Math.sqrt(distanciaReal) * FATOR_ESCALA_ORBITA) + TAMANHO_MAXIMO_SOL;
        return (int) (raioOrbita * 2);
    }

    private void drawComponente(ComponentesSistemaSolar componentesSistemaSolar, Graphics g, int centerX, int centerY, double angulo){
        int raioOrbita = (int) getDiametroOrbitaComponente(componentesSistemaSolar) / 2;
        int positionX = calculoPosicaoX(centerX, raioOrbita, angulo);
        int positionY = calculoPosicaoY(centerY, raioOrbita, angulo);
        int raioComponente = getDiametroComponente(componentesSistemaSolar) / 2;

        // Orbita
        g.setColor(Color.red);
        g.drawOval(centerX - raioOrbita, centerY - raioOrbita, getDiametroOrbitaComponente(componentesSistemaSolar), getDiametroOrbitaComponente(componentesSistemaSolar));

        // Componente
        g.setColor(componentesSistemaSolar.getCor());
        g.fillOval(positionX - raioComponente, positionY - raioComponente, getDiametroComponente(componentesSistemaSolar), getDiametroComponente(componentesSistemaSolar));
    }

    private int calculoPosicaoX(int centro, double raioOrbita, double angulo){
        return (int) ((int) centro + raioOrbita * Math.cos(angulo));
    }
    private int calculoPosicaoY(int centro, double raioOrbita, double angulo){
        return (int) ((int) centro + raioOrbita * Math.sin(angulo));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double zoomFactor = (e.getWheelRotation() < 0) ? 1.1 : 0.9;
        double mouseX = e.getX();
        double mouseY = e.getY();

        // Transformação temporaria
        AffineTransform zoomAtMouse = new AffineTransform();

        // 1. Move o Universo para que o mouse seja o novo centro
        zoomAtMouse.translate(mouseX, mouseY);

        zoomAtMouse.scale(zoomFactor, zoomFactor);

        zoomAtMouse.translate(-mouseX, -mouseY);

        // Aplica a nova transformação por cima da atual
        at.preConcatenate(zoomAtMouse);
        repaint();
    }


}
