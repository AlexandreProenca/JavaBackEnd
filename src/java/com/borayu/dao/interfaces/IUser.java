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
    public void remove(int id);
    public User get(int id);
    public List<User> getList();
    public void update(User user);
}
