package com.nroad.amcc.kb;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "`kb_contracted_area`")
public class ContractedArea {


    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    public String id;

    @Column
    public String name;

    @Column
    public String proportion;

    @ManyToOne
    private ProfessionDetails professionDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public ProfessionDetails getProfessionDetails() {
        return professionDetails;
    }

    public void setProfessionDetails(ProfessionDetails professionDetails) {
        this.professionDetails = professionDetails;
    }
}


