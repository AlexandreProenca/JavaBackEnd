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
import java.sql.Statement;
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
           String sql = "INSERT INTO public.user (name,email,email2,role,password,created, lastlogin, status) values (?,?,?,?,?,?,?,?)";
           ResultSet generatedKeys;
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getEmail2());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getPassword());
            ps.setDate(6, data);
            ps.setDate(7, data);
            ps.setString(8, "ACTIVE");

            //executa
            ps.execute();
            
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no generated key obtained.");
            }

            //fecha
            ps.close();
            generatedKeys.close();

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
    public void remove(Long id) {
         String sql = "DELETE FROM public.user WHERE id=?";
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

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
    public User get(Long id) {
        //String sql = "SELECT * FROM user WHERE id=?";
        String sql = "SELECT * FROM public.user WHERE id=?";
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            //tras p primeiro resultado
            rs.next();
            User user = new User();

            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setEmail2(rs.getString("email2"));
            user.setRole(rs.getString("role"));
            user.setPassword(rs.getString("password"));
            user.setCreated(rs.getDate("created"));
            user.setLastlogin(rs.getDate("lastlogin"));
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
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setEmail2(rs.getString("email2"));
                user.setRole(rs.getString("role"));
                user.setPassword(rs.getString("password"));
                user.setCreated(rs.getDate("created"));
                user.setLastlogin(rs.getDate("lastlogin"));
                user.setStatus(rs.getString("status"));
                users.add(user);
            }
            pstmt.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro ao listar todos os clientes: " + e);
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
          
           String sql = "UPDATE public.user SET name=?, email=?, email2=?, role=?, password=?, lastlogin=?, status=? WHERE id=?";
           
        try {
            //prepara
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getEmail2());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getPassword());
            ps.setDate(6, data);
            ps.setString(7, "ACTIVE");
            
            ps.setLong(8, user.getId());

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
    public User checkLogin(User user) {
        String sql = "SELECT * FROM public.user WHERE email=? AND password=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();

            User userNew = null;
            boolean hasMore = rs.next();
            if(hasMore){
                userNew = new User();
                userNew.setId(rs.getLong("id"));
                userNew.setName(rs.getString("name"));
                userNew.setEmail(rs.getString("email"));
                userNew.setEmail2(rs.getString("email2"));
                userNew.setRole(rs.getString("role"));
                userNew.setPassword(rs.getString("password"));
                userNew.setCreated(rs.getDate("created"));
                userNew.setLastlogin(rs.getDate("lastlogin"));
                userNew.setStatus(rs.getString("status"));
            }
            
            ps.close();
            rs.close();

            return userNew;

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
    
}
