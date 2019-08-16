package com.nroad.amcc.kb;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "`kb_area_top_three`")
public class AreaTop3Profession {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    public String id;

    @Column
    public String name;

    @ManyToOne
    public Area area;

    @Column
    public int rank;

}
