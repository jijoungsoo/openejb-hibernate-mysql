package kr.co.nknk_businessLogic.jpa;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class SimpleService {

	@PersistenceContext(unitName = "nknkmysql")
	private EntityManager entityManager;

	public void saveProcess(Member member) throws Exception {
	

		if (entityManager == null) {
			System.out.println("아웃 nul이라");
		} else {
			entityManager.persist(member);
		}
	}

}