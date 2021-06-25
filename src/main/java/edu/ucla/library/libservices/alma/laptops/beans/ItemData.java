
package edu.ucla.library.libservices.alma.laptops.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "item_data")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties({ "pid", "creation_date", "modification_date", "awaiting_reshelving", "reshelving_time",
						"physical_material_type", "provenance", "po_line", "issue_date", "is_magnetic", "arrival_date",
						"expected_arrival_date", "year_of_issue", "enumeration_a", "enumeration_b", "enumeration_c",
						"enumeration_d", "enumeration_e", "enumeration_f", "enumeration_g", "enumeration_h", "chronology_i",
						"chronology_j", "chronology_k", "chronology_l", "chronology_m", "break_indicator", "pattern_type",
						"linking_number", "type_of_unit", "description", "replacement_cost", "receiving_operator",
						"process_type", "work_order_type", "work_order_at", "inventory_number", "inventory_date",
						"inventory_price", "receive_number", "weeding_number", "weeding_date", "location",
						"alternative_call_number", "alternative_call_number_type", "alt_number_source", "storage_location_id",
						"pages", "pieces", "public_note", "fulfillment_note", "due_date", "due_date_policy",
						"internal_note_1", "internal_note_2", "internal_note_3", "statistics_note_1",
						"statistics_note_2", "statistics_note_3", "requested", "edition", "imprint", "language",
						"library_details", "parsed_alt_call_number", "parsed_call_number", "parsed_issue_level_description",
						"title_abcnph", "physical_condition", "link" })
public class ItemData {

    @XmlElement(name = "barcode")
    private String barcode;

    @XmlElement(name = "base_status")
    private ItemStatus base_status;

    @XmlElement(name = "policy")
    private ItemPolicy policy;

    @XmlElement(name = "library")
    private ItemLibrary library;

    public ItemData() {
        super();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public ItemStatus getBase_status() {
        return base_status;
    }

    public void setBase_status(ItemStatus status) {
        this.base_status = status;
    }

    public ItemPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(ItemPolicy type) {
        this.policy = type;
    }

    public ItemLibrary getLibrary() {
        return library;
    }

    public void setLibrary(ItemLibrary location) {
        this.library = location;
    }
}
