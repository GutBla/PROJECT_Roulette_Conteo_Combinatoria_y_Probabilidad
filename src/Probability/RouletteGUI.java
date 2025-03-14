package Probability;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class RouletteGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RouletteFrame frame = new RouletteFrame();
            frame.setVisible(true);
        });
    }
}

class RouletteFrame extends JFrame {
    private RoulettePanel roulettePanel;
    private RouletteGame game;

    public RouletteFrame() {
        setTitle("Ruleta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(new BorderLayout());

        roulettePanel = new RoulettePanel();
        game = new RouletteGame(roulettePanel);

        add(roulettePanel, BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        JButton startButton = new JButton("Girar");
        JButton pauseButton = new JButton("Pausar");

        startButton.setBackground(new Color(239, 183, 16));
        pauseButton.setBackground(new Color(228, 7, 3));

        controlPanel.add(startButton);
        controlPanel.add(pauseButton);

        startButton.addActionListener(e -> game.start());
        pauseButton.addActionListener(e -> game.togglePause(pauseButton));

        return controlPanel;
    }
}

class RouletteGame {
    private Thread gameThread;
    private boolean isPaused = false;
    private boolean isRunning = false;
    private RoulettePanel panel;
    private Ball ball;
    private RouletteWheel wheel;

    public RouletteGame(RoulettePanel panel) {
        this.panel = panel;
        ball = new Ball();
        wheel = new RouletteWheel();
    }

    public void start() {
        if (gameThread == null || !gameThread.isAlive()) {
            isPaused = false;
            isRunning = true;
            gameThread = new Thread(this::playGame);
            gameThread.start();
        }
    }

    public void togglePause(JButton pauseButton) {
        if (!isRunning) return;
        isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Continuar" : "Pausar");

        if (!isPaused) {
            synchronized (gameThread) {
                gameThread.notify();
            }
        }
    }

    private void playGame() {
        Random rand = new Random();
        double ballSpeed = rand.nextDouble() * 0.05 + 0.05;
        double wheelSpeed = ballSpeed / 2;

        while (isRunning) {
            synchronized (gameThread) {
                if (isPaused) {
                    try {
                        gameThread.wait();
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            ball.update(ballSpeed);
            wheel.update(wheelSpeed);
            ballSpeed *= 0.99;

            if (ballSpeed < 0.001) {
                isRunning = false;
            }

            panel.setBallAndWheelAngles(ball.getAngle(), wheel.getAngle());

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

class RouletteWheel {
    private double angle = 0.0;

    public double getAngle() {
        return angle;
    }

    public void update(double speed) {
        angle -= speed;
    }
}

class Ball {
    private double angle = 0.0;

    public double getAngle() {
        return angle;
    }

    public void update(double speed) {
        angle += speed;
    }
}

class RoulettePanel extends JPanel {
    private static final int numbers[] = {
            0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27,
            13, 36, 11, 30, 8, 23, 10, 5, 24, 16, 33, 1,
            20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3,
            26
    };

    private double ballAngleRad = 0.0;
    private double wheelAngleRad = 0.0;

    public RoulettePanel() {
        setBackground(new Color(4, 80, 32));
    }

    public void setBallAndWheelAngles(double ballAngle, double wheelAngle) {
        this.ballAngleRad = ballAngle;
        this.wheelAngleRad = wheelAngle;
        repaint();
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D) gr;

        int w = getWidth();
        int h = getHeight();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int offsetX = 20;
        int offsetY = 20;
        int diameter = Math.min(w - 2 * offsetX, h - 2 * offsetY);
        int radius = diameter / 2;
        int centerX = offsetX + radius;
        int centerY = offsetY + radius;

        g.setColor(new Color(239, 183, 16));
        g.setStroke(new BasicStroke(8));
        g.drawOval(offsetX, offsetY, diameter, diameter);

        g.setColor(new Color(239, 183, 16));
        g.fillOval(offsetX + 5, offsetY + 5, diameter - 10, diameter - 10);

        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.setColor(Color.WHITE);
        String text = "Ruleta";
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g.drawString(text, centerX - textWidth / 2, centerY + textHeight / 4);

        double angleRadDelta = Math.PI * 2 / numbers.length;
        Path2D segment = createSegment(centerX, centerY, radius - 30, radius, -angleRadDelta / 2, angleRadDelta / 2);

        AffineTransform originalAT = g.getTransform();
        g.rotate(wheelAngleRad, centerX, centerY);

        for (int i = 0; i < numbers.length; i++) {
            double angleRad0 = i * angleRadDelta;
            g.setTransform(originalAT);
            g.rotate(angleRad0 + wheelAngleRad, centerX, centerY);

            if (i == getBallIndex()) {
                g.setColor(new Color(155, 155, 155));
            } else if (i == 0) {
                g.setColor(new Color(0, 192, 0));
            } else if ((i & 1) == 1) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.DARK_GRAY);
            }

            g.fill(segment);
            String numberString = String.valueOf(numbers[i]);
            int delta = g.getFontMetrics().stringWidth(numberString) / 2;
            g.setColor(Color.WHITE);
            g.drawString(numberString, centerX - delta, centerY - radius + 20);
        }

        g.setTransform(originalAT);
    }

    private int getBallIndex() {
        double angleRadDelta = Math.PI * 2 / numbers.length;
        double normalizedBallAngle = (ballAngleRad + Math.PI * 2) % (Math.PI * 2);
        return (int) (normalizedBallAngle / angleRadDelta) % numbers.length;
    }

    private static Path2D createSegment(int centerX, int centerY, int innerRadius, int outerRadius, double angleRad0, double angleRad1) {
        Arc2D.Double arcInner = new Arc2D.Double(centerX - innerRadius, centerY - innerRadius, innerRadius * 2, innerRadius * 2,
                Math.toDegrees(angleRad0), Math.toDegrees(angleRad1 - angleRad0), Arc2D.OPEN);

        Arc2D.Double arcOuter = new Arc2D.Double(centerX - outerRadius, centerY - outerRadius, outerRadius * 2, outerRadius * 2,
                Math.toDegrees(angleRad1), Math.toDegrees(angleRad0 - angleRad1), Arc2D.OPEN);

        Path2D.Double path = new Path2D.Double();
        path.append(arcInner, true);
        path.append(arcOuter, true);
        path.closePath();

        AffineTransform at = AffineTransform.getRotateInstance(-Math.PI / 2, centerX, centerY);
        path.transform(at);

        return path;
    }
}
