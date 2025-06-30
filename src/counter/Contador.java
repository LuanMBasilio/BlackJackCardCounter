package counter;

public class Contador {

    private int contadorCorrente = 0;
    private final double numeroDeBaralhosInicial;
    private double cartasContadas = 0;

    public Contador(double numeroDeBaralhos) {
        this.numeroDeBaralhosInicial = numeroDeBaralhos;
    }

    public void atualizarContador(String carta) {
        switch (carta) {
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
                contadorCorrente++;
                break;
            case "10":
            case "Valete":
            case "Dama":
            case "Rei":
            case "Ás":
                contadorCorrente--;
                break;
            default:
                break;
        }

        cartasContadas++; // Incrementa sempre que uma carta é processada
    }

    public double calcularContadorReal() {
        double baralhosRestantes = calcularBaralhosRestantes();
        if (baralhosRestantes == 0) return 0;
        return contadorCorrente / baralhosRestantes;
    }


    public double calcularBaralhosRestantes() {
        double cartasRestantes = (numeroDeBaralhosInicial * 52) - cartasContadas;
        return Math.max(cartasRestantes / 52, 0);
    }

    public void incrementarCartasContadas() {
        cartasContadas++;
    }

    public int getContadorCorrente() {
        return contadorCorrente;
    }

    public double getNumeroDeBaralhos() {
        return numeroDeBaralhosInicial;
    }
}
