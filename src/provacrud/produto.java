package provacrud;

public class produto {

    private String nome;
    private Double valor;
    private int qtdDisponivel;
    private int qtdReservada;

    public produto(String nome, Double valor, int qtdDisp, int qtdRes) {
        this.nome = nome;
        this.valor = valor;
        this.qtdDisponivel = qtdDisp;
        this.qtdReservada = qtdRes;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public int getQtdReservada() {
        return qtdReservada;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public void setQtdReservada(int qtdReservada) {
        this.qtdReservada = qtdReservada;
    }

    public void entradaProduto(int qtdEntrada) {
        qtdDisponivel += qtdEntrada;
    }

    public void reservarProduto(int qtdReservar) {
        if (qtdReservar <= qtdDisponivel) {
            qtdDisponivel -= qtdReservar;
            qtdReservada += qtdReservar;
        } else {
            System.out.println("Quantidade insuficiente disponível para reserva.");
        }

    }

    public void cancelarReserva(int qtdCancelar) {
        if (qtdCancelar <= qtdReservada) {
            qtdDisponivel += qtdCancelar;
            qtdReservada -= qtdCancelar;
        } else {
            System.out.println("Quantidade insuficiente reservada para cancelar.");
        }
    }

    public void saidaProduto(int qtdSaida) {
        if (qtdSaida <= qtdReservada) {
            qtdReservada -= qtdSaida;
            if (qtdReservada == 0) {
                qtdDisponivel -= qtdSaida;
            }
        } else {
            System.out.println("Quantidade insuficiente reservada para saída.");
        }
    }

    public String toString() {
        return nome + " | Valor: " + valor + " | Disponível: " + qtdDisponivel + " | Reservada: " + qtdReservada;
    }
}
