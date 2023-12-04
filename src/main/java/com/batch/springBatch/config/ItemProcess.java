package com.batch.springBatch.config;

import org.springframework.batch.item.ItemProcessor;

import com.batch.springBatch.entity.Insurance;

public class ItemProcess implements ItemProcessor<Insurance, Insurance> {

	@Override
	public Insurance process(Insurance item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
