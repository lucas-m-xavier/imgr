/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.requestaccess;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.Solicitation;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.service.ImageService;
import br.ufes.p2_pss.service.PermissionService;
import br.ufes.p2_pss.service.SolicitationService;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.requestaccess.SolicitationsAuth;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class SolicitationAuthPresenter {
    
    private final SolicitationsAuth view;
    
    private final User admin;
    
    private DefaultTableModel dtm = new DefaultTableModel();

    public SolicitationAuthPresenter(User admin) throws Exception {
        this.view = new SolicitationsAuth();
        this.admin = admin;
        
        dtm = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Usuário", "Imagem", "Visualizar", "Compartilhar", "Deletar"}
        );
        
        view.getTblSolicitation().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.populate();
        
        view.getTblSolicitation().setModel(dtm);
        
        this.view.getBtnClose().addActionListener((ActionEvent arg0) -> {
            try {
                this.delete();
            } catch (Exception ex) {
                Logger.getLogger(SolicitationAuthPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.view.dispose();
        });
        
        this.view.getBtnAccept().addActionListener((ActionEvent arg0) -> {
            try {
                this.accept();
            } catch (Exception ex) {
                Logger.getLogger(SolicitationAuthPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.setVisible(true);
    }
    
    private void populate() throws Exception {
        dtm.setNumRows(0);
        ArrayList<Solicitation> solicitations = new ArrayList<>();
        SolicitationService solicitationService = new SolicitationService();
        solicitations = solicitationService.getAllByAdmin(admin);
        
        UserService userService;
        ImageService imageService;
        User user;
        Image image;
        
        for(Solicitation s : solicitations) {
            
            userService = new UserService();
            user = userService.findUserById(s.getIdUser());
            
            imageService = new ImageService();
            image = imageService.findImageById(s.getIdImage());
            
            dtm.addRow(new Object[]
                            {user.getUsername(), 
                            image.getName(), 
                            s.isView(), 
                            s.isShare(), 
                            s.isDelete()
                        });
        }
    }
    
    private void accept() throws Exception {
        String username = this.view.getTblSolicitation().getModel().getValueAt(this.view.getTblSolicitation().getSelectedRow(), 0).toString();
        
        UserService userService = new UserService();
        User user = userService.findUserByUsername(username);
        
        ImageService imageService = new ImageService();
        Image image = imageService.findImageByName(username);
        
        boolean view = (boolean) this.view.getTblSolicitation().getModel().getValueAt(this.view.getTblSolicitation().getSelectedRow(), 2);
        boolean share = (boolean) this.view.getTblSolicitation().getModel().getValueAt(this.view.getTblSolicitation().getSelectedRow(), 3);
        boolean delete= (boolean) this.view.getTblSolicitation().getModel().getValueAt(this.view.getTblSolicitation().getSelectedRow(), 4);
        
        Permission permission = new Permission(user.getId(), image.getId(), view, delete, share);
        PermissionService permissionService = new PermissionService();
        permissionService.update(permission);
        
        SolicitationService ss = new SolicitationService();
        Solicitation solicitation = ss.findSolicitationByImageAndUser(user, image);
        ss = new SolicitationService();
        ss.delete(solicitation.getId());
        
        JOptionPane.showMessageDialog(null, "Permissão concedida com sucesso!");
    }

    public SolicitationsAuth getView() {
        return view;
    }
    
    private void delete() throws Exception {
        ArrayList<Solicitation> solicitations = new ArrayList<>();
        SolicitationService solicitationService = new SolicitationService();
        solicitations = solicitationService.getAllByAdmin(admin);
        
        for (Solicitation s : solicitations) {
            solicitationService = new SolicitationService();
            solicitationService.delete(s.getId());
        }
    }
}
