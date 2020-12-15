/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.dao;

import br.ufes.p2_pss.business.state.userstate.AdminState;
import br.ufes.p2_pss.business.state.userstate.CommonState;
import br.ufes.p2_pss.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class UserDAO {
    private Connection conn;
    
    public UserDAO() throws Exception {
        try {
            this.conn = ConnectionSQL.getConnection();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public UserDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<User> getAll() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from User");
            rs = ps.executeQuery();

            ArrayList<User> list = new ArrayList<User>();
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                
                user.setUserState(new CommonState(user));
                
                if(rs.getString(4).equals("Administrador"))  user.setUserState(new AdminState(user));

                list.add(user);
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps, rs);
        }
    }
    
    public ArrayList<User> getAllAdmin() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from User where state = 'Administrador'");
            rs = ps.executeQuery();

            ArrayList<User> list = new ArrayList<User>();
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setUserState(new AdminState(user));

                list.add(user);
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps, rs);
        }
    }
    
    public void save(User user) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "INSERT INTO user (username, password)"
                    + " values (?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public User findUserByUsername(String username) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select user.* from user where username = ? ";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            User user = new User();
            
            while(rs.next()) {
                //User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                
                user.setUserState(new CommonState(user));
                if("Administrador".equals(rs.getString(4))) user.setUserState(new AdminState(user));
            }
            
            return user;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public User findUserById(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select user.* from user where idUser = ? ";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            User user = new User();
            
            while(rs.next()) {
                //User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                
                user.setUserState(new CommonState(user));
                if("Administrador".equals(rs.getString(4))) user.setUserState(new AdminState(user));
            }
            
            return user;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void saveAdmin(User user) throws Exception {
        PreparedStatement ps = null;

        try {
            String SQL = "INSERT INTO user (username, password, state)"
                    + " values (?, ?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, "Administrador");
            user.setUserState(new AdminState(user));
            
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void update(User user) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "UPDATE user SET username=?, password=? "
                    + "where idUser = ?;";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
            
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void updateAdmin(User user) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "UPDATE user SET username=?, password=?, state=? "
                    + "where idUser = ?;";
            
            ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, "Administrador");
            ps.setInt(4, user.getId());
            ps.executeUpdate();
            
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void delete(int id) throws Exception {
        PreparedStatement ps = null;
        try {            
            ps = conn.prepareStatement("delete from user where idUser = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro:" + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
}
