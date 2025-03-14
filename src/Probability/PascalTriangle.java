package Probability;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PascalTriangle extends JFrame {

    public PascalTriangle() {
        setTitle("Piramide de Pascal");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(4, 80, 32));

        JLabel titleLabel = new JLabel("Piramide de Pascal", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelN = new JLabel("Número total de ensayos (n):");
        labelN.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(labelN, gbc);

        JTextField textN = new JTextField(10);
        gbc.gridx = 1;
        rightPanel.add(textN, gbc);

        JButton generateButton = new JButton("Generar Triángulo");
        generateButton.setBackground(new Color(228, 7, 3));
        generateButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        rightPanel.add(generateButton, gbc);

        JTextArea resultArea = new JTextArea(20, 60);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(4, 80, 32));
        resultArea.setForeground(Color.WHITE);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.gridy = 3;
        rightPanel.add(scrollPane, gbc);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("");
                int rows = Integer.parseInt(textN.getText());
                generatePascalTriangle(rows, resultArea);
            }
        });

        mainPanel.add(rightPanel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        setVisible(true);
    }

    private void generatePascalTriangle(int rows, JTextArea resultArea) {
        rows = rows + 1;
        for (int i = 0; i < rows; i++) {
            int number = 1;
            resultArea.append(String.format("%" + (rows - i) * 2 + "s", ""));
            for (int j = 0; j <= i; j++) {
                resultArea.append(String.format("%4d", number));
                number = number * (i - j) / (j + 1);
            }
            resultArea.append("\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PascalTriangle::new);
    }
}

