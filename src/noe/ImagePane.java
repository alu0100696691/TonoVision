package noe;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class ImagePane extends JPanel {

    private BufferedImage image;

    public ImagePane(BufferedImage img) {
        try {
            image = img;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dimension getPreferredSize() {
        return image == null ? super.getPreferredSize() : new Dimension(image.getWidth(), image.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;
            g.drawImage(image, x, y, this);
        }
    }

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
    
    
    
}