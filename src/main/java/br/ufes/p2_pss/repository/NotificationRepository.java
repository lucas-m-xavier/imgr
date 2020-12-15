/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.repository;

import br.ufes.p2_pss.dao.NotificationDAO;
import br.ufes.p2_pss.model.Notification;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class NotificationRepository {
    
    private final NotificationDAO notificationDAO;

    public NotificationRepository() throws Exception {
        this.notificationDAO = new NotificationDAO();
    }
    
    public ArrayList<Notification> getAllByUserId (int id) throws Exception {
        return notificationDAO.getAllByUserId(id);
    }
    
    public void save(Notification notification) throws Exception {
        if (notification == null) throw new Exception("Notification cannot be null!");
        
        notificationDAO.save(notification);
    }
    
    public void deleteAllByUserId(int id) throws Exception {
        notificationDAO.deleteAllByUserId(id);
    }
}
