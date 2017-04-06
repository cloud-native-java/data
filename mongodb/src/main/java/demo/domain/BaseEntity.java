package demo.domain;

import org.joda.time.DateTime;

/**
 * An abstract base class that is
 * inherited by other domain classes in
 * the order application context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public class BaseEntity {

 private DateTime lastModified, createdAt;

 public DateTime getLastModified() {
  return lastModified;
 }

 public void setLastModified(DateTime lastModified) {
  this.lastModified = lastModified;
 }

 public DateTime getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(DateTime createdAt) {
  this.createdAt = createdAt;
 }

 @Override
 public String toString() {
  return "BaseEntity{" + "lastModified=" + lastModified + ", createdAt="
   + createdAt + '}';
 }
}
