package edu.ucla.library.libservices.alma.laptops.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "type")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlmaMember {

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "link")
    private String link;

    @XmlElement(name = "description")
    private String description;

    public AlmaMember() {
        super();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
