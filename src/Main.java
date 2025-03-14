import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Juego de Matemáticas Discretas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel mainTitle = new JLabel("Juego de Matemáticas Discretas");
        mainTitle.setFont(new Font("Serif", Font.BOLD, 24));
        mainTitle.setAlignmentX(CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Conteo Combinatorio y Probabilidad");
        subtitle.setFont(new Font("Serif", Font.ITALIC, 18));
        subtitle.setAlignmentX(CENTER_ALIGNMENT);

        mainPanel.add(mainTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(subtitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));


        buttonPanel.add(crearBoton("Principios del Conteo", "Counting.CountingGame", "icono1.png"));
        buttonPanel.add(crearBoton("Factorial", "Factorial.FactorialGame", "icono2.png"));
        buttonPanel.add(crearBoton("Permutaciones y Combinaciones", "Permutations_Combinations.PermutationsCombinationsGame", "icono3.png"));
        buttonPanel.add(crearBoton("Probabilidad", "Probability.ProbabilityCalculator", "icono4.png"));

        mainPanel.add(buttonPanel);


        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));
        mainPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        mainPanel.add(btnSalir);

        add(mainPanel);
    }

    private JButton crearBoton(String texto, String nombreClase, String nombreImagen) {
        JButton boton = new JButton(texto);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        if (nombreImagen != null) {
            ImageIcon icono = new ImageIcon("resources/" + nombreImagen);
            if (icono.getIconWidth() == -1) {
                System.err.println("No se encontró la imagen: " + nombreImagen);
            } else {
                Image imagenEscalada = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                boton.setIcon(new ImageIcon(imagenEscalada));
                boton.setVerticalTextPosition(SwingConstants.BOTTOM);
                boton.setHorizontalTextPosition(SwingConstants.CENTER);
            }
        }


        boton.addActionListener(e -> ejecutarClase(nombreClase));
        return boton;
    }

    private void ejecutarClase(String nombreClase) {
        try {
            Class<?> clazz = Class.forName(nombreClase);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            clazz.getMethod("createGUI").invoke(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Clase no encontrada: " + nombreClase);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Método no encontrado en la clase: " + nombreClase);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al ejecutar " + nombreClase + ": " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main menu = new Main();
            menu.setVisible(true);
        });
    }
}
