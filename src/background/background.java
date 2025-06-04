/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package background;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import static java.awt.JobAttributes.DestinationType.FILE;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class background extends JPanel {
    private final Image image;
    
    public background() throws IOException{
      File imgFile = new File("C:/Materi Semester 6/komponen/bg3.png");
        BufferedImage img = ImageIO.read(imgFile);
        image = img;
    }
        
    @Override
    protected void paintComponent(Graphics graph){
        super.paintComponent(graph);
        
        Graphics2D gd = (Graphics2D) graph.create();
        gd.drawImage(image,0,0,getWidth(),getHeight(),null);
        gd.dispose();
    }
    
    }
