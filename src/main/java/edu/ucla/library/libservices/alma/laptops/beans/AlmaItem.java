
package edu.ucla.library.libservices.alma.laptops.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlmaItem {

    @XmlElement(name = "bib_data")
    private BibData theBib;

    @XmlElement(name = "holding_data")
    private HoldingData theHolding;

    @XmlElement(name = "item_data")
    private ItemData theItem;

    public AlmaItem() {
        super();
    }

    public BibData getTheBib() {
        return theBib;
    }

    public void setTheBib(BibData bib) {
        this.theBib = bib;
    }

    public HoldingData getTheHolding() {
        return theHolding;
    }

    public void setTheHolding(HoldingData holding) {
        this.theHolding = holding;
    }

    public ItemData getTheItem() {
        return theItem;
    }

    public void setTheItem(ItemData item) {
        this.theItem = item;
    }
}
