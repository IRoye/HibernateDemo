package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * 包装hibernate 的初始化过程
 * 
 * 
 * */
@SuppressWarnings("deprecation")
public  final class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	/**
	 * 成为单例模式来用;
	 */
	private HibernateUtils(){
		
		
	}
	/**只会在虚拟机加载这个类的时候执行这段代码，可以保证只执行一次*/
	static{
		Configuration cfg = new Configuration();
		/*这一步，是读取配置文件，hibernate.cfg.xml的配置文件，如果不是这个名字
		 * 那么就要告诉configure(),指定相应的参数**/
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
		
	}
	/*增加一个get方法，返回sessionFactory**/
   public static SessionFactory getSessionFactory(){
	   return sessionFactory;
   }
	public static Session getSession(){
		
	return sessionFactory.openSession();
	}

   
   
}
