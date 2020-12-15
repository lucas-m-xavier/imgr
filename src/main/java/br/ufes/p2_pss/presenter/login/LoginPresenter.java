/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.login;

import br.ufes.p2_pss.presenter.home.HomePresenter;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.presenter.user.AddUpdateUserPresenter;
import br.ufes.p2_pss.service.UserService;
import br.ufes.p2_pss.view.Home;
import br.ufes.p2_pss.view.login.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Lucas
 */
public class LoginPresenter {
    
    private Login login;
    
    private HomePresenter homePresenter;

    public LoginPresenter(JDesktopPane desktop) {
        
        this.login = new Login();
        
        this.login.getBtnAcess().addActionListener((ActionEvent arg0) -> {
            if (verificarLogin()) {
                JOptionPane.showMessageDialog(null, "Bem vindo!");
                try {
                    homePresenter.showFooter();
                } catch (Exception ex) {
                    Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
                homePresenter.showMenu();
                login.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
            }
        });
        
        this.login.getBtnNewUser().addActionListener((ActionEvent arg0) -> {
            AddUpdateUserPresenter a = new AddUpdateUserPresenter();
            Home.Desktop.add(a.getView());
            a.getView().setVisible(true);
        });
        
        this.login.getBtnClose2().addActionListener((ActionEvent arg0) -> {
            login.dispose();
        });
        
        this.login.setVisible(true);
        desktop.add(login);
    }
    
    public LoginPresenter(HomePresenter homePresenter) {
        this.login = new Login();
        this.homePresenter = homePresenter;
        
        this.login.getBtnAcess().addActionListener((ActionEvent arg0) -> {
            if (verificarLogin()) {
                JOptionPane.showMessageDialog(null, "Bem vindo!");
                try {
                    homePresenter.showFooter();
                } catch (Exception ex) {
                    Logger.getLogger(LoginPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
                homePresenter.showMenu();
                login.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
            }
        });
        
        this.login.getBtnNewUser().addActionListener((ActionEvent arg0) -> {
            AddUpdateUserPresenter a = new AddUpdateUserPresenter();
            Home.Desktop.add(a.getView());
            a.getView().setVisible(true);
        });
        
        this.login.getBtnClose2().addActionListener((ActionEvent arg0) -> {
            login.dispose();
        });
        
        this.login.setVisible(true);
    }
    
    public boolean verificarLogin() {
        
        try {
            UserService userService = new UserService();
            
            for(User u : userService.getAll()) {
                if(u.login(login.getTfIdentificacao().getText(), new String(login.getPf().getPassword()))) {
                    homePresenter.setUser(u);
                    return true;
                }
            }
        }   catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return false;
    }

    public Login getLogin() {
        return login;
    }
}
