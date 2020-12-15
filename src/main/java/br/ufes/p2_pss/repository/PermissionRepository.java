/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.repository;

import br.ufes.p2_pss.dao.PermissionDAO;
import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.User;

/**
 *
 * @author Lucas
 */
public class PermissionRepository {
    private final PermissionDAO permissionDAO;

    public PermissionRepository() throws Exception {
        this.permissionDAO = new PermissionDAO();
    }
    
    public Permission findPermissionByImageAndUser(User user, Image image) throws Exception {
        if(user == null || image == null) throw new Exception("User and image cannot be null!");
        return permissionDAO.findPermissionByImageAndUser(user, image);
    }
    
    public void save(Permission permission) throws Exception {
        if(permission == null) throw new Exception("Permission cannot be null!");
        this.permissionDAO.save(permission);
    }
    
    public void update(Permission permission) throws Exception {
        if(permission == null) throw new Exception("Permission cannot be null!");
        this.permissionDAO.update(permission);
    }
}
