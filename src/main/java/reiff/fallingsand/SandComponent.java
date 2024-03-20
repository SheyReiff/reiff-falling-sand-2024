package reiff.fallingsand;

import javax.swing.*;
import java.awt.*;

public class SandComponent extends JComponent {

    private final Sand sand;
    private final Color standardSandColor = Color.decode("#C2B280");

    public SandComponent(Sand sand) {
        this.sand = sand;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(standardSandColor);
        for (int y = 0; y < sand.getHeight(); y++) {
            for (int x = 0; x < sand.getWidth(); x++) {
                if (sand.get(x, y) == 1) {
                    g.fillRect(x, y, 3, 3);
                }
            }
        }
    }
}
