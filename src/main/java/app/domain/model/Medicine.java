package app.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medecine", schema = "public")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Date expirationDate;
    private String description;
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
