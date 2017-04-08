package demo.data;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
// <1>
@EntityListeners(AuditingEntityListener.class)
// <2>
public class BaseEntity {

 @CreatedDate
 private Long createdAt; // <3>

 @LastModifiedDate
 private Long lastModified; // <4>

}
