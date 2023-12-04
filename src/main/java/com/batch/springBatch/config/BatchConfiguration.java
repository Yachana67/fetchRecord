package com.batch.springBatch.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.batch.api.chunk.listener.ItemReadListener;
import javax.batch.api.chunk.listener.ItemWriteListener;
import javax.batch.runtime.JobExecution;
import javax.sql.DataSource;

import org.apache.xmlbeans.impl.common.XPath.ExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.extensions.excel.RowMapper;
import org.springframework.batch.extensions.excel.mapping.BeanWrapperRowMapper;
import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.extensions.excel.support.rowset.RowSet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.batch.springBatch.entity.Insurance;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	
	
	@Autowired
    private JobBuilderFactory jobBuilderFactory;
	

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
   
//   
    
 
    int offset = 0;
    int pageSize = 20;

    @Bean
    @StepScope
    public PoiItemReader<Insurance> excelReader(@Value("#{jobExecutionContext['offset']}") Long jobOffset) {
        int linesToSkip;

       // System.out.println("Excel Offset: " + jobOffset);
   if (jobOffset != null && jobOffset == 0) {
            offset = jobOffset.intValue();
            linesToSkip = offset;
        } else {
            linesToSkip = offset + 1;
            offset = offset + pageSize;
        }

        PoiItemReader<Insurance> reader = new PoiItemReader<>();
        reader.setResource(new ClassPathResource("InsuranceData.xlsx"));
        reader.setLinesToSkip(linesToSkip);
        reader.setMaxItemCount(pageSize * 1); 
        reader.setRowMapper(excelDataRowMapper());

        return reader;
    }

   
    
    private RowMapper<Insurance> excelDataRowMapper() {
        return new ExcelDataRowMapper();
    }
    
    @Bean
    public ItemProcessor<Insurance, Insurance> yourItemProcessor() {
        return new ItemProcess(); 	

    }
   
    @Bean
    public ItemWriter<Insurance> writer() {
       return new Write();
    }

  
    
    
    @Bean
    public Step myStep(ItemReader<Insurance> reader,ItemProcessor<Insurance, Insurance> processor, ItemWriter<Insurance> writer) {
        return stepBuilderFactory.get("myStep")
              .<Insurance,Insurance>chunk(20) 
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job myJob(Step myStep) {
        return jobBuilderFactory.get("myJob")
                .incrementer(new RunIdIncrementer())
                .start(myStep)
                .build();
    }
}
