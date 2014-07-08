/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.borayu.controller;

import com.borayu.dao.UserDaoFactory;
import com.borayu.dao.interfaces.IUser;
import com.borayu.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexandre Proença
 */
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     */
    
    java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
       
       List<User> users = new ArrayList<User>(); 
       IUser jpa = UserDaoFactory.createUserDao("jpa");
       User user = new User();
               
       user.setLogin(request.getParameter("login"));
       user.setPasswd(request.getParameter("passwd"));
       user.setEmail2(request.getParameter("email2"));
       user.setLastlogin(data);
       user.setCreated(data);
       user.setStatus(request.getParameter("status"));
              
       jpa.add(user);
        
       users = jpa.getList();
       
       RequestDispatcher rd = request.getRequestDispatcher("usuario.jsp?usuarios="+users);
       rd.forward(request, response);
       
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
