package com.batch.springBatch.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;

import com.batch.springBatch.entity.Insurance;


public class ExcelDataRowMapper implements RowMapper<Insurance> {

	   @Override
	    public Insurance mapRow(RowSet rs) throws Exception {
	        Insurance insurance = new Insurance();
	        
	        
	        String[] rowDataArray = rs.getCurrentRow();
	        List<String> rowData = Arrays.asList(rowDataArray);

	       
	        insurance.setPolicy(Integer.parseInt(rowData.get(0)));

	        insurance.setCategory(rowData.get(6));
	        insurance.setEmail(rowData.get(16));
	        insurance.setNAME(rowData.get(7));
	        
	        return insurance;
	    }

}

