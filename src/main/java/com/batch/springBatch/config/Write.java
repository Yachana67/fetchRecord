package com.batch.springBatch.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.batch.springBatch.entity.Insurance;

public class Write implements ItemWriter<Insurance> {

	@Override
	public void write(List<? extends Insurance> items) throws Exception {
	System.out.println(items);

	}

}
