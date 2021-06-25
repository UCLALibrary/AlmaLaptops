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
    private int total_record_count;

    @XmlElement(name = "member")
    private List<AlmaMember> member;

    public MemberList() {
        super();
    }

    public List<AlmaMember> getMember() {
        return member;
    }

    public void setMember(List<AlmaMember> records) {
        this.member = records;
    }

    public int getTotal_record_count() {
        return total_record_count;
    }

    public void setTotal_record_count(int recordCount) {
        this.total_record_count = recordCount;
    }
}
