/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.service;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Notification;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.Solicitation;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.repository.NotificationRepository;
import br.ufes.p2_pss.repository.PermissionRepository;
import br.ufes.p2_pss.repository.SolicitationRepository;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class SolicitationService {
    
    private final SolicitationRepository solicitationRepository;
    private final PermissionRepository permissionRepository;
    private final NotificationRepository notificationRepository;

    public SolicitationService() throws Exception {
        this.solicitationRepository = new SolicitationRepository();
        this.permissionRepository = new PermissionRepository();
        this.notificationRepository = new NotificationRepository();
    }
    
    public ArrayList<Solicitation> getAllByAdmin(User admin) throws Exception {
        return solicitationRepository.getAllByAdmin(admin);
    }
    
    public Solicitation findSolicitationByImageAndUser(User user, Image image) throws Exception {
        return solicitationRepository.findSolicitationByImageAndUser(user, image);
    }
    
    public void accept(Solicitation solicitation) throws Exception{
        Permission accept = new Permission();
        accept.setIdUser(solicitation.getIdUser());
        accept.setIdImage(solicitation.getIdImage());
        accept.setView(solicitation.isView());
        accept.setDelete(solicitation.isDelete());
        accept.setShare(solicitation.isShare());
        
        this.permissionRepository.update(accept);
        this.solicitationRepository.delete(solicitation.getId());
        
        UserService userService = new UserService();
        User user = userService.findUserById(solicitation.getIdUser());
        
        this.notificationRepository.save(new Notification("Você teve uma solicitação aceita pelo administrador!", user));
    }
    
    public void save(Solicitation solicitation) throws Exception {
        solicitationRepository.save(solicitation);
    }
    
    public void delete(int id) throws Exception {
        solicitationRepository.delete(id);
    }
}
