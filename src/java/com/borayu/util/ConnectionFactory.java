/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.borayu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexandre Proença
 */
public class ConnectionFactory {

    /**
     *
     */

    private static final String DRIVER_POSTGRES = "org.postgresql.Driver";
    private static final String URL_POSTGRES = "jdbc:postgresql://www.aws-orion.com.br/Borayu";
    private static final String USER_POSTGRES = "postgres";
    private static final String PASSWORD_POSTGRES = "gmmaster765";

    private static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
    private static final String URL_MYSQL = "jdbc:mysql://www.aws-orion.com.br/Borayu";
    private static final String USER_MYSQL = "root";
    private static final String PASSWORD_MYSQL = "alaj23";

    public static Connection getConnection(String driver) {

        if (driver.equalsIgnoreCase("postgres")) {

            try {
                Class.forName(DRIVER_POSTGRES);
                return DriverManager.getConnection(URL_POSTGRES, USER_POSTGRES, PASSWORD_POSTGRES);

            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("Error to open postgres dabase", ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("***** Error ClassNotFoundException!", ex);

            }
        } else if (driver.equalsIgnoreCase("mysql")) {

            try {
                Class.forName(DRIVER_MYSQL);
                return DriverManager.getConnection(URL_MYSQL, USER_MYSQL, PASSWORD_MYSQL);

            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("Error to open Mysql dabase", ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("***** Error ClassNotFoundException!", ex);

            }
        } else {

            return null;
        }

    }
}
