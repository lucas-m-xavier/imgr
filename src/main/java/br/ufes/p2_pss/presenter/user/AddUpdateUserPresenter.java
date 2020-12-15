/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.user;

import br.ufes.p2_pss.business.command.AddCommandUser;
import br.ufes.p2_pss.business.command.ICommandUserPresenter;
import br.ufes.p2_pss.business.command.UpdateCommandUser;
import br.ufes.p2_pss.business.observer.UserSubject;
import br.ufes.p2_pss.business.state.cruduserstate.AddUserPresenter;
import br.ufes.p2_pss.business.state.cruduserstate.CrudUserState;
import br.ufes.p2_pss.business.state.cruduserstate.UpdateUserPresenter;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.cruduser.AddUpdateUser;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class AddUpdateUserPresenter extends UserSubject{
    
    private AddUpdateUser crudUser;
    private CrudUserState state;
    private User user;
    private ICommandUserPresenter command;

    //Add
    public AddUpdateUserPresenter() {
        crudUser = new AddUpdateUser();
        this.user = null;
        this.state = new AddUserPresenter(this);
        this.crudUser.getCbAdmin().setVisible(false);
        
        this.crudUser.getBtnSave().addActionListener((ActionEvent arg0) -> {
            try {
                command = new AddCommandUser(state);
                command.execute();
                notifyObserver();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        
        this.crudUser.getjButton1().addActionListener((ActionEvent arg0) -> {
            crudUser.dispose();
        });
        
        this.crudUser.setVisible(true);
    }
    
    //Update
    public AddUpdateUserPresenter(User user) {
        this.crudUser = new AddUpdateUser();
        this.user = user;
        this.state = new UpdateUserPresenter(this);
        this.setUserView(user);
        
        this.crudUser.getBtnSave().addActionListener((ActionEvent arg0) -> {
            try {
                command = new UpdateCommandUser(state);
                command.execute();
                notifyObserver();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });
        
        this.crudUser.getjButton1().addActionListener((ActionEvent arg0) -> {
            crudUser.dispose();
        });
        
        this.crudUser.setVisible(true);
    }

    public void saveUser() {
        user = new User();
        user.setUsername(this.crudUser.getTfUsername().getText());
        user.setPassword(new String(this.crudUser.getPf().getPassword()));
        boolean admin = false;
        boolean usernameExist = false;

        if (!user.validateUsernameAndPassword()) {
            JOptionPane.showMessageDialog(null, "Usuário e senha devem ter no minimo 5 caracteres e no máximo 20");
            return;
        }

        try {
            UserService userService = new UserService();
            if (userService.getAll().size() < 1) {
                admin = true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return;
        }

        try {
            UserService userService = new UserService();
            for (User u : userService.getAll()) {
                if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                    usernameExist = true;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return;
        }

        try {
            UserService userService = new UserService();
            if (usernameExist) {
                JOptionPane.showMessageDialog(null, "Este nome de usuário já está em uso!");
            } else {
                if (admin) {
                    userService.saveAdmin(user);
                } else {
                    userService.save(user);
                }
                JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
                crudUser.dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public AddUpdateUser getView() {
        return crudUser;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public User getUserView() {
        return new User(this.crudUser.getTfUsername().getText(), new String(this.crudUser.getPf().getPassword()));
    }
    
    public void setUserView(User user) {
        this.crudUser.getTfUsername().setText(user.getUsername());
        this.crudUser.getPf().setText(user.getPassword());
    }
}
