/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.impl;
 
import se.nrm.dina.datamodel.EntityBean;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement; 

/**
 *
 * @author idali
 */
@Entity
@Table(name = "geoname")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geoname.findAll", query = "SELECT g FROM Geoname g"),
    @NamedQuery(name = "Geoname.findByGeonameId", query = "SELECT g FROM Geoname g WHERE g.geonameId = :geonameId"),
    @NamedQuery(name = "Geoname.findByName", query = "SELECT g FROM Geoname g WHERE g.name = :name"), 
    @NamedQuery(name = "Geoname.findByLatitude", query = "SELECT g FROM Geoname g WHERE g.latitude = :latitude"),
    @NamedQuery(name = "Geoname.findByLongitude", query = "SELECT g FROM Geoname g WHERE g.longitude = :longitude"),  
    @NamedQuery(name = "Geoname.findByTimezone", query = "SELECT g FROM Geoname g WHERE g.timezone = :timezone"),
    @NamedQuery(name = "Geoname.findByModdate", query = "SELECT g FROM Geoname g WHERE g.moddate = :moddate"),
    @NamedQuery(name = "Geoname.findByISOCode", query = "SELECT g FROM Geoname g WHERE g.iSOCode = :iSOCode")})
public class Geoname implements EntityBean, Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "geonameId")
    private Integer geonameId;
    
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    
    @Size(max = 255)
    @Column(name = "asciiname")
    private String asciiname;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "alternatenames")
    private String alternatenames;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private BigDecimal latitude;
    
    @Column(name = "longitude")
    private BigDecimal longitude;
    
    @Column(name = "fclass")
    private Character fclass;
    
    @Size(max = 10)
    @Column(name = "fcode")
    private String fcode;
    
    @Size(max = 2)
    @Column(name = "country")
    private String country;
    
    @Size(max = 60)
    @Column(name = "cc2")
    private String cc2;
    
    @Size(max = 20)
    @Column(name = "admin1")
    private String admin1;
    
    @Size(max = 80)
    @Column(name = "admin2")
    private String admin2;
    
    @Size(max = 20)
    @Column(name = "admin3")
    private String admin3;
    
    @Size(max = 20)
    @Column(name = "admin4")
    private String admin4;
    
    @Column(name = "population")
    private Integer population;
    
    @Column(name = "elevation")
    private Integer elevation;
    
    @Column(name = "gtopo30")
    private Integer gtopo30;
    
    @Size(max = 40)
    @Column(name = "timezone")
    private String timezone;
    
    @Column(name = "moddate")
    @Temporal(TemporalType.DATE)
    private Date moddate;
    
    @Size(max = 24)
    @Column(name = "ISOCode")
    private String iSOCode;

    public Geoname() {
    }
    
    @XmlID
    @XmlAttribute(name = "id")
    @Override
    public String getIdentityString() {
        return String.valueOf(geonameId);
    }
    
//    @XmlAttribute(name = "uuid") 
//    @Override
//    public String getUUID() {
//        return Util.getInstance().getURLLink(this.getClass().getSimpleName()) + geonameId;
//    }
    
    @Override
    public int getEntityId() {
        return geonameId;
    }

    public Geoname(Integer geonameId) {
        this.geonameId = geonameId;
    }

    public Integer getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(Integer geonameId) {
        this.geonameId = geonameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatenames() {
        return alternatenames;
    }

    public void setAlternatenames(String alternatenames) {
        this.alternatenames = alternatenames;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Character getFclass() {
        return fclass;
    }

    public void setFclass(Character fclass) {
        this.fclass = fclass;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCc2() {
        return cc2;
    }

    public void setCc2(String cc2) {
        this.cc2 = cc2;
    }

    public String getAdmin1() {
        return admin1;
    }

    public void setAdmin1(String admin1) {
        this.admin1 = admin1;
    }

    public String getAdmin2() {
        return admin2;
    }

    public void setAdmin2(String admin2) {
        this.admin2 = admin2;
    }

    public String getAdmin3() {
        return admin3;
    }

    public void setAdmin3(String admin3) {
        this.admin3 = admin3;
    }

    public String getAdmin4() {
        return admin4;
    }

    public void setAdmin4(String admin4) {
        this.admin4 = admin4;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public Integer getGtopo30() {
        return gtopo30;
    }

    public void setGtopo30(Integer gtopo30) {
        this.gtopo30 = gtopo30;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Date getModdate() {
        return moddate;
    }

    public void setModdate(Date moddate) {
        this.moddate = moddate;
    }

    public String getISOCode() {
        return iSOCode;
    }

    public void setISOCode(String iSOCode) {
        this.iSOCode = iSOCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (geonameId != null ? geonameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Geoname)) {
            return false;
        }
        Geoname other = (Geoname) object;
        return !((this.geonameId == null && other.geonameId != null) || (this.geonameId != null && !this.geonameId.equals(other.geonameId)));
    }

    @Override
    public String toString() {
        return "se.nrm.dina.datamodel.Geoname[ geonameId=" + geonameId + " ]";
    }
    
}
