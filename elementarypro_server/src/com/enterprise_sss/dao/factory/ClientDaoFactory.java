package com.enterprise_sss.dao.factory;

import com.enterprise_sss.dao.impl.ClientDao;
import com.enterprise_sss.dao.inter.ClientInter;
import com.enterprise_sss.vo.ClientVO;

public class ClientDaoFactory {
	
	public static ClientInter getClientDao(ClientVO cvo){
		return new ClientDao(cvo);
	}
	
	public static ClientInter getClientDao(){
		return new ClientDao();
	}
	
}
