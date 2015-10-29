package kr.co.nknk_business_logic.ejb;

import javax.ejb.Remote;
@Remote
public interface Hello{
    public String sayHello() throws Exception;
}

