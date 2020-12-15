package br.ufes.p2_pss.presenter.home;

import br.ufes.p2_pss.business.state.userstate.AdminState;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.presenter.image.ShareImagePresenter;
import br.ufes.p2_pss.presenter.image.ViewImagesPresenter;
import br.ufes.p2_pss.presenter.user.CRUDUserPresenter;
import br.ufes.p2_pss.presenter.login.LoginPresenter;
import br.ufes.p2_pss.presenter.notification.NotificationPresenter;
import br.ufes.p2_pss.presenter.requestaccess.SolicitationAuthPresenter;
import br.ufes.p2_pss.service.NotificationService;
import br.ufes.p2_pss.view.Home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class HomePresenter {
    
    private Home home = new Home();
    private User user = null;

    public HomePresenter() {
        this.home.setVisible(true);
        HomePresenter homePresenter = this;
        this.hideFooter();
        this.hideMenu();
        
        LoginPresenter a = new LoginPresenter(homePresenter);
        homePresenter.home.Desktop.add(a.getLogin());
        a.getLogin().setVisible(true);
        
        home.getBtnNotification().addActionListener((ActionEvent arg0) -> {
            NotificationPresenter a1 = null;
            try {
                a1 = new NotificationPresenter(user);
                homePresenter.home.Desktop.add(a1.getView());
                a1.getView().setVisible(true);
                home.getBtnNotification().setText("0");
            } catch (Exception ex) {
                Logger.getLogger(HomePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        home.getItemAutorizarAcesso().addActionListener((ActionEvent arg0) -> {
            SolicitationAuthPresenter a1 = null;
            try {
                a1 = new SolicitationAuthPresenter(this.user);
                homePresenter.home.Desktop.add(a1.getView());
                a1.getView().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(HomePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        home.getMenuViewImages().addActionListener((ActionEvent arg0) -> {
            ViewImagesPresenter a1 = null;
            try {
                a1 = new ViewImagesPresenter(homePresenter);
                homePresenter.home.Desktop.add(a1.getView());
                a1.getView().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(HomePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        home.getItemVisualizarUsuarios().addActionListener((ActionEvent arg0) -> {
            CRUDUserPresenter a1 = null;
            try {
                a1 = new CRUDUserPresenter(home);
                homePresenter.home.Desktop.add(a1.getView());
                a1.getView().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(HomePresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        home.getItemShare().addActionListener((ActionEvent arg0) -> {
            ShareImagePresenter a1 = new ShareImagePresenter(homePresenter);
            homePresenter.home.Desktop.add(a1.getView());
            a1.getView().setVisible(true);
        });
        
        home.getItemEntrar().addActionListener((ActionEvent arg0) -> {
            LoginPresenter a1 = new LoginPresenter(homePresenter);
            homePresenter.home.Desktop.add(a1.getLogin());
            a1.getLogin().setVisible(true);
        });
        
        home.getItemSair().addActionListener((ActionEvent arg0) -> {
            user = null;
            hideMenu();
            hideFooter();
        });
    }
    
    
    public void hideMenu() {
        this.home.getMenuAdmin().setVisible(false);
        this.home.getMenuImagens().setVisible(false);
        this.home.getItemSair().setVisible(false);
        this.home.getItemEntrar().setVisible(true);
    }
    
    public void hideFooter() {
        this.home.getLblUsername().setVisible(false);
        this.home.getLblState().setVisible(false);
        this.home.getBtnNotification().setVisible(false);
    }
    
    public void showFooter() throws Exception {
        this.home.getLblUsername().setText(this.user.getUsername());
        
        this.home.getLblState().setText("Usuário Comum");
        
        if(this.user.getUserState() instanceof AdminState) this.home.getLblState().setText("Administrador");
        
        this.home.getLblUsername().setVisible(true);
        this.home.getLblState().setVisible(true);
        
        NotificationService notificationService = new NotificationService();
        int notificationNumber = notificationService.getAllByUserId(user.getId()).size();
        this.home.getBtnNotification().setText(String.valueOf(notificationNumber));
        
        this.home.getBtnNotification().setVisible(true);
    }
    
    public void showMenu() {
        this.home.getMenuImagens().setVisible(true);
        this.home.getItemEntrar().setVisible(false);
        this.home.getItemSair().setVisible(true);
        if(this.user.getUserState() instanceof AdminState) this.home.getMenuAdmin().setVisible(true);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Home getHome() {
        return home;
    }
}
