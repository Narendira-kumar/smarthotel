package com.demo.hc1.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hc1.ws.entity.CabInfo;

@Repository
public interface CabInfoRepository extends JpaRepository<CabInfo , Long>{
	
	CabInfo findByCabId(Long cabId);

}
