
package edu.ucla.library.libservices.alma.laptops.beans;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "item_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemData {

    @XmlElement(name = "barcode")
    private String barcode;

    @XmlElement(name = "base_status")
    private String status;

    @XmlElement(name = "policy")
    private String type;

    @XmlElement(name = "library")
    private String location;

    public ItemData() {
        super();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
