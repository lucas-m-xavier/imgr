/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.service;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.repository.PermissionRepository;

/**
 *
 * @author Lucas
 */
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService() throws Exception {
        this.permissionRepository = new PermissionRepository();
    }
    
    public Permission findPermissionByImageAndUser(User user, Image image) throws Exception {
        return permissionRepository.findPermissionByImageAndUser(user, image);
    }
    
    public void save(Permission permission) throws Exception {
        this.permissionRepository.save(permission);
    }
    
    public void update(Permission permission) throws Exception {
        this.permissionRepository.update(permission);
    }
}
