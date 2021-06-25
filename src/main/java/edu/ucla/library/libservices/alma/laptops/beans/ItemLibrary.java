
package edu.ucla.library.libservices.alma.laptops.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "policy")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemLibrary {

    @XmlElement(name = "desc")
    private String desc;

    @XmlElement(name = "value")
    private String value;

    public ItemLibrary() {
        super();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

