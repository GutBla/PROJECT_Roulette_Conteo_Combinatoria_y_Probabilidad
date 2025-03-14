package Probability;

import javax.swing.*;
import java.awt.*;

public class ProbabilityCalculator extends JFrame {
    public void createGUI() {
        setTitle("Probabilidad - Matemáticas Discretas");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(4, 80, 32));

        JLabel titleLabel = new JLabel("Probabilidad", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel subtitle1 = new JLabel("Calcular Probabilidad");
        subtitle1.setForeground(Color.WHITE);
        subtitle1.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(subtitle1, gbc);

        gbc.gridwidth = 1;

        JLabel labelA = new JLabel("Número de Casos Favorables (A):");
        labelA.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(labelA, gbc);

        JTextField textA = new JTextField(10);
        gbc.gridx = 1;
        leftPanel.add(textA, gbc);

        JLabel labelS = new JLabel("Total de Casos Posibles (S):");
        labelS.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(labelS, gbc);

        JTextField textS = new JTextField(10);
        gbc.gridx = 1;
        leftPanel.add(textS, gbc);

        JButton calcProbButton = new JButton("Calcular Probabilidad");
        calcProbButton.setBackground(new Color(228, 7, 3));
        calcProbButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        leftPanel.add(calcProbButton, gbc);

        JLabel probResult = new JLabel("▶ Resultado ◀ ");
        probResult.setForeground(Color.WHITE);
        gbc.gridy = 4;
        leftPanel.add(probResult, gbc);

        JLabel probPercentResult = new JLabel("▶ Probabilidad ◀");
        probPercentResult.setForeground(Color.WHITE);
        gbc.gridy = 5;
        leftPanel.add(probPercentResult, gbc);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setOpaque(false);

        JLabel subtitle2 = new JLabel("Calcular Distribución y Coeficiente Binomial");
        subtitle2.setForeground(Color.WHITE);
        subtitle2.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(subtitle2, gbc);

        gbc.gridwidth = 1;

        JLabel labelN = new JLabel("Número total de ensayos (n):");
        labelN.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(labelN, gbc);

        JTextField textN = new JTextField(10);
        gbc.gridx = 1;
        rightPanel.add(textN, gbc);

        JLabel labelK = new JLabel("Número de éxitos deseados (k):");
        labelK.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(labelK, gbc);

        JTextField textK = new JTextField(10);
        gbc.gridx = 1;
        rightPanel.add(textK, gbc);

        JLabel labelP = new JLabel("Probabilidad de éxito (p):");
        labelP.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(labelP, gbc);

        JTextField textP = new JTextField(10);
        gbc.gridx = 1;
        rightPanel.add(textP, gbc);

        JButton coefButton = new JButton("Coeficiente Binomial");
        coefButton.setBackground(new Color(228, 7, 3));
        coefButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        rightPanel.add(coefButton, gbc);

        JLabel coefResult = new JLabel("▶  Resultado  ◀");
        coefResult.setForeground(Color.WHITE);
        gbc.gridy = 5;
        rightPanel.add(coefResult, gbc);

        JButton distButton = new JButton("Distribución Binomial");
        distButton.setBackground(new Color(228, 7, 3));
        distButton.setForeground(Color.WHITE);
        gbc.gridy = 6;
        rightPanel.add(distButton, gbc);

        JLabel distResult = new JLabel("▶  Resultado  ◀");
        distResult.setForeground(Color.WHITE);
        gbc.gridy = 7;
        rightPanel.add(distResult, gbc);

        JLabel distPercentResult = new JLabel("▶ Probabilidad ◀");
        distPercentResult.setForeground(Color.WHITE);
        gbc.gridy = 8;
        rightPanel.add(distPercentResult, gbc);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        JButton playRouletteButton = new JButton("Jugar con la Ruleta");
        playRouletteButton.setBackground(new Color(228, 7, 3));
        playRouletteButton.setForeground(Color.WHITE);
        bottomPanel.add(playRouletteButton);

        JButton pascalButton = new JButton("Pirámide de Pascal");
        pascalButton.setBackground(new Color(228, 7, 3));
        pascalButton.setForeground(Color.WHITE);
        bottomPanel.add(pascalButton);

        pascalButton.addActionListener(e -> {
            new PascalTriangle();
        });

        calcProbButton.addActionListener(e -> {
            try {
                int A = Integer.parseInt(textA.getText());
                int S = Integer.parseInt(textS.getText());
                if (S != 0) {
                    double probability = (double) A / S;
                    probResult.setText("P(A) = " + probability);
                    double percentage = probability * 100;
                    probPercentResult.setText("Probabilidad = " + String.format("%.2f", percentage) + "%");
                    textP.setText(String.format("%.5f", probability));
                } else {
                    probResult.setText("Error: S no puede ser 0.");
                }
            } catch (NumberFormatException ex) {
                probResult.setText("Error: Entrada no válida.");
            }
        });

        coefButton.addActionListener(e -> {
            try {
                int n = Integer.parseInt(textN.getText());
                int k = Integer.parseInt(textK.getText());
                long coef = binomialCoefficient(n, k);
                coefResult.setText("C(n, k) = " + coef);
            } catch (NumberFormatException ex) {
                coefResult.setText("Error: Entrada no válida.");
            }
        });

        distButton.addActionListener(e -> {
            try {
                int n = Integer.parseInt(textN.getText());
                int k = Integer.parseInt(textK.getText());
                double p = Double.parseDouble(textP.getText());
                if (p >= 0 && p <= 1) {
                    double dist = binomialDistribution(n, k, p);
                    distResult.setText("P(X = k) = " + dist);
                    double distPercentage = dist * 100;
                    distPercentResult.setText("Probabilidad = " + String.format("%.4f", distPercentage) + "%");
                } else {
                    distResult.setText("Error: p debe estar entre 0 y 1.");
                }
            } catch (NumberFormatException ex) {
                distResult.setText("Error: Entrada no válida.");
            }
        });

        playRouletteButton.addActionListener(e -> new ProbabilityGame());

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    public static long binomialCoefficient(int n, int k) {
        long result = 1;
        if (k > n) {
            return 0;
        }
        for (int i = 0; i < k; i++) {
            result = result * (n - i) / (i + 1);
        }
        return result;
    }

    public static double binomialDistribution(int n, int k, double p) {
        long coef = binomialCoefficient(n, k);
        return coef * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    public static void main(String[] args) {
        new ProbabilityCalculator();
    }
}
