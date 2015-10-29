package kr.co.nknk_business_logic.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import kr.co.nknk_businessLogic.jpa.Member;
import kr.co.nknk_businessLogic.jpa.SimpleService;

@Stateless
public class HelloBean implements Hello {

	@EJB
	private SimpleService simpleService;

	public String sayHello() throws Exception {
		
    	Member member = new Member("jijsx@hotmail.com", "지정", "1234");
		simpleService.saveProcess(member);

		
		return "Hello World!!!!";
	}
}