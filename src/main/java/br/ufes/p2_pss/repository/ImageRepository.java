/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.repository;

import br.ufes.p2_pss.dao.ImageDAO;
import br.ufes.p2_pss.model.Image;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class ImageRepository {
    private final ImageDAO imageDAO;

    public ImageRepository() throws Exception {
        this.imageDAO = new ImageDAO();
    }
    
    public Image findImageBySource(String source) throws Exception {
        return imageDAO.findImageBySource(source);
    }
    
    public ArrayList<Image> getAll() throws Exception {
        return imageDAO.getAll();
    }
   
    public void save(Image image) throws Exception {
        if(image == null) throw new Exception("Imagem não pode ser Nula!");
        this.imageDAO.save(image);
    }

    public void delete(int id) throws Exception {
        this.imageDAO.delete(id);
    }
}
