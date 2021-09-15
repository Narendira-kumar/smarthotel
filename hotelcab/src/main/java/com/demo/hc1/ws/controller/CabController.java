package com.demo.hc1.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.hc1.ws.dto.CabBookDto;
import com.demo.hc1.ws.entity.CabInfo;
import com.demo.hc1.ws.model.response.CabResponseModel;
import com.demo.hc1.ws.service.CabService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Cab Details", description = "REST API for Cab ", tags = "Cab")
public class CabController {
	
	@Autowired 
	CabService cabservice;
	
	@ApiOperation(value = "${cabController.createCabInfo.ApiOperation}", notes = "${cabController.createcabinfo.ApiOperation.Notes}", tags = "Cab")
	@PostMapping(path = "/addCab", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public CabResponseModel createCabInfo(@RequestBody CabInfo cabInfo) {
		CabInfo trans1= cabservice.createCabDetails(cabInfo);
		CabResponseModel response = new CabResponseModel();
		response.setCabId(trans1.getCabId());
		return response;
	}

	@ApiOperation(value = "${cabController.bookCab.ApiOperation}", notes = "${cabController.bookcab.ApiOperation.Notes}", tags = "Cab")
	@PostMapping(path = "/bookCab", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public CabResponseModel bookCab(@RequestBody CabBookDto cabBookDto) {
		
		CabInfo trans1= cabservice.bookCab(cabBookDto);
		CabResponseModel response = new CabResponseModel();
		response.setCabId(trans1.getCabId());
		return response;
	}

}
