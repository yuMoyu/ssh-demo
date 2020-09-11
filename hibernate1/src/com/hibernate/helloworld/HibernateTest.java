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
		//1 创建一个SessionFactory对象
		SessionFactory sessionFactory = null;
		//1)创建一个Configuration对象 对应hibernate的基本配置信息喝对象关系映射信息 .cfg.xml
		Configuration configuration = new Configuration().configure();
		//File file = new File(*.cfg.xml); 
		//Configuration configuration = new Configuration().configure(file);
		//2)创建ServiceRegistry 对象
		//hibernate的任何配置和服务都需要在该对象注册后才有效
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				                            .buildServiceRegistry();
		//3)生成session的工厂
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		//2 创建一个session对象
		//session：持久化对象在其管理下进行持久化操作，flush前持久层操作数据存在session对象处（像硬盘和cpu之间的缓存cache）
		Session session = sessionFactory.openSession();
		//3 开启事务
		Transaction transaction = session.beginTransaction();
		//4执行保存操作
		//News news = new News("JAVA","ATGUIGU",new Date(new java.util.Date().getTime()));
		//session.save(news);
		News news2 = (News)session.get(News.class, 1);
		System.out.println(news2);
		//5 提交事务
		transaction.commit();
		//6 关闭Session
		session.close();
		//7 关闭SessionFactory对象
		sessionFactory.close();
	}

}
