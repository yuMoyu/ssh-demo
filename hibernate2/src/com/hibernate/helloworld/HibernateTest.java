package com.hibernate.helloworld;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

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
	
	/**
	 * ʹ��increment��������
	 */
	@Test
	public void testIdGenerator() {
		News news = new News("AA","aa",new Date());
		session.save(news);
		//Thread.sleep(5000);
 	}
	
	/**
	 * ��̬���£�sql��ֻ������Ϊnull�ֶ�
	 */
	@Test
	public void testDynamicUpdate(){
		News news = (News) session.get(News.class, 1);
		news.setAuthor("AA");
		
	}
	
	@Test
	public void testDoWork(){
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				System.out.println(connection); 
				
				//���ô洢����. 
			}
		});
	}
	
	/**
	 * evict: �� session �����а�ָ���ĳ־û������Ƴ�
	 */
	@Test
	public void testEvict(){
		News news1 = (News) session.get(News.class, 1);
		News news2 = (News) session.get(News.class, 2);
		
		news1.setTitle("AA");
		news2.setTitle("BB");
		
		session.evict(news1); 
	}
	
	/**
	 * delete: ִ��ɾ������. ֻҪ OID �����ݱ���һ����¼��Ӧ, �ͻ�׼��ִ�� delete ����
	 * �� OID �����ݱ���û�ж�Ӧ�ļ�¼, ���׳��쳣
	 * 
	 * ����ͨ������ hibernate �����ļ� hibernate.use_identifier_rollback Ϊ true,
	 * ʹɾ�������, ���� OID ��Ϊ  null
	 */
	@Test
	public void testDelete(){
//		News news = new News();
//		news.setId(11);
		
		News news = (News) session.get(News.class, 163840);
		session.delete(news); 
		
		System.out.println(news);
	}
	
	/**
	 * ע��:
	 * 1. �� OID ��Ϊ null, �����ݱ��л�û�к����Ӧ�ļ�¼. ���׳�һ���쳣. 
	 * 2. �˽�: OID ֵ���� id �� unsaved-value ����ֵ�Ķ���, Ҳ����Ϊ��һ���������
	 */
	@Test
	public void testSaveOrUpdate(){
		News news = new News("FFF", "fff", new Date());
		news.setId(11);
		
		session.saveOrUpdate(news); 
	}
	
	/**
	 * update:
	 * 1. ������һ���־û�����, ����Ҫ��ʾ�ĵ��� update ����. ��Ϊ�ڵ��� Transaction
	 * �� commit() ����ʱ, ����ִ�� session �� flush ����.
	 * 2. ����һ���������, ��Ҫ��ʽ�ĵ��� session �� update ����. ���԰�һ���������
	 * ��Ϊ�־û�����
	 * 
	 * ��Ҫע���:
	 * 1. ����Ҫ���µ������������ݱ�ļ�¼�Ƿ�һ��, ���ᷢ�� UPDATE ���. 
	 *    ������� updat ��������äĿ�ĳ��� update ����� ? �� .hbm.xml �ļ��� class �ڵ�����
	 *    select-before-update=true (Ĭ��Ϊ false). ��ͨ������Ҫ���ø�����. 
	 * 
	 * 2. �����ݱ���û�ж�Ӧ�ļ�¼, ���������� update ����, ���׳��쳣
	 * 
	 * 3. �� update() ��������һ���������ʱ, 
	 * ����� Session �Ļ������Ѿ�������ͬ OID �ĳ־û�����, ���׳��쳣. ��Ϊ�� Session ������
	 * ���������� OID ��ͬ�Ķ���!
	 *    
	 */
	@Test
	public void testUpdate(){
		News news = (News) session.get(News.class, 1);
		
		transaction.commit();
		session.close();
		
//		news.setId(100);

		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
//		news.setAuthor("SUN"); 
		
		News news2 = (News) session.get(News.class, 1);
		session.update(news);
	}
	
	/**
	 * get VS load:
	 * 
	 * 1. ִ�� get ����: ���������ض���. 
	 *    ִ�� load ����, �������øö���, �򲻻�����ִ�в�ѯ����, ������һ���������
	 *    
	 *    get �� ��������, load ���ӳټ���. 
	 * 
	 * 2. load �������ܻ��׳� LazyInitializationException �쳣: ����Ҫ��ʼ��
	 * �������֮ǰ�Ѿ��ر��� Session
	 * 
	 * 3. �����ݱ���û�ж�Ӧ�ļ�¼, Session Ҳû�б��ر�.  
	 *    get ���� null
	 *    load ����ʹ�øö�����κ�����, û����; ����Ҫ��ʼ����, �׳��쳣.  
	 */
	@Test
	public void testLoad(){
		
		News news = (News) session.load(News.class, 10);
		System.out.println(news.getClass().getName()); 
		
//		session.close();
//		System.out.println(news); 
	}
	
	@Test
	public void testGet(){
		News news = (News) session.get(News.class, 1);
//		session.close();
		System.out.println(news); 
	}
	
	/**
	 * persist(): Ҳ��ִ�� INSERT ����
	 * 
	 * �� save() ������ : 
	 * �ڵ��� persist ����֮ǰ, �������Ѿ��� id ��, �򲻻�ִ�� INSERT, ���׳��쳣
	 */
	@Test
	public void testPersist(){
		News news = new News();
		news.setTitle("EE");
		news.setAuthor("ee");
		news.setData(new Date());
		news.setId(200); 
		
		session.persist(news); 
	}
	
	/**
	 * 1. save() ����
	 * 1). ʹһ����ʱ�����Ϊ�־û�����
	 * 2). Ϊ������� ID.
	 * 3). �� flush ����ʱ�ᷢ��һ�� INSERT ���.
	 * 4). �� save ����֮ǰ�� id ����Ч��
	 * 5). �־û������ ID �ǲ��ܱ��޸ĵ�!
	 */
	@Test
	public void testSave(){
		News news = new News();
		news.setTitle("CC");
		news.setAuthor("cc");
		news.setData(new Date());
		//news.setId(100); 
		
		System.out.println(news);
		
		session.save(news);

		System.out.println(news);
//		news.setId(101); 
	}
	
	/**
	 * clear(): ������
	 */
	@Test
	public void testClear(){
		News news1 = (News) session.get(News.class, 1);
		
		session.clear();
		
		News news2 = (News) session.get(News.class, 1);
	}
	
	/**
	 * refresh(): ��ǿ�Ʒ��� SELECT ���, ��ʹ Session �����ж����״̬�����ݱ��ж�Ӧ�ļ�¼����һ��!
	 */
	@Test
	public void testRefresh(){
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		
		session.refresh(news); 
		System.out.println(news); 
	}
	
	/**
	 * flush: ʹ���ݱ��еļ�¼�� Session �����еĶ����״̬����һ��. Ϊ�˱���һ��, ����ܻᷢ�Ͷ�Ӧ�� SQL ���.
	 * 1. �� Transaction �� commit() ������: �ȵ��� session �� flush ����, ���ύ����
	 * 2. flush() ��������ܻᷢ�� SQL ���, �������ύ����. 
	 * 3. ע��: ��δ�ύ�������ʽ�ĵ��� session.flush() ����֮ǰ, Ҳ�п��ܻ���� flush() ����.
	 * 1). ִ�� HQL �� QBC ��ѯ, ���Ƚ��� flush() ����, �Եõ����ݱ�����µļ�¼
	 * 2). ����¼�� ID ���ɵײ����ݿ�ʹ�������ķ�ʽ���ɵ�, ���ڵ��� save() ����ʱ, �ͻ��������� INSERT ���. 
	 * ��Ϊ save ������, ���뱣֤����� ID �Ǵ��ڵ�!
	 */
	@Test
	public void testSessionFlush2(){
		News news = new News("Java", "SUN", new Date());
		session.save(news);
	}
	
	@Test
	public void testSessionFlush(){
		News news = (News) session.get(News.class, 1);
		news.setAuthor("Oracle");
		
//		session.flush();
//		System.out.println("flush");
		
		News news2 = (News) session.createCriteria(News.class).uniqueResult();
		System.out.println(news2);
	}
	
	@Test
	public void testSessionCache(){
		
		News news = (News) session.get(News.class, 1);
		System.out.println(news); 
		
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);
	}

}
