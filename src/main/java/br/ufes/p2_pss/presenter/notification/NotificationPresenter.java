/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.presenter.notification;

import br.ufes.p2_pss.model.Notification;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.service.NotificationService;
import br.ufes.p2_pss.view.notification.NotificationView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lucas
 */
public class NotificationPresenter {
    
    private NotificationView view;
    private ArrayList<Notification> notifications;
    private final DefaultTableModel tblNotifications;
    private final User user;

    public NotificationPresenter(User user) throws Exception {
        this.user = user;
        
        this.view = new NotificationView();
        
        this.view.getBtnClose().addActionListener((ActionEvent arg0) -> {
            try {
                NotificationService notificationService = new NotificationService();
                notificationService.deleteAllByUserId(user.getId());
            } catch (Exception ex) {
                Logger.getLogger(NotificationPresenter.class.getName()).log(Level.SEVERE, null, ex);
            }
            view.dispose();
        });
        
        tblNotifications = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Descrição"}
        );
        
        view.getTbl().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.completeTable();
        
        view.getTbl().setModel(tblNotifications);
        
        view.setVisible(true);
        
    }

    public NotificationView getView() {
        return view;
    }
    
    private void completeTable() throws Exception {
        tblNotifications.setNumRows(0);
        NotificationService notificationService = new NotificationService();
        notifications = notificationService.getAllByUserId(user.getId());
        notifications.forEach(n -> {
            tblNotifications.addRow(new Object[]{n.getDescription()});
        });
    }
}
