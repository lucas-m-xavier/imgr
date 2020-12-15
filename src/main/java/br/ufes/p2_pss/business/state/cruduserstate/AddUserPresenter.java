/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.state.cruduserstate;

import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.presenter.user.AddUpdateUserPresenter;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.cruduser.AddUpdateUser;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class AddUserPresenter extends CrudUserState {

    public AddUserPresenter(AddUpdateUserPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void saveUser() {
        boolean admin = false;
        boolean usernameExist = false;
        AddUpdateUser view = this.presenter.getView();
        
        User user = this.presenter.getUserView();
        
        if (!user.validateUsernameAndPassword()) {
            JOptionPane.showMessageDialog(null, "Usuário e senha devem ter no minimo 5 caracteres e no máximo 20");
            return;
        }
        
        try {
            UserService userService = new UserService();
            if (userService.getAll().size() < 1 || presenter.getView().getCbAdmin().isSelected()) {
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
                presenter.getView().dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
