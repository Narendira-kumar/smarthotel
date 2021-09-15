package com.demo.hc1.ws.service;

import com.demo.hc1.ws.dto.CabBookDto;
import com.demo.hc1.ws.entity.CabInfo;

public interface CabService {

	public CabInfo createCabDetails(CabInfo cabInfo);
	
	public CabInfo bookCab(CabBookDto cabBookDto);
	
	 
}
