/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HongdaRestsw;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hongda
 */
@Entity
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findById", query = "SELECT c FROM Consumption c WHERE c.id = :id")
    , @NamedQuery(name = "Consumption.findByUserId", query = "SELECT c FROM Consumption c WHERE c.userId.id = :userId")
    , @NamedQuery(name = "Consumption.findByFoodId", query = "SELECT c FROM Consumption c WHERE c.foodId.id = :foodId")
    , @NamedQuery(name = "Consumption.findByDate", query = "SELECT c FROM Consumption c WHERE c.date = :date")
    , @NamedQuery(name = "Consumption.findByQuantityAndFat", query = "SELECT c FROM Consumption c,Food f WHERE c.quantity=:quantity AND f.fat=:fat AND c.foodId.id = f.id")
    , @NamedQuery(name = "Consumption.findByQuantity", query = "SELECT c FROM Consumption c WHERE c.quantity = :quantity")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @JoinColumn(name = "FOOD_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Food foodId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users userId;

    public Consumption() {
    }

    public Consumption(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
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
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HongdaRestsw.Consumption[ id=" + id + " ]";
    }
    
}
