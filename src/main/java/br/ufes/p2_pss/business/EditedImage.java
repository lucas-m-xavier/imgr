/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Lucas
 */
public class EditedImage {

    public static BufferedImage setImage(String source, Integer x, Integer y) {
        Double nX = null;
        Double nY = null;
        Double pprc = null;
        Graphics2D g2d = null;
        BufferedImage image = null, newImage = null;

        try {
            image = ImageIO.read(new File(source));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EditedImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        nX = (double) image.getWidth();

        nY = (double) image.getHeight();
        if (nX >= x) {
            pprc = (nY / nX);
            nX = (double) x;
            nY = (nX * pprc);

            while (nY > y) {
                nX = (double) (--x);
                nY = (nX * pprc);
            }
        } else if (nY >= y) {
            pprc = (nX / nY);
            nY = (double) y;

            while (nX > x) {
                nY = (double) (--y);
                nX = (nY * pprc);
            }
        }

        newImage = new BufferedImage(nX.intValue(), nY.intValue(), BufferedImage.TYPE_INT_RGB);
        g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, nX.intValue(), nY.intValue(), null);

        return newImage;
    }
}
