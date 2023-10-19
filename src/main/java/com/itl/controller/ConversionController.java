package com.itl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itl.entities.ConversionTable;
import com.itl.entities.Resource_urls;
import com.itl.service.ConverterService;
import com.itl.service.ResourceUrlService;
import com.itl.web.dto.DataConversionVO;
import com.itl.web.dto.RequestVO;

@RestController
@RequestMapping(value = "/convesionApi")
public class ConversionController {

	@Autowired
	ResourceUrlService resourceUrlService;

	@Autowired
	ConverterService converterService;

	@PostMapping(value = "/saveUrlData", produces = "application/json")
	public String saveUrlData(@RequestBody RequestVO requestVO) {

		Resource_urls data = resourceUrlService.getbyApiId(requestVO.getUrlId());
		if (data != null) {
			return "record exists already";
		} else {
			data = new Resource_urls();
			data.setApiId(requestVO.getUrlId());
			data.setApiName(requestVO.getUrlName());
			data.setServiceUrl(requestVO.getServiceUrl());
			Resource_urls entity = resourceUrlService.saveOrUpdate(data);
			if (entity != null) {
				return "record saved succussfully";
			} else {
				return "fail to save record";
			}
		}
	}

	@PostMapping(value = "/saveData", produces = "application/json")
	public String SaveRequestUrl(@RequestBody DataConversionVO dataConversionVO) {

		ConversionTable data = converterService.getbyIncomingColumn(dataConversionVO.getIncomingColumnName());
		if (data != null) {
			return "record exists already";
		} else {
			data = new ConversionTable();
			data.setApiName(dataConversionVO.getApiName());
			data.setApiNumber(dataConversionVO.getApiNumber());
			data.setIncomingColumnName(dataConversionVO.getIncomingColumnName());
			data.setOutgoingColumnName(dataConversionVO.getOutgoingColumnName());
			data.setTenantId(dataConversionVO.getTenantId());
			data.setDirection(dataConversionVO.getDirection());

			ConversionTable entity = converterService.saveOrUpdate(data);
			if (entity != null) {
				return "record saved succussfully";
			} else {
				return "fail to save record";
			}
		}
	}

	
	@PostMapping(value = "/convertRecords", produces = "application/json")
	public ResponseEntity<?> convertRecords(@RequestBody Map<String, Object> inputMap, @RequestParam int apiId) {

		List<ConversionTable> tableData = converterService.getbyApiNumber(apiId); // Fetch all apiId Specific rows from
																					// // // the table
		Map<String, String> incomingTranslationMap = new HashMap<>();
		Map<String, String> outgoingTranslationMap = new HashMap<>();
		tableData.forEach(p -> {
			if (p != null && p.getDirection().equals("incoming")) {
				incomingTranslationMap.put(p.getIncomingColumnName(), p.getOutgoingColumnName());
			}
			if (p != null && p.getDirection().equals("outgoing")) {
				outgoingTranslationMap.put(p.getIncomingColumnName(), p.getOutgoingColumnName());
			}
		});

		Map<String, Object> requestMap = converterService.translateInputMap(inputMap, incomingTranslationMap);

		ResponseEntity<Map<String, Object>> response = converterService.sendToAnotherService(requestMap, apiId);

		Map<String, Object> responseReceived = response.getBody();

		Map<String, Object> outgoingMap = converterService.translateResponseMap(responseReceived,
				outgoingTranslationMap);

		return new ResponseEntity<>(outgoingMap, response.getStatusCode());
	}

	@PostMapping(value="/abcs",produces = "application/json")
	public String getKey(@RequestParam Integer tenantId, @RequestParam String lookupValue,@RequestParam String LookupCode) {
		StringBuilder sb = new StringBuilder();
		sb.append(tenantId).append("-").append(lookupValue).append("-")
				.append(LookupCode).append(".");
		return sb.toString();
	}
	
}
