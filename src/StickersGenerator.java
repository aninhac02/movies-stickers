import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StickersGenerator {

    void generate(InputStream inputStream, String fileName, String movieName) throws IOException {
        //leitura da imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + 200;

        var newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        var font = new Font(Font.SANS_SERIF, Font.BOLD, 80);
        graphics.setFont(font);
        graphics.drawString(movieName, 0, newHeight - 100);

        ImageIO.write(newImage, "png", new File(fileName));
    }
}
