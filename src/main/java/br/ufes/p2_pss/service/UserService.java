/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.service;

import br.ufes.p2_pss.model.User;
import br.ufes.p2_pss.repository.UserRepository;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdk.jshell.spi.ExecutionControl;

/**
 *
 * @author Lucas
 */
public class UserService {
    
    private final UserRepository userRepository;

    public UserService() throws Exception{
        this.userRepository = new UserRepository();
    }
    
    public User findUserById(int id) throws Exception {
        return userRepository.findUserById(id);
    }
    
    public ArrayList<User> getAll() throws Exception {
        return this.userRepository.getAll();
    }
    
    public ArrayList<User> getAllAdmin () throws Exception {
        return userRepository.getAllAdmin();
    }
    
    public void save(User user) throws Exception {
        userRepository.save(user);
    }
    
    public User findUserByUsername(String username) throws Exception {
        return userRepository.findUserByUsername(username);
    }
    
    public void saveAdmin(User user) throws Exception {
        userRepository.saveAdmin(user);
    }
    
    public void update(User user) throws Exception {
        userRepository.update(user);
    }
    
    public void updateAdmin(User user) throws Exception {
        userRepository.updateAdmin(user);
    }
    
    public void delete(int id) throws Exception {
        userRepository.delete(id);
    }
    
}
