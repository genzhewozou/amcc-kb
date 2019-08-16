package com.nroad.amcc.kb;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "`kb_area`")
public class Area {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Id
    public String id;

    @Column
    public String name;

    @OneToMany(mappedBy = "area")
    public List<AreaTop3Profession> areaTop3Professions;
}
