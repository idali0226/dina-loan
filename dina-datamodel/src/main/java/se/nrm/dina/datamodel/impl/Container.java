/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nrm.dina.datamodel.impl;
 
import se.nrm.dina.datamodel.BaseEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;  
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient; 

/**
 *
 * @author idali
 */
@Entity
@Table(name = "container")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Container.findAll", query = "SELECT c FROM Container c"),
    @NamedQuery(name = "Container.findByContainerID", query = "SELECT c FROM Container c WHERE c.containerID = :containerID"),  
    @NamedQuery(name = "Container.findByCollectionMemberID", query = "SELECT c FROM Container c WHERE c.collectionMemberID = :collectionMemberID"),
    @NamedQuery(name = "Container.findByDescription", query = "SELECT c FROM Container c WHERE c.description = :description"),
    @NamedQuery(name = "Container.findByName", query = "SELECT c FROM Container c WHERE c.name = :name"), 
    @NamedQuery(name = "Container.findByType", query = "SELECT c FROM Container c WHERE c.type = :type")})
public class Container extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ContainerID")
    private Integer containerID;
    
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CollectionMemberID")
    private int collectionMemberID;
    
    @Size(max = 255)
    @Column(name = "Description")
    private String description;
    
    @Size(max = 64)
    @Column(name = "Name")
    private String name;
    
    @Column(name = "Number")
    private Integer number;
    
    @Column(name = "Type")
    private Short type;
    
    @OneToMany(mappedBy = "parentID", fetch =  FetchType.LAZY)
    private List<Container> containerList;
    
    @JoinColumn(name = "ParentID", referencedColumnName = "ContainerID")
    @ManyToOne
    private Container parentID;
    
    @JoinColumn(name = "ModifiedByAgentID", referencedColumnName = "AgentID")
    @ManyToOne
    private Agent modifiedByAgentID;
    
    @JoinColumn(name = "CreatedByAgentID", referencedColumnName = "AgentID")
    @ManyToOne
    private Agent createdByAgentID;
    
    @JoinColumn(name = "StorageID", referencedColumnName = "StorageID")
    @ManyToOne
    private Storage storageID;
    
    @OneToMany(mappedBy = "containerOwnerID", fetch = FetchType.LAZY)
    private List<Collectionobject> collectionobjectList;
    
    @OneToMany(mappedBy = "containerID", fetch = FetchType.LAZY)
    private List<Collectionobject> collectionobjectList1;

    public Container() {
    }

    public Container(Integer containerID) {
        this.containerID = containerID;
    }

    public Container(Integer containerID, Date timestampCreated, int collectionMemberID) {
        this.containerID = containerID;
        this.timestampCreated = timestampCreated;
        this.collectionMemberID = collectionMemberID;
    }
    
    @XmlID
    @XmlAttribute(name = "id")
    @Override
    public String getIdentityString() {
        return String.valueOf(containerID);
    }
    
//    @XmlAttribute(name = "uuid") 
//    @Override
//    public String getUUID() {
//        return Util.getInstance().getURLLink(this.getClass().getSimpleName()) + containerID;
//    }
    
    @Override
    public int getEntityId() {
        return containerID;
    }

    public Integer getContainerID() {
        return containerID;
    }

    public void setContainerID(Integer containerID) {
        this.containerID = containerID;
    }
  

    public int getCollectionMemberID() {
        return collectionMemberID;
    }

    public void setCollectionMemberID(int collectionMemberID) {
        this.collectionMemberID = collectionMemberID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @XmlTransient
    public List<Container> getContainerList() {
        return containerList;
    }

    public void setContainerList(List<Container> containerList) {
        this.containerList = containerList;
    }

    @XmlIDREF
    public Container getParentID() {
        return parentID;
    }

    public void setParentID(Container parentID) {
        this.parentID = parentID;
    }

    @XmlIDREF
    public Agent getModifiedByAgentID() {
        return modifiedByAgentID;
    }

    public void setModifiedByAgentID(Agent modifiedByAgentID) {
        this.modifiedByAgentID = modifiedByAgentID;
    }

    @XmlIDREF
    public Agent getCreatedByAgentID() {
        return createdByAgentID;
    }

    public void setCreatedByAgentID(Agent createdByAgentID) {
        this.createdByAgentID = createdByAgentID;
    }

    @XmlIDREF
    public Storage getStorageID() {
        return storageID;
    }

    public void setStorageID(Storage storageID) {
        this.storageID = storageID;
    }

    @XmlTransient
    public List<Collectionobject> getCollectionobjectList() {
        return collectionobjectList;
    }

    public void setCollectionobjectList(List<Collectionobject> collectionobjectList) {
        this.collectionobjectList = collectionobjectList;
    }

    @XmlTransient
    public List<Collectionobject> getCollectionobjectList1() {
        return collectionobjectList1;
    }

    public void setCollectionobjectList1(List<Collectionobject> collectionobjectList1) {
        this.collectionobjectList1 = collectionobjectList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (containerID != null ? containerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Container)) {
            return false;
        }
        Container other = (Container) object;
        return !((this.containerID == null && other.containerID != null) || (this.containerID != null && !this.containerID.equals(other.containerID)));
    }

    @Override
    public String toString() {
        return "se.nrm.dina.datamodel.Container[ containerID=" + containerID + " ]";
    } 
}
