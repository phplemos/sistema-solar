import java.awt.*;

public enum ComponentesSistemaSolar {// ESTRELA
    SOL       ("Sol",      1390000.0,     0.0,              0.0,              25.0,          new Color(255, 204, 0)),
    MERCURIO  ("Mercúrio", 4879.4,        57.9,             0.24,             58.6,          new Color(169, 169, 169)),
    VENUS     ("Vênus",    12103.6,       108.2,            0.62,             -243.0,        new Color(218, 165, 32)),
    TERRA     ("Terra",    12756.2,       149.6,            1.00,             1.0,           new Color(0, 0, 255)),
    MARTE     ("Marte",    6794.4,        227.9,            1.88,             1.03,          new Color(178, 34, 34)),
    JUPITER   ("Júpiter",  142984.0,      778.3,            11.86,            0.41,          new Color(210, 180, 140)),
    SATURNO   ("Saturno",  120536.0,      1429.4,           29.46,            0.45,          new Color(238, 232, 170)),
    URANO     ("Urano",    51118.0,       2870.9,           84.01,            -0.72,         new Color(173, 216, 230)),
    NETUNO    ("Netuno",   49538.0,       4504.3,           164.79,           0.67,          new Color(0, 0, 139));

    private final String nome;
    private final double diametroKm;
    private final double distanciaSolMilhoesKm;
    private final double periodoTranslacaoAnos;
    private final double periodoRotacaoDias;
    private final Color cor;

    ComponentesSistemaSolar(String nome, double diametroKm, double distanciaSolMilhoesKm,
        double periodoTranslacaoAnos, double periodoRotacaoDias, Color cor) {
        this.nome = nome;
        this.diametroKm = diametroKm;
        this.distanciaSolMilhoesKm = distanciaSolMilhoesKm;
        this.periodoTranslacaoAnos = periodoTranslacaoAnos;
        this.periodoRotacaoDias = periodoRotacaoDias;
        this.cor = cor;
    }

    public double getDiametroKm() { return diametroKm; }
    public double getDistanciaSolMilhoesKm() { return distanciaSolMilhoesKm; }
    public Color getCor() { return cor; }

    public double getVelocidadeTranslacao() {
        if (periodoTranslacaoAnos == 0) return 0;
        return 1.0 / periodoTranslacaoAnos;
    }

    public double getVelocidadeRotacao() {
        if (periodoRotacaoDias == 0) return 0;
        return 1.0 / periodoRotacaoDias;
    }
}
