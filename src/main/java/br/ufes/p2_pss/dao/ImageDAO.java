/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.dao;

import br.ufes.p2_pss.model.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class ImageDAO {
    private Connection conn;

    public ImageDAO() throws Exception {
        try {
            this.conn = ConnectionSQL.getConnection();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public ImageDAO(Connection conn) {
        this.conn = conn;
    }
    
    public Image findImageById(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select image.* from image where idImage = ? ";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            Image image = new Image();
            
            while(rs.next()) {
                //User user = new User();
                image.setId(rs.getInt(1));
                image.setSource(rs.getString(2));
                image.setName(rs.getString(3));
            }
            
            return image;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public Image findImageByName(String name) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select image.* from image where name = ? ";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, name);
            rs = ps.executeQuery();
            
            Image image = new Image();
            
            while(rs.next()) {
                //User user = new User();
                image.setId(rs.getInt(1));
                image.setSource(rs.getString(2));
                image.setName(rs.getString(3));
            }
            
            return image;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public Image findImageBySource(String source) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select image.* from image where source = ? ";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, source);
            rs = ps.executeQuery();
            
            Image image = new Image();
            
            while(rs.next()) {
                
                image.setId(rs.getInt(1));
                image.setSource(rs.getString(2));
                image.setName(rs.getString(3));
            }
            
            return image;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public ArrayList<Image> getAll() throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from image");
            rs = ps.executeQuery();

            ArrayList<Image> list = new ArrayList<Image>();
            
            while (rs.next()) {
                Image image = new Image();
                image.setId(rs.getInt(1));
                image.setSource(rs.getString(2));
                image.setName(rs.getString(3));

                list.add(image);
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps, rs);
        }
    }
    
    public void save(Image image) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "INSERT INTO image (source, name)"
                    + " values (?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, image.getSource());
            ps.setString(2, image.getName());
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void delete(int id) throws Exception {
        PreparedStatement ps = null;
        
        PermissionDAO permissionDAO = new PermissionDAO();
        permissionDAO.delete(id);
        
        try {            
            ps = conn.prepareStatement("delete from image where idImage = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro:" + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
}
