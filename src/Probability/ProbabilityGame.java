package Probability;

import javax.swing.*;
import java.awt.*;

public class ProbabilityGame extends JFrame {

    public ProbabilityGame() {
        setTitle("Probabilidad con la Ruleta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(4, 80, 32));

        JLabel titleLabel = new JLabel("Probabilidad con la Ruleta", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        JLabel leftPlaceholder = new JLabel("");
        leftPanel.add(leftPlaceholder);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel subtitle = new JLabel("¿Cuál crees que es la probabilidad de alguno de los siguientes casos?");
        subtitle.setForeground(Color.WHITE);
        subtitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        rightPanel.add(subtitle, gbc);

        String[] cases = {
                "Número único", "Rojo/Negro", "Par/Impar", "Alto/Bajo",
                "Docena", "Columna", "Línea", "Cuadro", "Calle", "Caballo"
        };
        JComboBox<String> caseSelector = new JComboBox<>(cases);
        gbc.gridy = 1;
        rightPanel.add(caseSelector, gbc);

        JLabel formulaLabel = new JLabel("Fórmula ");
        formulaLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        rightPanel.add(formulaLabel, gbc);

        caseSelector.addActionListener(e -> {
            int selectedIndex = caseSelector.getSelectedIndex();
            String[] formulas = {
                    "1/37", "18/37", "18/37", "18/37",
                    "12/37", "12/37", "6/37", "4/37", "3/37", "2/37"
            };
            formulaLabel.setText("Fórmula: " + formulas[selectedIndex]);
        });

        JLabel fromLabel = new JLabel("Desde: ");
        fromLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        rightPanel.add(fromLabel, gbc);

        JSlider fromSlider = createCustomSlider();
        gbc.gridx = 1;
        rightPanel.add(fromSlider, gbc);

        JLabel fromValueLabel = new JLabel("0%");
        fromValueLabel.setForeground(Color.WHITE);
        gbc.gridx = 2;
        rightPanel.add(fromValueLabel, gbc);

        fromSlider.addChangeListener(e -> fromValueLabel.setText(fromSlider.getValue() + "%"));

        JLabel toLabel = new JLabel("Hasta: ");
        toLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        rightPanel.add(toLabel, gbc);

        JSlider toSlider = createCustomSlider();
        gbc.gridx = 1;
        rightPanel.add(toSlider, gbc);

        JLabel toValueLabel = new JLabel("100%");
        toValueLabel.setForeground(Color.WHITE);
        gbc.gridx = 2;
        rightPanel.add(toValueLabel, gbc);

        toSlider.addChangeListener(e -> toValueLabel.setText(toSlider.getValue() + "%"));

        JButton verifyButton = new JButton("Verificar Respuesta");
        verifyButton.setBackground(new Color(228, 7, 3));
        verifyButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        rightPanel.add(verifyButton, gbc);

        JLabel resultLabel = new JLabel("▶ Resultado ◀ ");
        resultLabel.setForeground(Color.WHITE);
        gbc.gridy = 6;
        rightPanel.add(resultLabel, gbc);

        verifyButton.addActionListener(e -> {
            int selectedCaseIndex = caseSelector.getSelectedIndex();
            double caseProbability = getCaseProbability(selectedCaseIndex);

            int fromValue = fromSlider.getValue();
            int toValue = toSlider.getValue();

            double percentage = caseProbability * 100;
            boolean isInRange = percentage >= fromValue && percentage <= toValue;

            resultLabel.setText(String.format("Probabilidad: %.2f%% - %s", percentage, isInRange ? "Verdadero ✔" : "Falso ✖"));
            resultLabel.setForeground(isInRange ? Color.GREEN : Color.RED);
        });

        gbc.gridy = 7;
        JLabel binomialSubtitle = new JLabel("¿ Cual crees que es la probabilidad de acierto en un numero de tiradas ?");
        binomialSubtitle.setForeground(Color.WHITE);
        binomialSubtitle.setFont(new Font("Arial", Font.BOLD, 16));
        rightPanel.add(binomialSubtitle, gbc);

        String[] hits = {
                "1 acierto en 10", "2 aciertos en 10", "3 aciertos en 10", "4 aciertos en 10",
                "5 aciertos en 10", "6 aciertos en 10", "7 aciertos en 10", "8 aciertos en 10",
                "9 aciertos en 10", "10 aciertos en 10"
        };
        JComboBox<String> hitSelector = new JComboBox<>(hits);
        gbc.gridy = 8;
        rightPanel.add(hitSelector, gbc);

        JLabel binomialFormulaLabel = new JLabel("Coeficiente Binomial Ejemplo: ");
        binomialFormulaLabel.setForeground(Color.WHITE);
        gbc.gridy = 9;
        rightPanel.add(binomialFormulaLabel, gbc);

        hitSelector.addActionListener(e -> {
            int selectedIndex = hitSelector.getSelectedIndex();
            String[] binomialFormulas = {
                    "1", "10", "45", "120", "210", "252", "210", "120", "45", "10"
            };
            binomialFormulaLabel.setText("Coeficiente Binomial Ejemplo: " + binomialFormulas[selectedIndex]);
        });

        JLabel binomialFromLabel = new JLabel("Desde: ");
        binomialFromLabel.setForeground(Color.WHITE);
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        rightPanel.add(binomialFromLabel, gbc);

        JSlider binomialFromSlider = createCustomSlider();
        gbc.gridx = 1;
        rightPanel.add(binomialFromSlider, gbc);

        JLabel binomialFromValueLabel = new JLabel("0%");
        binomialFromValueLabel.setForeground(Color.WHITE);
        gbc.gridx = 2;
        rightPanel.add(binomialFromValueLabel, gbc);

        binomialFromSlider.addChangeListener(e -> binomialFromValueLabel.setText(binomialFromSlider.getValue() + "%"));

        JLabel binomialToLabel = new JLabel("Hasta: ");
        binomialToLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 11;
        rightPanel.add(binomialToLabel, gbc);

        JSlider binomialToSlider = createCustomSlider();
        gbc.gridx = 1;
        rightPanel.add(binomialToSlider, gbc);

        JLabel binomialToValueLabel = new JLabel("100%");
        binomialToValueLabel.setForeground(Color.WHITE);
        gbc.gridx = 2;
        rightPanel.add(binomialToValueLabel, gbc);

        binomialToSlider.addChangeListener(e -> binomialToValueLabel.setText(binomialToSlider.getValue() + "%"));

        JButton verifyBinomialButton = new JButton("Verificar Respuesta");
        verifyBinomialButton.setBackground(new Color(228, 7, 3));
        verifyBinomialButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        rightPanel.add(verifyBinomialButton, gbc);

        JLabel binomialResultLabel = new JLabel("▶ Resultado ◀");
        binomialResultLabel.setForeground(Color.WHITE);
        gbc.gridy = 13;
        rightPanel.add(binomialResultLabel, gbc);

        verifyBinomialButton.addActionListener(e -> {
            int selectedHitIndex = hitSelector.getSelectedIndex();
            int totalAttempts = 10; // Total de intentos
            double probability = getCaseProbability(selectedHitIndex) * totalAttempts;

            int binomialFromValue = binomialFromSlider.getValue();
            int binomialToValue = binomialToSlider.getValue();

            boolean isInRange = probability >= binomialFromValue && probability <= binomialToValue;

            binomialResultLabel.setText(String.format("Probabilidad: %.2f%% - %s", probability, isInRange ? "Verdadero ✔" : "Falso ✖"));
            binomialResultLabel.setForeground(isInRange ? Color.GREEN : Color.RED);
        });

        JButton showRouletteButton = new JButton("Mostrar Ruleta");
        showRouletteButton.setBackground(new Color(228, 7, 3));
        showRouletteButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        rightPanel.add(showRouletteButton, gbc);

        showRouletteButton.addActionListener(e -> {
            RouletteFrame frame = new RouletteFrame();
            frame.setVisible(true);
        });


        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();
        setVisible(true);
    }

    private double getCaseProbability(int selectedIndex) {
        double[] probabilities = {1.0 / 37, 18.0 / 37, 18.0 / 37, 18.0 / 37, 12.0 / 37, 12.0 / 37, 6.0 / 37, 4.0 / 37, 3.0 / 37, 2.0 / 37};
        return probabilities[selectedIndex];
    }

    private JSlider createCustomSlider() {
        JSlider slider = new JSlider(0, 100, 0);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(4, 80, 32));
        slider.setForeground(Color.WHITE);
        return slider;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProbabilityGame::new);
    }
}
