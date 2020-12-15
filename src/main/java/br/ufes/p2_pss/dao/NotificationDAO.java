/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.dao;

import br.ufes.p2_pss.model.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class NotificationDAO {
    private Connection conn;

    public NotificationDAO() throws Exception {
        try {
            this.conn = ConnectionSQL.getConnection();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public NotificationDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Notification> getAllByUserId(int id) throws Exception {
        PreparedStatement ps = null;

        ResultSet rs = null;
        try {

            ps = conn.prepareStatement("select * from notification where idUser = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            ArrayList<Notification> list = new ArrayList<Notification>();
            
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(rs.getInt(1));
                notification.setDescription(rs.getString(2));

                list.add(notification);
            }
            return list;
        } catch (SQLException sqle) {
            throw new Exception(sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps, rs);
        }
    }
    
    public void save(Notification notification) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "INSERT INTO notification (description, idUser)"
                    + " values (?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setString(1, notification.getDescription());
            ps.setInt(2, notification.getUser().getId());
            ps.executeUpdate();
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void deleteAllByUserId(int id) throws Exception {
        PreparedStatement ps = null;
        try {            
            ps = conn.prepareStatement("delete from notification where idUser = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
}
