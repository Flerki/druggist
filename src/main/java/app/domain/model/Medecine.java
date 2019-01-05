package app.domain.model;




import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medecine", schema = "public")
public class Medecine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date expirationDate;
    private String description;
    private String category;
    private int ownerId;

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Medecine(int id, String name, Date expirationDate, String
            category) {
        this.id = id;
        this.name = name;
        this.expirationDate = expirationDate;
        this.category = category;
    }

    public Medecine() {
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }


    public String getCategory() {
        return category;
    }


}
