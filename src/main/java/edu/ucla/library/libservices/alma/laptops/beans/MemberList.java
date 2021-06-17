package edu.ucla.library.libservices.alma.laptops.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "members")
@XmlAccessorType(XmlAccessType.FIELD)
public class MemberList {


    @XmlAttribute(name = "total_record_count")
    private int recordCount;

    @XmlElement(name = "member")
    private List<AlmaMember> records;

    public MemberList() {
        super();
    }

    public List<AlmaMember> getRecords() {
        return records;
    }

    public void setRecords(List<AlmaMember> records) {
        this.records = records;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}
