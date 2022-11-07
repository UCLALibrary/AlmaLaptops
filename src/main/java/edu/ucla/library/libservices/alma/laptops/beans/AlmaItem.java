
package edu.ucla.library.libservices.alma.laptops.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@JsonRootName(value = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties({ "bib_data", "holding_data", "link", "additional_info" })
public class AlmaItem {

    @XmlElement(name = "bib_data")
    private BibData bib_data;

    @XmlElement(name = "holding_data")
    private HoldingData holding_data;

    @XmlElement(name = "item_data")
    private ItemData item_data;

    public AlmaItem() {
        super();
    }

    public BibData getBib_data() {
        return bib_data;
    }

    public void setBib_data(BibData bib) {
        this.bib_data = bib;
    }

    public HoldingData getHolding_data() {
        return holding_data;
    }

    public void setHolding_data(HoldingData holding) {
        this.holding_data = holding;
    }

    public ItemData getItem_data() {
        return item_data;
    }

    public void setItem_data(ItemData item) {
        this.item_data = item;
    }
}
