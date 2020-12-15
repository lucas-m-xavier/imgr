/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.service;

import br.ufes.p2_pss.model.Notification;
import br.ufes.p2_pss.repository.NotificationRepository;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class NotificationService {
    
    private final NotificationRepository notificationRepository;

    public NotificationService() throws Exception {
        this.notificationRepository = new NotificationRepository();
    }
    
    public ArrayList<Notification> getAllByUserId(int id) throws Exception {
        return this.notificationRepository.getAllByUserId(id);
    }
    
    public void save(Notification notification) throws Exception {
        notificationRepository.save(notification);
    }
    
    public void deleteAllByUserId(int id) throws Exception {
        notificationRepository.deleteAllByUserId(id);
    }
    
}
