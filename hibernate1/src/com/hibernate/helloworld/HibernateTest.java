package com.hibernate.helloworld;

import static org.junit.Assert.*;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		//1 ����һ��SessionFactory����
		SessionFactory sessionFactory = null;
		//1)����һ��Configuration���� ��Ӧhibernate�Ļ���������Ϣ�ȶ����ϵӳ����Ϣ .cfg.xml
		Configuration configuration = new Configuration().configure();
		//File file = new File(*.cfg.xml); 
		//Configuration configuration = new Configuration().configure(file);
		//2)����ServiceRegistry ����
		//hibernate���κ����úͷ�����Ҫ�ڸö���ע������Ч
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				                            .buildServiceRegistry();
		//3)����session�Ĺ���
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//2 ����һ��session����
		//session���־û�������������½��г־û�������flushǰ�־ò�������ݴ���session���󴦣���Ӳ�̺�cpu֮��Ļ���cache��
		Session session = sessionFactory.openSession();
		//3 ��������
		Transaction transaction = session.beginTransaction();
		//4ִ�б������
		//News news = new News("JAVA","ATGUIGU",new Date(new java.util.Date().getTime()));
		//session.save(news);
		News news2 = (News)session.get(News.class, 1);
		System.out.println(news2);
		//5 �ύ����
		transaction.commit();
		//6 �ر�Session
		session.close();
		//7 �ر�SessionFactory����
		sessionFactory.close();
	}

}
