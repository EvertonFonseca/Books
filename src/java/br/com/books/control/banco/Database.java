/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.books.control.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Windows
 */
public class Database {
    
    private String user  = "EVERTON";
    private String senha = "lider";
    private String url   = "jdbc:oracle:thin:@localhost:1521:xe";
    private Connection conn;

    static {
        try {
           
            Class.forName("oracle.jdbc.OracleDriver");
       
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public Database(){}
    public Database(String user,String senha){
        
        this.user = user;
        this.senha = senha;
    }
    
    public void connect(){
     
        try {
            this.conn = DriverManager.getConnection(url, user, senha);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void close(){
        
        try {
            this.conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ResultSet sql(String query){
         
        ResultSet result = null;

        try {
            PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            result  = st.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public PreparedStatement getStatement(String sql){
        
        try {
            return this.conn.prepareStatement(sql);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
