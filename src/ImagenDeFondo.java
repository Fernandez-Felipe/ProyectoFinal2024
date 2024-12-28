import javax.swing.*;
import java.awt.*;

public class ImagenDeFondo extends JPanel {

    JFrame Ovserve;

    public ImagenDeFondo(JFrame Ovserve){
        this.Ovserve = Ovserve;
        setLayout(null);
    }

    @Override
    public void paint(Graphics g){

        Image img = new ImageIcon(getClass().getResource("ImagenDeFondo.jpg")).getImage();
        g.drawImage(img,0,0,500,485,this);

        setOpaque(false);
        super.paint(g);

    }

}
