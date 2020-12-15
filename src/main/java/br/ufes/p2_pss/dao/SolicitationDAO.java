/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.dao;

import br.ufes.p2_pss.model.Solicitation;
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
public class SolicitationDAO {
    private Connection conn;

    public SolicitationDAO() throws Exception {
        try {
            this.conn = ConnectionSQL.getConnection();
        } catch (Exception e) {
            throw new Exception("Erro: \n" + e.getMessage());
        }
    }

    public SolicitationDAO(Connection conn) {
        this.conn = conn;
    }
    
    public ArrayList<Solicitation> getAllByAdmin(User admin) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "select * from permission where idAdmin = ?;";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, admin.getId());
            rs = ps.executeQuery();
            
            ArrayList<Solicitation> solicitations = new ArrayList<>();
            
            while(rs.next()) {
                Solicitation solicitation = new Solicitation();
                solicitation.setId(rs.getInt(1));
                solicitation.setIdImage(rs.getInt(2));
                solicitation.setIdAdmin(rs.getInt(3));
                solicitation.setIdUser(rs.getInt(4));
                solicitation.setView(rs.getBoolean(5));
                solicitation.setDelete(rs.getBoolean(6));
                solicitation.setShare(rs.getBoolean(7));
                
                solicitations.add(solicitation);
            }
            
            return solicitations;
                                  
        } catch (SQLException sqle) {
            throw new Exception("Erro: " + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
    
    public void save(Solicitation solicitation) throws Exception {
        PreparedStatement ps = null;
        
        try {
            String SQL = "INSERT INTO solicitation (idImage, idAdmin, idUser, viewS, deleteS, shareS)"
                    + " values (?, ?, ?, ?, ?, ?);";
                               
            ps = conn.prepareStatement(SQL);
            
            ps.setInt(1, solicitation.getIdImage());
            ps.setInt(2, solicitation.getIdAdmin());
            ps.setInt(3, solicitation.getIdUser());
            ps.setBoolean(4, solicitation.isView());
            ps.setBoolean(5, solicitation.isDelete());
            ps.setBoolean(6, solicitation.isShare());
            
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
            ps = conn.prepareStatement("delete from solicitation where idSolicitation = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Erro:" + sqle);
        } finally {
            ConnectionSQL.closeConnection(conn, ps);
        }
    }
}
