package Permutations_Combinations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PermutationsCombinationsGame {

    public void createGUI() {
        JFrame frame = new JFrame("Juego de Permutaciones y Combinaciones");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel Texto1Label = new JLabel("PRACTICA EL USO DE LA COMBINATORIA Y PERMUTACION");
        Texto1Label.setBounds(10, 20, 380, 25);
        panel.add(Texto1Label);

        JLabel permLabel = new JLabel("Permutación P(n, r): P(n, r) = n! / (n - r)!");
        permLabel.setBounds(10, 70, 380, 25);
        panel.add(permLabel);

        JLabel combLabel = new JLabel("Combinación C(n, r): C(n, r) = n! / [r! (n - r)!]");
        combLabel.setBounds(10, 100, 380, 25);
        panel.add(combLabel);

        JLabel nLabel = new JLabel("Valor de n:");
        nLabel.setBounds(10, 130, 80, 25);
        panel.add(nLabel);

        JTextField nText = new JTextField(20);
        nText.setBounds(100, 130, 165, 25);
        panel.add(nText);

        JLabel rLabel = new JLabel("Valor de r:");
        rLabel.setBounds(10, 170, 80, 25);
        panel.add(rLabel);

        JTextField rText = new JTextField(20);
        rText.setBounds(100, 170, 165, 25);
        panel.add(rText);

        JButton permButton = new JButton("Calcular Permutación");
        permButton.setBounds(10, 200, 200, 25);
        panel.add(permButton);

        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(10, 230, 380, 25);
        panel.add(resultLabel);

        permButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(nText.getText());
                int r = Integer.parseInt(rText.getText());
                int permResult = PermutationsCombinationsFormula.permutation(n, r);
                resultLabel.setText("Permutación P(" + n + ", " + r + ") = " + permResult);
            }
        });

        JButton combButton = new JButton("Calcular Combinación");
        combButton.setBounds(10, 260, 200, 25);
        panel.add(combButton);

        combButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = Integer.parseInt(nText.getText());
                int r = Integer.parseInt(rText.getText());
                int combResult = PermutationsCombinationsFormula.combination(n, r);
                resultLabel.setText("Combinación C(" + n + ", " + r + ") = " + combResult);
            }
        });

        JButton newWindowButton = new JButton("Empezar a Jugar - Nivel 1");
        newWindowButton.setBounds(250, 300, 200, 25);
        panel.add(newWindowButton);

        newWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame("JUEGO NIVEL 1 - PERMUTACION");
                newFrame.setSize(600, 400);
                newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                String contexto = "<html>Imagina que estás organizando una carrera de autos de juguete con las letras 'ABCDE', <br>donde el orden en que se alinean los autos es importante. ¿De cuántas formas puedes organizar 3 autos? </html>";
                JPanel newPanel = new JPanel();
                newFrame.add(newPanel);
                placeNewWindowComponents(newPanel, "ABCDE", 3, contexto);

                newFrame.setVisible(true);
            }
        });

        JButton newWindowButton2 = new JButton("Empezar a Jugar - Nivel 2");
        newWindowButton2.setBounds(250, 330, 200, 25);
        panel.add(newWindowButton2);

        newWindowButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame2 = new JFrame("JUEGO NIVEL 2 - PERMUTACION");
                newFrame2.setSize(600, 400);
                newFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String contexto = "<html>Ahora imagina que estás decorando una pared con letras de 'FGHIJ', y necesitas <br>seleccionar 4 letras para formar un patrón decorativo. ¿De cuántas formas diferentes puedes organizar estas 4 letras?</html>";
                JPanel newPanel2 = new JPanel();
                newFrame2.add(newPanel2);
                placeNewWindowComponents(newPanel2, "FGHIJ", 4, contexto);  // Nivel 2, diferentes letras y r

                newFrame2.setVisible(true);
            }
        });

        JButton newWindowButton3 = new JButton("Empezar a Jugar - Nivel 3");
        newWindowButton3.setBounds(250, 360, 200, 25);
        panel.add(newWindowButton3);

        newWindowButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame3 = new JFrame("JUEGO NIVEL 3 - PERMUTACION");
                newFrame3.setSize(600, 400);
                newFrame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String contexto = "<html>En una fiesta temática, estás organizando a las personas en grupos de 5 usando <br>las letras 'KLMNO'. ¿De cuántas maneras puedes organizar estos 5 invitados en fila para tomar una foto?</html>";
                JPanel newPanel3 = new JPanel();
                newFrame3.add(newPanel3);
                placeNewWindowComponents(newPanel3, "KLMNO", 5, contexto);  // Nivel 3, diferentes letras y r

                newFrame3.setVisible(true);
            }
        });

        JButton newWindowButton4 = new JButton("Empezar a Jugar - Nivel 4");
        newWindowButton4.setBounds(460, 300, 200, 25);
        panel.add(newWindowButton4);

        newWindowButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame4 = new JFrame("JUEGO NIVEL 4 - COMBINATORIA");
                newFrame4.setSize(600, 400);
                newFrame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String contexto = "<html>Imagina que estás organizando una feria en tu barrio y necesitas seleccionar a <br>3 personas de un grupo de 6 para encargarse de la organización del evento. ¿Cuántas combinaciones posibles puedes hacer para formar el equipo de organizadores?</html>";
                JPanel newPanel4 = new JPanel();
                newFrame4.add(newPanel4);
                placeNewWindowComponents2(newPanel4, 3, "Ana, Juan, Luis, Maria, Carlos, Sofia",6,contexto);  // Nivel 4, r=3 personas

                newFrame4.setVisible(true);
            }
        });

        JButton newWindowButton5 = new JButton("Empezar a Jugar - Nivel 5");
        newWindowButton5.setBounds(460, 330, 200, 25);
        panel.add(newWindowButton5);

        newWindowButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame5 = new JFrame("JUEGO NIVEL 5 - COMBINATORIA");
                newFrame5.setSize(600, 400);
                newFrame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String contexto = "<html>Estás formando un equipo de rescate para una misión especial, y tienes que elegir <br>4 personas de un grupo de 7 candidatos. ¿Cuántas combinaciones diferentes puedes formar para esta misión?</html>";
                JPanel newPanel5 = new JPanel();
                newFrame5.add(newPanel5);
                placeNewWindowComponents2(newPanel5, 4, "Ana, Brandon, Juan, Luis, Maria, Carlos, Sofia",7,contexto);  // Nivel 5, r=6 personas

                newFrame5.setVisible(true);
            }
        });

        JButton newWindowButton6 = new JButton("Empezar a Jugar - Nivel 6");
        newWindowButton6.setBounds(460, 360, 200, 25);
        panel.add(newWindowButton6);

        newWindowButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame6 = new JFrame("JUEGO NIVEL 6 - COMBINATORIA");
                newFrame6.setSize(600, 400);
                newFrame6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


                String contexto = "<html>Imagina que estás planificando un viaje y necesitas seleccionar a 8 personas de <br>un grupo de 10 para ir contigo. ¿Cuántas combinaciones puedes hacer para decidir quiénes irán?</html>";
                JPanel newPanel6 = new JPanel();
                newFrame6.add(newPanel6);
                placeNewWindowComponents2(newPanel6, 8, "Ana, Brandon, Juan, Alan, Jorge, Luis, Maria, Logan,Carlos, Sofia",10,contexto);  // Nivel 6, r=8 personas

                newFrame6.setVisible(true);
            }
        });

    }


    private void placeNewWindowComponents(JPanel panel, String letters, int r,String problem) {
        panel.setLayout(null);

        JLabel newLabelPr = new JLabel(problem);
        newLabelPr.setBounds(5, 0, 500, 100);
        panel.add(newLabelPr);


        JLabel newLabel = new JLabel("<html>Estás organizando las letras: \"" + letters + "\"<br> Elemento a organizar: " + r + "</html>");
        newLabel.setBounds(10, 80, 280, 50);
        panel.add(newLabel);

        JTextField userInput = new JTextField(20);
        userInput.setBounds(10, 140, 165, 25);
        panel.add(userInput);

        JLabel feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(10, 170, 380, 25);
        panel.add(feedbackLabel);

        JButton checkButton = new JButton("Verificar respuesta");
        checkButton.setBounds(10, 200, 200, 25);
        panel.add(checkButton);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userGuess = Integer.parseInt(userInput.getText());
                int correctAnswer = PermutationsCombinationsFormula.permutation(letters.length(), r);
                if (userGuess == correctAnswer) {
                    feedbackLabel.setText("¡Correcto! Hay " + correctAnswer + " formas de organizar " + r + " letras.");
                } else {
                    feedbackLabel.setText("Incorrecto. Hay " + correctAnswer + " formas de organizar " + r + " letras.");
                }
            }
        });

        JButton backButton = new JButton("Volver");
        backButton.setBounds(250, 300, 100, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cerrando la nueva ventana");
                SwingUtilities.getWindowAncestor(panel).dispose();  // Cierra la ventana actual
            }
        });
    }

    private void placeNewWindowComponents2(JPanel panel, int r, String persons, int c,String problem) {
        panel.setLayout(null);

        JLabel newLabelPr = new JLabel(problem);
        newLabelPr.setBounds(5, 0, 500, 100);  //
        panel.add(newLabelPr);


        JLabel newLabel = new JLabel("<html>Estás seleccionando " + r +" personas de: <br> " +persons +" <br> </html>");
        newLabel.setBounds(10, 80, 500, 50);
        panel.add(newLabel);

        JTextField userInput = new JTextField(20);
        userInput.setBounds(10, 140, 165, 25);
        panel.add(userInput);

        JLabel feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(10, 170, 380, 25);
        panel.add(feedbackLabel);

        JButton checkButton = new JButton("Verificar respuesta");
        checkButton.setBounds(10, 200, 200, 25);
        panel.add(checkButton);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int userGuess = Integer.parseInt(userInput.getText());
                int correctAnswer = PermutationsCombinationsFormula.combination(c, r);
                if (userGuess == correctAnswer) {
                    feedbackLabel.setText("¡Correcto! Hay " + correctAnswer + " combinaciones posibles.");
                } else {
                    feedbackLabel.setText("Incorrecto. Hay " + correctAnswer + " combinaciones posibles.");
                }
            }
        });

        JButton backButton = new JButton("Volver");
        backButton.setBounds(250, 300, 100, 25);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cerrando la nueva ventana");
                SwingUtilities.getWindowAncestor(panel).dispose();  // Cierra la ventana actual
            }
        });
    }


}
