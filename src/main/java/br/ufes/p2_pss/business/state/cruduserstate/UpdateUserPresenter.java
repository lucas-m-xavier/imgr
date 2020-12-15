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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class UpdateUserPresenter extends CrudUserState {

    public UpdateUserPresenter(AddUpdateUserPresenter presenter) {
        super(presenter);
    }
    
    @Override
    public void saveUser() {
        AddUpdateUser view = this.presenter.getView();
        
        User user = this.presenter.getUserView();
        
        try {
            UserService userService = new UserService();
            user.setId(this.presenter.getUser().getId());
            
            if(presenter.getView().getCbAdmin().isSelected())   userService.updateAdmin(user);
            else userService.update(user);
        } catch (Exception ex) {
            Logger.getLogger(AddUserPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JOptionPane.showMessageDialog(view,"Usuário " + user.getUsername()+
                " atualizado com sucesso!", "Atualizado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
        presenter.getView().dispose();
    }
}
