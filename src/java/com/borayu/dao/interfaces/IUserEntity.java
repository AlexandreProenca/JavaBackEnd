/*
 * Projeto Borayu
 * RestFull Server  * 
 */

package com.borayu.dao.interfaces;

import com.borayu.model.entity.UserEntity;
import java.util.List;

/**
 *
 * @author borayu02
 */
public interface IUserEntity {
    
    
    public void add(UserEntity user);
    public void remove(int id);
    public UserEntity get(int id);
    public List<UserEntity> getList();
    public void update(UserEntity user);
}
