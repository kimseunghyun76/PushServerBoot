package com.hellowd.server.api.repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-08
 * Time : 오후 1:40
 * DB Properties는 아래와 같이 프로파일별로 나눠서 설정한다.
 */
@Configuration
@EnableAutoConfiguration
@PropertySource({"classpath:/properties/local/db.properties"})
public class RepositoryConfiguration {

    @Configuration
    @Profile("live1")
    @PropertySource("classpath:/properties/live1/db.properties")
    static class Live1{}

    @Configuration
    @Profile("live2")
    @PropertySource("classpath:/properties/live2/db.properties")
    static class Live2{}

    @Configuration
    @Profile("local")
    @PropertySource("classpath:/properties/local/db.properties")
    static class Local{}
}