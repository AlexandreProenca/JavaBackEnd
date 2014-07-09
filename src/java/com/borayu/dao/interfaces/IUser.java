/*
 * Projeto Borayu
 * RestFull Server  * 
 */

package com.borayu.dao.interfaces;

import com.borayu.model.User;
import java.util.List;

/**
 *
 * @author borayu02
 */
public interface IUser {
    public void add(User user);
    public void remove(Long id);
    public User get(Long id);
    public List<User> getList();
    public void update(User user);
    public User checkLogin(User user);
}
