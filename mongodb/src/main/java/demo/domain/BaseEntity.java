package demo.domain;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class BaseEntity {

 private DateTime lastModified, createdAt;
}
