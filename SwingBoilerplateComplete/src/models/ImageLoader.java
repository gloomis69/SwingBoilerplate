package models;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class ImageLoader {

    private ImageLoader() {
        // Utility class — prevent instantiation
    }

    public static void setThumbnail(JLabel label, String path, int width, int height) {
        ImageIcon icon = loadIcon(path);
        if (icon == null) {
            label.setIcon(null);
            return;
        }

        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(scaled));
    }

    private static ImageIcon loadIcon(String path) {
        // Try classpath first
        var url = ImageLoader.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        }

        // Fallback to file system
        return new ImageIcon(path);
    }
}

