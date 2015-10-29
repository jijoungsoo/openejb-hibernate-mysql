package kr.co.nknk_businessLogic.jpa.test;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.co.nknk_businessLogic.jpa.Member;
import kr.co.nknk_businessLogic.jpa.SimpleService;

public class SimpleServiceIntegrationTest {

	@EJB
	private SimpleService simpleService;

	private Context context;

	private EJBContainer ejbContainer;
	protected static EntityManagerFactory emf;



	@Before
	public void init() throws NamingException {
		System.out.println("1111");
		final Properties p = new Properties();
		p.put("movieDatabase", "new://Resource?type=DataSource");
		p.put("movieDatabase.JdbcDriver", "com.mysql.jdbc.Driver");
		p.put("movieDatabase.JdbcUrl", "jdbc:mysql://localhost:3306/nknk");
		p.put("movieDatabase.username", "root");
		p.put("movieDatabase.password", "xxxxx");
		ejbContainer = EJBContainer.createEJBContainer(p);
		context = ejbContainer.getContext();
		System.out.println("22222");
		context.bind("inject", this);
		System.out.println("333");

	}

	@Test
	public void testSaveProcess() throws Exception {
		System.out.println("444");
		Member member = new Member("jijsx@hotmail.com", "지정", "1234");
		System.out.println("555");
//		 SimpleService simpleService1 = (SimpleService)		context.lookup("java:global/kr/co/nknk_businessLogic/jpa/SimpleService");
		simpleService.saveProcess(member);
		System.out.println("666");

	}

	@After
	public void destroy() {
		// ejbContainer.close();
	}

}
