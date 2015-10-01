package demo.domain;

/**
 * An abstract base class that is inherited by other domain classes
 * in the order application context.
 *
 * @author Kenny Bastani
 * @author Josh Long
 */
public class BaseEntity {

    private Long lastModified, createdAt;

    public Long getLastModified() {
        return lastModified;
    }

    public void setLastModified(Long lastModified) {
        this.lastModified = lastModified;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "lastModified=" + lastModified +
                ", createdAt=" + createdAt +
                '}';
    }
}
