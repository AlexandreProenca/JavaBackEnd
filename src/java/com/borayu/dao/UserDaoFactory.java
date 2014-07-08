/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.borayu.dao;
import com.borayu.dao.interfaces.IUser;
import com.borayu.dao.jdbc.JDBCUserDAO;
import com.borayu.dao.jpa.JpaUserDao;
import com.borayu.model.User;

/**
 *
 * @author borayu02
 */
public class UserDaoFactory {
    
    public static IUser createUserDao(String implement){
        
        if (implement.equalsIgnoreCase("jdbc")){
         return new JDBCUserDAO();
        
        }else{
         return null;
        }
    }
}
      
