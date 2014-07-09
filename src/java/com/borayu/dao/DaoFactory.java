package com.borayu.dao;

import com.borayu.dao.interfaces.IUserEntity;
import com.borayu.dao.jpa.JpaUserDao;
import com.borayu.search.CompassManager;
import javax.persistence.EntityManagerFactory;
import org.compass.core.Compass;

public final class DaoFactory {
	
	private static final DaoFactory INSTANCE = new DaoFactory();
	
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
	private final EntityManagerFactory emf;
	private final Compass compass;
	
	private DaoFactory() {
		emf = PersistenceManager.getInstance().getEntityManagerFactory();
		compass = CompassManager.getInstance().getCompass();
	}
	
	public IUserEntity getUserJpa() {
		return new JpaUserDao(emf, compass);
	}

//	public MarkDao getMarkDao() {
//		return new JpaMarkDao(emf);
//	}

}
