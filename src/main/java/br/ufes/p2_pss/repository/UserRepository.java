/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.repository;

import br.ufes.p2_pss.dao.UserDAO;
import br.ufes.p2_pss.model.User;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class UserRepository {
    
    private final UserDAO userDAO;

    public UserRepository() throws Exception {
        this.userDAO = new UserDAO();
    }
    
    public User findUserById(int id) throws Exception {
        return userDAO.findUserById(id);
    }
    
    public ArrayList<User> getAll () throws Exception {
        return userDAO.getAll();
    }
    
    public ArrayList<User> getAllAdmin () throws Exception {
        return userDAO.getAllAdmin();
    }
    
    public void save(User user) throws Exception {
        if (user == null) throw new Exception("User cannot be null!");
        
        userDAO.save(user);
    }
    
    public User findUserByUsername(String username) throws Exception {
        return userDAO.findUserByUsername(username);
    }
    
    public void saveAdmin(User user) throws Exception {
        if (user == null) throw new Exception("User cannot be null!");
        
        userDAO.saveAdmin(user);
    }
    
    public void update(User user) throws Exception {
        if (user == null) throw new Exception("User cannot be null!");
        
        userDAO.update(user);
    }
    
    public void updateAdmin(User user) throws Exception {
        if (user == null) throw new Exception("User cannot be null!");
        
        userDAO.updateAdmin(user);
    }
    
    public void delete(int id) throws Exception {
        userDAO.delete(id);
    }
    
}
