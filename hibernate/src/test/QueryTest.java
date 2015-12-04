package test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.User;
import utils.HibernateUtils;

/****
 * Query接口的使用
 * 
 * @author Administrator
 * 如果点击下一页，就把
 */
public class QueryTest {
	public static void main(String[] args) {

		/*List<User> list = query("张三");
		for (User user : list) {
			System.out.println("用户名" + user.getName());
		}
*/
		
		List<User> list = query();
		for(User user:list){
			System.out.println("用户名" + user.getName()+user.getId());
		}
		
	}

	/**
	 * get()方法只能根据id来查询,使用Query接口来查询
	 * 
	 */
	static List<User> query() {
		Session s = null;
		List<User> userlist = null;
		try {
			s = HibernateUtils.getSession();
			String hql = "from User";
			Query query = s.createQuery(hql);
			/***
			 * 和jdbc不同的是，参数是从0开始的；
			 */
			//query.setString(0, name);
			query.setFirstResult(0);
			query.setMaxResults(2);
			userlist = query.list();

		} finally {
			if (s != null)
				s.close();
		}
		return userlist;

	}

	public static void delete(Object entity) {

		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtils.getSession();
			tx = s.beginTransaction();
			s.delete(entity);
			tx.commit();
		} finally {

			if (s != null)
				s.close();
		}

	}
	
	

}
