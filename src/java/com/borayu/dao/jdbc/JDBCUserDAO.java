/*
 * Projeto Borayu
 * RestFull Server  * 
 */

package com.borayu.dao.jdbc;

import com.borayu.dao.interfaces.IUser;
import com.borayu.model.User;
import com.borayu.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author borayu02
 */
public class JDBCUserDAO implements IUser{
    
    Date data = new Date(System.currentTimeMillis());
    Connection conn;
    

    public JDBCUserDAO() {
        
        conn = ConnectionFactory.getConnection("postgres");
        
    }
    
    
    
    @Override
    public void add(User user) {
           String sql = "INSERT INTO public.user (login,passwd,email2,lastlogin,created,state) values (?,?,?,?,?)";
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasswd());
            ps.setString(3, user.getEmail2());
            ps.setDate(4, data);
            ps.setDate(5, data);
            ps.setString(6, "ACTIVE");

            //executa
            ps.execute();

            //fecha
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remove(int id) {
         String sql = "DELETE FROM public.user WHERE id=?";
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            //executa
            ps.executeUpdate();

            //fecha
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public User get(int id) {
        //String sql = "SELECT * FROM user WHERE id=?";
        String sql = "SELECT * FROM public.user WHERE id=?";
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            //tras p primeiro resultado
            rs.next();
            User user = new User();

            user.setId(rs.getLong("id"));
            user.setLogin(rs.getString("login"));
            user.setPasswd(rs.getString("passwd"));
            user.setEmail2(rs.getString("email2"));
            user.setLastlogin(rs.getDate("lastlogin"));
            user.setCreated(rs.getDate("created"));
            user.setStatus(rs.getString("status"));
            ps.close();
            rs.close();

            return user;

        } catch (SQLException ex) {
            Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public List<User> getList() {
         List<User> users = new ArrayList<User>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM public.user");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setPasswd(rs.getString("passwd"));
                user.setEmail2(rs.getString("email2"));
                user.setLastlogin(rs.getDate("lastlogin"));
                user.setCreated(rs.getDate("created"));
                user.setStatus(rs.getString("status"));
                users.add(user);
            }
            pstmt.close();
            rs.close();

        } catch (Exception e) {
            System.out.println("Erro ao listar todos os clientes: " + e);
            e.printStackTrace();
            return null;
        } finally {

            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return users;
    }

    @Override
    public void update(User user) {
        
    }
    
}
