package com.itl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itl.entities.Resource_urls;
import com.itl.repository.ResourceUrl_Repo;

@Service
public class ResourceUrlService {

	@Autowired
	ResourceUrl_Repo resourceUrl_Repo;

	public Resource_urls saveOrUpdate(Resource_urls entity) {
		if (entity != null) {
			return resourceUrl_Repo.save(entity);
		} else {
			return null;
		}
	}

	public Resource_urls getbyApiId(int apiId) {
		Resource_urls id = resourceUrl_Repo.findByApiId(apiId);
		return id;

	}

	
}
