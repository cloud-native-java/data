package demo;

import org.joda.time.DateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.*;

@SpringBootApplication
public class OrderApplication {

 @Bean
 CustomConversions customConversions() {
  return new CustomConversions(
   Collections.singletonList(new LongToDateTimeConverter()));
 }

 @ReadingConverter
 public static class LongToDateTimeConverter implements
  Converter<Long, DateTime> {

  @Override
  public DateTime convert(Long source) {
   return Optional.ofNullable(source).map(DateTime::new).orElse(null);
  }
 }

 public static void main(String[] args) {
  SpringApplication.run(OrderApplication.class, args);
 }
}
