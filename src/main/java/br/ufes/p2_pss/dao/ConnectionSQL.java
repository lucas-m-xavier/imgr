/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.p2_pss.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Lucas
 */
public class ConnectionSQL {
    
    public static Connection getConnection() {

        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useTimezone=true&serverTimezone=UTC", "root","1234");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return c;
    }

    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {}
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {}
    }

    public static void closeConnection(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {}
    }
    
}
