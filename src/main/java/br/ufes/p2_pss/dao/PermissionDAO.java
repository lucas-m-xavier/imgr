/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.dao;

import br.ufes.p2_pss.model.Image;
import br.ufes.p2_pss.model.Permission;
import br.ufes.p2_pss.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Lucas
 */
public class PermissionDAO {
    private Connection conn;

    public PermissionDAO() throws Exception {
        try {
            this.conn = ConnectionSQL.getConnection();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public PermissionDAO(Connection conn) {
        this.conn = conn;
    }
    
    public Permission findPermissionByImageAndUser(User user, Image image) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select permission.* from permission where idUser = ? and idImage = ?";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, user.getId());
            ps.setInt(2, image.getId());
            rs = ps.executeQuery();
            
            Permission permission = new Permission();
            
            while(rs.next()) {
                permission.setIdUser(rs.getInt(1));
                permission.setIdImage(rs.getInt(2));
                permission.setShare(rs.getBoolean(3));
                permission.setView(rs.getBoolean(4));
                permission.setDelete(rs.getBoolean(5));
            }
                
            return permission;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void save(Permission permission) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "INSERT INTO permission (idUser, idImage, shared, viewer, deleted)"
                    + " values (?, ?, ?, ?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, permission.getIdUser());
            ps.setInt(2, permission.getIdImage());
            ps.setBoolean(3, permission.isShare());
            ps.setBoolean(4, permission.isView());
            ps.setBoolean(5, permission.isDelete());
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void update(Permission permission) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "UPDATE permission SET shared = ?, viewer = ?, deleted = ? "
                    + "where idUser = ? and idImage = ?;";
            
            ps = conn.prepareStatement(SQL);
            ps.setBoolean(1, permission.isShare());
            ps.setBoolean(2, permission.isView());
            ps.setBoolean(3, permission.isDelete());
            ps.setInt(4, permission.getIdUser());
            ps.setInt(5, permission.getIdImage());
            
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
            ps = conn.prepareStatement("delete from permission where idImage = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro:" + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
}
