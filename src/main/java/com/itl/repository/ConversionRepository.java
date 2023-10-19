package com.itl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.itl.entities.ConversionTable;



@Repository
public interface ConversionRepository extends JpaRepository<ConversionTable, Integer> {

	public List<ConversionTable>  findByApiNumber(int apiNumber);
	public ConversionTable findByIncomingColumnName(String incomingColumn);
	
}
