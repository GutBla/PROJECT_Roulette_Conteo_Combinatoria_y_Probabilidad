package Factorial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FactorialGame {

    private static int generatedNumber;

    public void createGUI() {
        JFrame frame = new JFrame("Juego: Adivina el FactorialGame");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("¡Adivina el FactorialGame!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        JLabel problemLabel = new JLabel("", SwingConstants.CENTER);
        problemLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        panel.add(problemLabel);

        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("Ingrese su respuesta:");
        JTextField inputField = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        panel.add(inputPanel);

        JButton checkButton = new JButton("Verificar Respuesta");
        panel.add(checkButton);
        frame.add(panel, BorderLayout.NORTH);

        JLabel resultLabel = new JLabel("Resultado:", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(resultLabel, BorderLayout.CENTER);

        generatedNumber = generateNumber();
        problemLabel.setText("¿Cuál es el factorial de " + generatedNumber + "?");

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long userAnswer = Long.parseLong(inputField.getText());
                    long correctAnswer = calculateFactorial(generatedNumber);

                    if (userAnswer == correctAnswer) {
                        resultLabel.setText("¡Correcto! " + generatedNumber + "! = " + correctAnswer);
                    } else {
                        resultLabel.setText("Incorrecto. " + generatedNumber + "! = " + correctAnswer);
                    }

                    generatedNumber = generateNumber();
                    problemLabel.setText("¿Cuál es el factorial de " + generatedNumber + "?");
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Por favor, ingrese un número válido.");
                }
            }
        });

        frame.getContentPane().setBackground(new Color(173, 216, 230));
        checkButton.setBackground(new Color(30, 144, 255));
        checkButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkButton.setForeground(Color.WHITE);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static long calculateFactorial(int number) {
        long result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    public static int generateNumber() {
        return (int) (Math.random() * 21);
    }
}