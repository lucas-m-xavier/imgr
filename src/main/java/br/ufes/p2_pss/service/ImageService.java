/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.service;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.repository.ImageRepository;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService() throws Exception {
        this.imageRepository = new ImageRepository();
    }
    
    public Image findImageBySource(String source) throws Exception {
        return imageRepository.findImageBySource(source);
    }
    
    public ArrayList<Image> getAll() throws Exception {
        return imageRepository.getAll();
    }
   
    public void save(Image image) throws Exception {
        this.imageRepository.save(image);
    }

    public void delete(int id) throws Exception {
        this.imageRepository.delete(id);
    }
}
