package com.piano.orm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class UserDao extends HibernateDaoSupport{

	public static final String UNAME = "userName";
	private static final Logger log = LoggerFactory
			.getLogger(UserDao.class);
	
	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public void update(String userName, Integer isVerified){
		User user = findByUname(userName);
		System.out.println("user:"+user.getUserName() + " " + user.getIsVerified() + "isVerified:"+isVerified);
		user.setIsVerified(isVerified);
		try {
			getHibernateTemplate().saveOrUpdate(user);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	
	public void update(String userName, String lastLoginTime){
		User user = findByUname(userName);
		System.out.println("user:"+user.getUserName() + " " + user.getLastLoginTime());
		user.setLastLoginTime(lastLoginTime);
		try {
			getHibernateTemplate().saveOrUpdate(user);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	}
	
	public User findByUname(Object uname) {
		List<User> list = findByProperty(UNAME, uname);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public User findById(Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.piano.orm.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static UserDao getFromApplicationContext(ApplicationContext ctx){
		return (UserDao) ctx.getBean("UserDao");
	}
}
