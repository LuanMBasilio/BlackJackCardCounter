package counter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private static Contador contador;

    public static void iniciarPrograma() {
        double numeroDeBaralhos = obterNumeroDeBaralhos();
        contador = new Contador(numeroDeBaralhos);
        criarMenu();
    }

    private static double obterNumeroDeBaralhos() {
        double numeroDeBaralhos = 0;
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Digite o número de baralhos:");
            if (input == null || input.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite um número válido.");
                continue;
            }
            try {
                numeroDeBaralhos = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Número inválido. Tente novamente.");
            }
        }
        return numeroDeBaralhos;
    }

    private static void criarMenu() {
        JFrame frame = new JFrame("Contador de Cartas - Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 500);  // Ajustado para acomodar o botão maior

        // Painel superior com informações dos contadores
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new GridLayout(1, 3));

        JLabel contadorCorrenteLabel = new JLabel("Contador Corrente: " + contador.getContadorCorrente());
        JLabel contadorRealLabel = new JLabel("Contador Real: " + contador.calcularContadorReal());
        JLabel baralhosRestantesLabel = new JLabel("Baralhos Restantes: " + contador.getNumeroDeBaralhos());

        painelSuperior.add(contadorCorrenteLabel);
        painelSuperior.add(contadorRealLabel);
        painelSuperior.add(baralhosRestantesLabel);
        frame.add(painelSuperior, BorderLayout.NORTH);

        //Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridBagLayout()); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Espaçamento entre os botões

        // add botões
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Valete", "Dama", "Rei", "Ás"};
        int pos = 0;
        for (String valor : valores) {
            JButton botaoCarta = new JButton(valor);
            botaoCarta.setFont(new Font("Arial", Font.PLAIN, 14));
            botaoCarta.setPreferredSize(new Dimension(80, 40));  // dimensão botões carta

            botaoCarta.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Você escolheu a carta: " + valor);
                    contador.atualizarContador(valor);
                    contador.incrementarCartasContadas();

                    double baralhosRestantes = contador.calcularBaralhosRestantes();
                    contadorCorrenteLabel.setText("Contador Corrente: " + contador.getContadorCorrente());
                    contadorRealLabel.setText("Contador Real: " + contador.calcularContadorReal());
                    baralhosRestantesLabel.setText("Baralhos Restantes: " + String.format("%.2f", baralhosRestantes));
                }
            });

            gbc.gridx = pos % 4;  // Colocando o botão na posição correta na grade
            gbc.gridy = pos / 4;
            painelBotoes.add(botaoCarta, gbc);
            pos++;
        }

        // Botão de embaralhar
        JButton embaralharButton = new JButton("Embaralhar");
        embaralharButton.setFont(new Font("Arial", Font.PLAIN, 16));  // Fonte maior
        embaralharButton.setBackground(Color.RED);
        embaralharButton.setForeground(Color.WHITE);
        embaralharButton.setPreferredSize(new Dimension(160, 40));  // Botão maior

        embaralharButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Resetando os contadores e cartas contadas
                contador = new Contador(contador.getNumeroDeBaralhos());
                JOptionPane.showMessageDialog(frame, "As contagens foram resetadas. O baralho foi embaralhado.");
                contadorCorrenteLabel.setText("Contador Corrente: " + contador.getContadorCorrente());
                contadorRealLabel.setText("Contador Real: " + contador.calcularContadorReal());
                baralhosRestantesLabel.setText("Baralhos Restantes: " + contador.getNumeroDeBaralhos());
            }
        });

        // Fazendo o botão "Embaralhar" ocupar duas colunas (nas colunas 2 e 3 da última linha)
        gbc.gridx = 1;  // Inicia na coluna 1 (segunda coluna)
        gbc.gridy = 3;  // Última linha (linha 3)
        gbc.gridwidth = 2;  // O botão "Embaralhar" vai ocupar duas colunas
        painelBotoes.add(embaralharButton, gbc);

        // Adicionando o painel de botões ao frame
        frame.add(painelBotoes, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
