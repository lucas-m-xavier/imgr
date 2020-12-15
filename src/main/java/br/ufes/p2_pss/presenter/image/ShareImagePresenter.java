/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.image;

import br.ufes.p2_pss.dao.ImageDAO;
import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Notification;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.presenter.home.HomePresenter;
import br.ufes.p2_pss.service.ImageService;
import br.ufes.p2_pss.service.NotificationService;
import br.ufes.p2_pss.service.PermissionService;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.business.EditedImage;
import br.ufes.p2_pss.view.image.ShareImage;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lucas
 */
public class ShareImagePresenter {
    
    private ShareImage view;
    
    private final Image image;
    
    private final HomePresenter home;
    
    private BufferedImage bufferedImage;

    public ShareImagePresenter(HomePresenter homePresenter) {
        this.home = homePresenter;
        this.image = new Image();
        this.view = new ShareImage();
        
        this.view.getBtnClose().addActionListener((ActionEvent arg0) -> {
            this.view.dispose();
        });
        
        this.view.getBtnSearch().addActionListener((ActionEvent arg0) -> {
            findImage();
        });
        
        this.view.getBtnShare().addActionListener((ActionEvent arg0) -> {
            try {
                shareImage();
                this.view.dispose();
            } catch (Exception ex) {
                Logger.getLogger(ShareImagePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void findImage() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens (.jpg)", "jpg");
        String baseDir = System.getProperty("user.home") + "/Desktop";
        File dir = new File(baseDir);
        
        JFileChooser choose = new JFileChooser();
        choose.setCurrentDirectory(dir);
        choose.setFileSelectionMode(JFileChooser.FILES_ONLY);
        choose.setAcceptAllFileFilterUsed(false);
        choose.addChoosableFileFilter(filter);
        
        int returnOption = choose.showOpenDialog(null);
        
        if (returnOption == JFileChooser.APPROVE_OPTION) {
            String dirImage = choose.getSelectedFile().getAbsolutePath();
            image.setSource(dirImage);
            
            bufferedImage = EditedImage.setImage(dirImage, 484, 459);
            this.view.getLblImage().setIcon(new ImageIcon(bufferedImage));
        }
    }
    
    private void shareImage() throws Exception {
        User userHost = home.getUser();
        image.setName(this.view.getTfName().getText());
        
        ImageDAO imageDAO = new ImageDAO();
        imageDAO.save(image);
        
        ImageService imageService = new ImageService();
        image.setId(imageService.findImageBySource(image.getSource()).getId());
        
        UserService userService = new UserService();
        User user = userService.findUserByUsername(this.view.getTfShare().getText());
        
        PermissionService permissionService = new PermissionService();
        Permission permission = new Permission(userHost.getId(), image.getId(), true, false, false);
        permissionService.save(permission);
        
        permissionService = new PermissionService();
        permission = new Permission(user.getId(), image.getId(), true, false, false);
        permissionService.save(permission);
        
        Notification notification = new Notification("O usuário " + userHost.getUsername() +
                " compartilhou a imagem " + image.getName() + " com você!", user);
        
        NotificationService notificationService = new NotificationService();
        notificationService.save(notification);
        
        JOptionPane.showMessageDialog(null, "Imagem compartilhada com sucesso!");
    }

    public ShareImage getView() {
        return view;
    }
    
}
