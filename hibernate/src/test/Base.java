package test;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import utils.HibernateUtils;
import domain.User;

public class Base {
	public static void main(String[] args) {
		User user = new User();
		user.setBirthday("1994-06-06");
		user.setName("语文");
		addUser(user);
		/*user = getUser(1);
		System.out.println(user.getBirthday());*/

	}	

	static void  addUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		} catch (HibernateException e) {
			/**
			 * 只是回滚是不行的，一定要把异常报告出去
			 * */
			if (tx != null)
				tx.rollback();
			throw e;
		}finally{
			if(s!=null)
				s.close();
		}

	}
//更新操作
	static void  UpdateUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.update(user);
			tx.commit();
		} catch (HibernateException e) {
			/**
			 * 只是回滚是不行的，一定要把异常报告出去
			 * */
			if (tx != null)
				tx.rollback();
			throw e;
		}finally{
			if(s!=null)
				s.close();
		}

	}
	
	//删除
	static void  DeleteUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			/**
			 * 只是回滚是不行的，一定要把异常报告出去
			 * */
			if (tx != null)
				tx.rollback();
			throw e;
		}finally{
			if(s!=null)
				s.close();
		}

	}
	//根据id查询
	static Object getById(Class user,Serializable id) {
		Session s = null;
		Object obj = null;
		try {
			s = HibernateUtils.getSession();
		    obj = s.get(User.class, id);		
		} catch (HibernateException e) {
			/**
			 * 只是回滚是不行的，一定要把异常报告出去
			 * */
		}finally{
			if(s!=null)
				s.close();
		} 
		return obj;

	}
	//以后简写成这样也是可以的；
	/*static void  addUser(User user) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.save(user);
			tx.commit();
		} finally{
			if(s!=null)
				s.close();
		}

	}*/
	
	
	static User getUser(int id){
		Session s = null;
		User user = null;
		try {
			s = HibernateUtils.getSession();
			Class userclass = User.class;
			user = (User)s.get(userclass,id);
			
		} catch (HibernateException e) {
			
		}finally{
			if(s!=null)
				s.close();
		}
		return user;

		
	}
}
