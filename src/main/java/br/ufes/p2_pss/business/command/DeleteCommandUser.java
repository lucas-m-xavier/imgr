/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.business.command;

import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.cruduser.CRUDUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class DeleteCommandUser implements ICommandUserPresenter{

    private User user;
    private CRUDUser view;

    public DeleteCommandUser(User user, CRUDUser view) {
        this.user = user;
        this.view = view;
    }
    
    @Override
    public void execute() {
        try {
            UserService userService = new UserService();
            
            userService.delete(this.user.getId());
            
            JOptionPane.showMessageDialog(view, "Usuário " + user.getUsername()+ " removido com sucesso!",
                    "Exclusão concluida", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception ex) {
            Logger.getLogger(DeleteCommandUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
