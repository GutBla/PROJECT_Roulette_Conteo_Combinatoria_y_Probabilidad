package Counting;

import javax.swing.*;

public class CountingGame {
    public void createGUI() {
        String[] tamanos = {"Pequeña", "Mediana", "Grande"};
        String[] masas = {"Delgada", "Gruesa", "Integral"};
        String[] ingredientes = {"Queso", "Pepperoni", "Champiñones", "Jamón", "Piña"};

        JOptionPane.showMessageDialog(null, "Crea tu Pizza Matemática");

        String tamanoSeleccionado = (String) JOptionPane.showInputDialog(null,
                "Selecciona el tamaño de tu pizza:",
                "Tamaño de Pizza",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tamanos,
                tamanos[0]);

        String masaSeleccionada = (String) JOptionPane.showInputDialog(null,
                "Selecciona el tipo de masa:",
                "Tipo de Masa",
                JOptionPane.QUESTION_MESSAGE,
                null,
                masas,
                masas[0]);

        String mensajeIngredientes = "Selecciona tus ingredientes (separa las opciones con comas, por ejemplo: 1,3,5):\n";
        for (int i = 0; i < ingredientes.length; i++) {
            mensajeIngredientes += (i + 1) + ". " + ingredientes[i] + "\n";
        }
        String ingredientesSeleccionados = JOptionPane.showInputDialog(null, mensajeIngredientes, "Ingredientes de la Pizza", JOptionPane.QUESTION_MESSAGE);

        String[] indicesIngredientes = ingredientesSeleccionados.split(",");
        int numIngredientes = indicesIngredientes.length;

        int numTamanos = tamanos.length;
        int numMasas = masas.length;

        int combinaciones = numTamanos * numMasas * numIngredientes;

        JOptionPane.showMessageDialog(null, "Has seleccionado: Pizza " + tamanoSeleccionado + " con masa " + masaSeleccionada + " y " + numIngredientes + " ingredientes adicionales.\n"
                + "Combinaciones posibles con tus selecciones: " + combinaciones + " tipos de pizza diferentes.");
    }
}
