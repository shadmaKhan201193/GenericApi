package com.itl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itl.entities.Resource_urls;

@Repository
public interface ResourceUrl_Repo extends JpaRepository<Resource_urls,Integer>{

	public Resource_urls findByApiId(int apiId);
	
	//public Resource_urls findByServiceUrl(int apiId);
	
}
