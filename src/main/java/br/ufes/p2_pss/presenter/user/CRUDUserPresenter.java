/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.user;

import br.ufes.p2_pss.business.command.DeleteCommandUser;
import br.ufes.p2_pss.business.command.ICommandUserPresenter;
import br.ufes.p2_pss.business.observer.IUserObserver;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.Home;
import br.ufes.p2_pss.view.cruduser.CRUDUser;
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
public class CRUDUserPresenter implements IUserObserver {
    private CRUDUser view;
    
    private final DefaultTableModel tmUsers;
    
    private ArrayList<User> users;
    
    private ICommandUserPresenter command;

    public CRUDUserPresenter(Home home) throws Exception {
        
        this.view = new CRUDUser();
        
        tmUsers = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Username", "Password"}
        );
        
        this.view.getBtnAdd().addActionListener((ActionEvent arg0) -> {
            AddUpdateUserPresenter a = new AddUpdateUserPresenter();
            a.registerObserver(this);
            Home.Desktop.add(a.getView());
            a.getView().getCbAdmin().setVisible(true);
            a.getView().setVisible(true);
        });
        
        this.view.getBtnUpdate().addActionListener((ActionEvent arg0) -> {
            User u = getUserInTable();
            
            if(u != null) {
                AddUpdateUserPresenter a = new AddUpdateUserPresenter(u);
                a.registerObserver(this);
                Home.Desktop.add(a.getView());
                a.getView().setVisible(true);
            }
        });
        
        this.view.getBtnDelete().addActionListener((ActionEvent arg0) -> {
            User u = getUserInTable();
            if (u != null) {
                int response = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja deletar o usuario " + u.getUsername() + "?", "Excluir usuário", JOptionPane.YES_OPTION);
                
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        command = new DeleteCommandUser(u, view);
                        command.execute();
                        completeTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        });
        
        this.completeTable();
        
        view.getTbl().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        view.setVisible(true);
        
    }
    
    private void completeTable() throws Exception {
        tmUsers.setNumRows(0);
        UserService userService = new UserService();
        this.users = userService.getAll();
        
        this.users.forEach(u -> {
            tmUsers.addRow(new Object[]{u.getUsername(), u.getPassword()});
        });
        
        this.view.getTbl().setModel(tmUsers);
    }

    public CRUDUser getView() {
        return view;
    }
    
    private User getUserInTable() {
        return users.get(view.getTbl().getSelectedRow());
    }

    @Override
    public void update() {
        try {
            this.completeTable();
        } catch (Exception ex) {
            Logger.getLogger(CRUDUserPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
