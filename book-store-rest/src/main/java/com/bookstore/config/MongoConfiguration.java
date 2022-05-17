package com.bookstore.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.bookstore.repository")
@EntityScan(basePackages = "com.bookstore.persistence.entity")
@EnableMongoAuditing
public class MongoConfiguration {
}
