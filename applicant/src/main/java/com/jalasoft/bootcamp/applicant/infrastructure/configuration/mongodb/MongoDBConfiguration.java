package com.jalasoft.bootcamp.applicant.infrastructure.configuration.mongodb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoSimpleTypes;

@Configuration
public class MongoDBConfiguration
{

    /*
     * Configures the transaction manager to MongoDB, this is disabled by default.
     * This requires replicas to perform this under a transaction.
     * https://docs.mongodb.com/manual/reference/method/Session.startTransaction/
     */
    @Bean
    MongoTransactionManager txManager(MongoDatabaseFactory dbFactory)
    {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public MongoTemplate mongoTemplate(
        MongoDatabaseFactory mongoDbFactory,
        MongoMappingContext context)
    {

        MappingMongoConverter converter =
            new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        converter.setCustomConversions(customConversions());
        converter.afterPropertiesSet();

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;
    }

    @Bean
    public MongoMappingContext mongoMappingContext()
    {
        MongoMappingContext context = new MongoMappingContext();
        context.setSimpleTypeHolder(new SimpleTypeHolder(new HashSet<>(Arrays.asList(
            LocalDate.class,
            LocalDateTime.class,
            Date.class
        )), MongoSimpleTypes.HOLDER));
        return context;
    }

    public MongoCustomConversions customConversions()
    {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        converterList.add(LocalDateToDateConverter.INSTANCE);
        converterList.add(DateToLocalDateConverter.INSTANCE);
        return new MongoCustomConversions(converterList);
    }

    @WritingConverter
    enum LocalDateToDateConverter implements Converter<LocalDate,
        Date>
    {
        INSTANCE;

        public Date convert(final LocalDate source)
        {
            ZoneId defaultZoneId = ZoneId.systemDefault();

            return source == null ? null
                : Date.from(source.atStartOfDay(defaultZoneId).toInstant());
        }
    }

    @ReadingConverter
    enum DateToLocalDateConverter implements Converter<Date,
        LocalDate>
    {
        INSTANCE;

        public LocalDate convert(final Date source)
        {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            return source == null ? null : source.toInstant().atZone(defaultZoneId).toLocalDate();
        }
    }
}




