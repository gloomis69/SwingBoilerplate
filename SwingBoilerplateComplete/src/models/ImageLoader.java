package models;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class ImageLoader {

    private ImageLoader() {
        // Utility class — prevent instantiation
    }

    /**
     * Loads an image, scales it to a thumbnail size, and assigns it to a JLabel.
     *
     * @param label the JLabel to receive the image
     * @param path  classpath or file-system path to the image
     * @param width thumbnail width
     * @param height thumbnail height
     */
    public static void setThumbnail(
            JLabel label,
            String path,
            int width,
            int height
    ) {
        ImageIcon icon = loadIcon(path);
        if (icon == null) {
            label.setIcon(null);
            return;
        }

        Image scaled = icon.getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH);

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

