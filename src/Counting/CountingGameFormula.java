package Counting;

import javax.swing.*;

public class CountingGameFormula {
    public void createGUI() {
        String[] tamanos = {"Pequeña", "Mediana", "Grande"};
        String[] masas = {"Delgada", "Gruesa", "Integral"};

        int numTamanos = tamanos.length;
        int numMasas = masas.length;

        int sumaCombinaciones = numTamanos + numMasas;
        int multiplicacionCombinaciones = numTamanos * numMasas;

        String mensaje = "Combinaciones posibles:\n" +
                "Suma de opciones: " + sumaCombinaciones + "\n" +
                "Multiplicación de opciones: " + multiplicacionCombinaciones;

        JOptionPane.showMessageDialog(null, mensaje, "Resultados de Combinaciones", JOptionPane.INFORMATION_MESSAGE);
    }
}
