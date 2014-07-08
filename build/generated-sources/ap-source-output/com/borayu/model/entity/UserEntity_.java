package com.borayu.model.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-07-08T14:04:50")
@StaticMetamodel(UserEntity.class)
public class UserEntity_ { 

    public static volatile SingularAttribute<UserEntity, Long> id;
    public static volatile SingularAttribute<UserEntity, String> passwd;
    public static volatile SingularAttribute<UserEntity, Date> created;
    public static volatile SingularAttribute<UserEntity, String> status;
    public static volatile SingularAttribute<UserEntity, Date> lastlogin;
    public static volatile SingularAttribute<UserEntity, String> login;
    public static volatile SingularAttribute<UserEntity, String> email2;

}