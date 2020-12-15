/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.requestaccess;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Notification;
import br.ufes.p2_pss.model.Solicitation;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.service.NotificationService;
import br.ufes.p2_pss.service.SolicitationService;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.requestaccess.RequestAccessV;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class RequestAcessPresenter {
    
    private RequestAccessV view;
    
    private final Image image;
    
    private final User user;
    
    DefaultListModel dlm = new DefaultListModel();

    public RequestAcessPresenter(Image image, User user) throws Exception {
        this.view = new RequestAccessV();
        this.image = image;
        this.user = user;
        
        this.populate();
        
        this.getView().getBtnClose().addActionListener((ActionEvent arg0) -> {
            this.view.dispose();
        });
        
        this.getView().getBtnSolicitar().addActionListener((ActionEvent arg0) -> {
            try {
                requestAccess();
                this.view.dispose();
            } catch (Exception ex) {
                Logger.getLogger(RequestAcessPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void populate() throws Exception {
        dlm.clear();
        
        ArrayList<User> admins = new ArrayList<>();
        UserService userService = new UserService();
        admins = userService.getAllAdmin();
        
        for(User u : admins) {
            dlm.addElement(u.getUsername());
        }
        
        this.view.getListAdmin().setModel(dlm);
    }

    private void requestAccess() throws Exception {
        UserService userService = new UserService();
        User admin = userService.findUserByUsername(this.view.getListAdmin().getSelectedValue());
        
        
        Solicitation solicitation = new Solicitation(admin.getId(), user.getId(), image.getId(), this.view.getCbView().isSelected(),
                this.view.getCbShare().isSelected(), this.view.getCbDelete().isSelected());
        SolicitationService solicitationService = new SolicitationService();
        solicitationService.save(solicitation);
        
        Notification notification = new Notification("O usuário " + user.getUsername() + " fez uma nova solicitação de acesso!", admin);
        NotificationService notificationService = new NotificationService();
        notificationService.save(notification);
        
        JOptionPane.showMessageDialog(null, "Solicitação realizada com sucesso!");
    }
    
    public RequestAccessV getView() {
        return view;
    }
}
