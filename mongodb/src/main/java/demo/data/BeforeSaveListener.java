package demo.data;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
class BeforeSaveListener extends AbstractMongoEventListener<BaseEntity> {

 @Override
 public void onBeforeSave(BeforeSaveEvent<BaseEntity> event) {

  DateTime timestamp = new DateTime();

  if (event.getSource().getCreatedAt() == null)
   event.getSource().setCreatedAt(timestamp);

  event.getSource().setLastModified(timestamp);

  super.onBeforeSave(event);
 }
}
