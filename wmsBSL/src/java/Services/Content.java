/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author chetansurana
 */
@Entity
@Table(name = "Content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Content.findAll",
            query = "SELECT c FROM Content c")
    , @NamedQuery(name = "Content.findById", 
            query = "SELECT c FROM Content c WHERE c.id = :id")
    , @NamedQuery(name = "Content.findByName",
            query = "SELECT c FROM Content c WHERE c.name = :name")
    , @NamedQuery(name = "Content.findByGenre",
            query = "SELECT c FROM Content c WHERE c.genre = :genre")
    , @NamedQuery(name = "Content.findByType", 
            query = "SELECT c FROM Content c WHERE c.type = :type")
    , @NamedQuery(name = "Content.findByRating",
            query = "SELECT c FROM Content c WHERE c.rating = :rating")})
public class Content implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "genre")
    private String genre;
    @Size(max = 20)
    @Column(name = "type")
    private String type;
    @Column(name = "rating")
    private Integer rating;

    public Content() {
    }

    public Content(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Content)) {
            return false;
        }
        Content other = (Content) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Services.Content[ id=" + id + " ]";
    }
    
}
