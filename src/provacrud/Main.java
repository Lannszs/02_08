package provacrud;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

    List<produto> listaProduto = new ArrayList<produto>();

    public static void main(String[] args) {
        Main main = new Main();
        main.runMenu();
    }

    public void runMenu() {
        String menu = "";

        while (!menu.equals("8")) {
            menu = JOptionPane.showInputDialog("                              Loja JôJoias \n"
                    + "                          Digite uma opção \n"
                    + " \n"
                    + "1- Consultar estoque          5 - Entrada de produto \n"
                    + "2- Cadastrar                          6 - Reservas \n"
                    + "3- Atualizar                            7 -Registrar saida\n"
                    + "4 - Remover                           8 -Sair\n");

            if (menu.equals("1")) {
                consultarEstoque();
            } else if (menu.equals("2")) {
                cadastrarProduto();
            } else if (menu.equals("3")) {
                atualizarProduto();
            } else if (menu.equals("4")) {
                removerProduto();
            } else if (menu.equals("5")) {
                entradaProduto();
            } else if (menu.equals("6")) {
                String reservaMenu = "";
                while (!reservaMenu.equals("3")) {
                    reservaMenu = JOptionPane.showInputDialog("                    Loja JôJoias \n"
                            + "                 Digite uma opção \n"
                            + " \n"
                            + "1- Reservar produto\n"
                            + "2- Cancelar reserva\n"
                            + "3- Voltar\n");

                    if (reservaMenu.equals("1")) {
                        reservarProduto();
                    } else if (reservaMenu.equals("2")) {
                        cancelarReserva();
                    }
                }
            } else if (menu.equals("7")) {
                registrarSaidaProduto();
            }
        }
        System.out.println("saiu");
    }

    public void consultarEstoque() {
        StringBuilder estoque = new StringBuilder("Estoque:\n");

        for (produto p : listaProduto) {
            estoque.append("Nome: ").append(p.getNome())
                    .append(" | Valor: ").append(p.getValor())
                    .append(" | Disponível: ").append(p.getQtdDisponivel())
                    .append(" | Reservada: ").append(p.getQtdReservada())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null, estoque.toString(), "Estoque", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cadastrarProduto() {
        String novoProduto = JOptionPane.showInputDialog("Digite os dados do produto (nome, valor, qtd disponível, qtd reservada):");
        String[] dados = novoProduto.split(",");
        if (dados.length == 4) {
            String nome = dados[0].trim();
            Double valor = Double.parseDouble(dados[1].trim());
            int qtdDisp = Integer.parseInt(dados[2].trim());
            int qtdRes = Integer.parseInt(dados[3].trim());

            produto novo = new produto(nome, valor, qtdDisp, qtdRes);
            listaProduto.add(novo);
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Certifique-se de inserir nome, valor, qtd disponível e qtd reservada separados por vírgula.");
        }
        
    }

    public void atualizarProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto a ser atualizado:");

        for (produto p : listaProduto) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                double novoValor = Double.parseDouble(JOptionPane.showInputDialog("Digite o novo valor do produto:"));
                int novaQtdDisponivel = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade disponível do produto:"));
                int novaQtdReservada = Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade reservada do produto:"));

                p.setValor(novoValor);
                p.setQtdDisponivel(novaQtdDisponivel);
                p.setQtdReservada(novaQtdReservada);

                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!", "Atualização", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        mensagemErro();
    }

    public void removerProduto() {
        String nome = JOptionPane.showInputDialog("Digite o nome do produto a ser removido:");

        for (produto p : listaProduto) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                listaProduto.remove(p);
                JOptionPane.showMessageDialog(null, "Produto removido com sucesso!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        mensagemErro();
    }

    private void entradaProduto() {
        String nomeProdutoEntrada = JOptionPane.showInputDialog("Digite o nome do produto para entrada:");
        int qtdEntrada = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de produtos a serem adicionados:"));

        for (produto p : listaProduto) {
            if (p.getNome().equalsIgnoreCase(nomeProdutoEntrada)) {
                p.entradaProduto(qtdEntrada);
                JOptionPane.showMessageDialog(null, "Entrada de produtos realizada com sucesso!", "Entrada de Produto", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        mensagemErro();
    }

    private void reservarProduto() {
        String nomeProdutoReserva = JOptionPane.showInputDialog("Digite o nome do produto para reserva:");
        int qtdReservar = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de produtos a serem reservados:"));

        for (produto p : listaProduto) {
            if (p.getNome().equalsIgnoreCase(nomeProdutoReserva)) {
                p.reservarProduto(qtdReservar);
                JOptionPane.showMessageDialog(null, "Reserva de produtos realizada com sucesso!", "Reserva de Produto", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        mensagemErro();
    }

    private void cancelarReserva() {
        String nomeProdutoCancelar = JOptionPane.showInputDialog("Digite o nome do produto para cancelar a reserva:");
        int qtdCancelar = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de produtos a serem cancelados da reserva:"));

        for (produto p : listaProduto) {
            if (p.getNome().equalsIgnoreCase(nomeProdutoCancelar)) {
                p.cancelarReserva(qtdCancelar);
                JOptionPane.showMessageDialog(null, "Cancelamento de reserva realizado com sucesso!", "Cancelamento de Reserva", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        mensagemErro();
    }

    private void registrarSaidaProduto() {
        String nomeProdutoSaida = JOptionPane.showInputDialog("Digite o nome do produto para registrar a saída:");
        int qtdSaida = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de produtos a serem registrados na saída:"));

        for (produto p : listaProduto) {
            if (p.getNome().equalsIgnoreCase(nomeProdutoSaida)) {
                p.saidaProduto(qtdSaida);
                JOptionPane.showMessageDialog(null, "Saída de produtos registrada com sucesso!", "Saída de Produto", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        mensagemErro();
    }
    
    private void mensagemErro(){
         JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
    }

}
