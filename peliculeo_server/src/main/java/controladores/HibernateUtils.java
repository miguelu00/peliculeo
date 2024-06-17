package controladores;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
	private static SessionFactory sessionFactory;
	private static Session session = null;
	
	public static void buildSessionFactory()
	{
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		
		sessionFactory = new MetadataSources(sr).buildMetadata().buildSessionFactory();
	}
	
	public static void openSession()
	{
		try {
			session = sessionFactory.openSession();
		} catch(HibernateException e)
		{
			System.out.println("No se pudo conectar a la Base de datos: " + e.getMessage());
		}
		
	}
	
	public static Session getCurrentSession()
	{
		if (!session.isOpen())
		{
			openSession();
		}
		return session;
	}
	
	public static void closeSessionFactory()
	{
		if (session != null)
		{
			try {
				session.close();
			} catch(HibernateException e)
			{
				System.out.println("Error al cerrar la Sesión: " + e.getMessage());
				
			}
			
		}
		if (sessionFactory != null)
		{
			try {
				sessionFactory.close();
			} catch(HibernateException e)
			{
				System.out.println("Error al cerrar el SessionFactory de Hibernate: " + e.getMessage());
				
			}
			
		}
	}

}


