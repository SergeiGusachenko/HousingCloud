package cloud.housing.ticket.models;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private int id;
    private String subject;
    private String priority;
    private String issueType;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Ticket(String subject, String priority, String issueType) {
        this.subject = subject;
        this.priority = priority;
        this.issueType = issueType;
    }

    public Ticket(){}


    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssueType() {
        return issueType;
    }

    public int getId() {
        return id;
    }
    public int setId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getPriority() {
        return priority;
    }


    @Override
    public String toString(){
        return subject + " " +
                priority + " " +
                String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
