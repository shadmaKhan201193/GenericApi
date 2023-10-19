package com.itl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itl.entities.ConversionTable;
import com.itl.entities.Resource_urls;
import com.itl.repository.ConversionRepository;

@Service
public class ConverterService {

	@Autowired
	ConversionRepository conversionRepository;

	@Autowired
	ResourceUrlService resourceUrlService;
	
	@Autowired
	RestTemplate restTemplate;

	public ConversionTable getKey(int Id) {
		Optional<ConversionTable> key = conversionRepository.findById(Id);
		if (key.isPresent()) {
			return key.get();
		} else {
			return null;
		}
	}

	public List<ConversionTable> getAllRecords() {
		List<ConversionTable> api = conversionRepository.findAll();
		return api;
	}

	public List<ConversionTable> getbyApiNumber(int apinumber) {
		List<ConversionTable> api = conversionRepository.findByApiNumber(apinumber);
		return api;

	}

	public ConversionTable getbyIncomingColumn(String incomingColumn) {
		ConversionTable api = conversionRepository.findByIncomingColumnName(incomingColumn);
		return api;

	}

	public ConversionTable saveOrUpdate(ConversionTable entity) {
		if (entity != null) {
			return conversionRepository.save(entity);
		} else {
			return null;
		}
	}

	public ResponseEntity<Map<String, Object>> sendToAnotherService(Map<String, Object> data, int apiId) {
		try {
			Resource_urls resourceUrls = resourceUrlService.getbyApiId(apiId);
			String url = resourceUrls.getServiceUrl();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writeValueAsString(data);
			HttpEntity<String> requestEntity = new HttpEntity<>(jsonString, headers);
			ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<Map<String, Object>>() {
					});
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	public Map<String, Object> translateInputMap(Map<String, Object> inputMap, Map<String, String> translationMap) {
	    Map<String, Object> requestMap = new HashMap<>();
	    inputMap.entrySet().forEach(p -> {
	        if (translationMap.containsKey(p.getKey())) {
	            requestMap.put(translationMap.get(p.getKey()), p.getValue());
	        } else {
	            requestMap.put(p.getKey(), p.getValue());
	        }
	    });
	    return requestMap;
	}
	
	public Map<String, Object> translateResponseMap(Map<String, Object> responseReceived, Map<String, String> translationMap) {
	    Map<String, Object> outgoingMap = new HashMap<>();
	    responseReceived.entrySet().forEach(p -> {
	        if (translationMap.containsKey(p.getKey())) {
	            outgoingMap.put(translationMap.get(p.getKey()), p.getValue());
	        } else {
	            outgoingMap.put(p.getKey(), p.getValue());
	        }
	    });

	    return outgoingMap;
	}

}





