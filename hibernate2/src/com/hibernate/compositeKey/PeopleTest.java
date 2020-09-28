package com.hibernate.compositeKey;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PeopleTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				                            .buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testUpdate(){
		People people = new People(new PeoplePK("ÀèÃ÷","ÄÐ"),11);
		session.save(people); 
	}
	@Test
	public void test() {
		People people = new People(new PeoplePK("ÀèÃ÷","ÄÐ"),11);
		System.out.println(people.getPeopleKey().getType());
		People people1 = new People(null,11);
		System.out.println(people1.getPeopleKey().getName()==null);
		
	}

}
