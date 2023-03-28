import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
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

        var font = new Font("Impact", Font.BOLD, 64);
        graphics.setFont(font);

        //calculo para centralizar o texto na imagem

        FontMetrics metrics = graphics.getFontMetrics();
        int textWidth = metrics.stringWidth(movieName);
        int textHeight = metrics.getHeight();

        int x = (newImage.getWidth() - textWidth) / 2;
        int y = newHeight - 100;
        // int y = (newImage.getHeight() - textHeight) / 2  + metrics.getAscent();

        //fim do calculo obs: nesse caso só o x é necessário por conta da img modificada, esse y seria para centralizar totalmente o texto

        graphics.drawString(movieName, x, y);

        // add contorno ao texto:

        TextLayout textLayout = new TextLayout(movieName, font, graphics.getFontRenderContext());
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(x, y);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.BLUE);
        graphics.draw(outline);
        graphics.setClip(outline);

        graphics.dispose();

        var dir = new File("src/stickers");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        ImageIO.write(newImage, "png", new File(dir, fileName));
    }
}
