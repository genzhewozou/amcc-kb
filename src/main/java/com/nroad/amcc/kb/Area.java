package com.nroad.amcc.kb;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "`kb_area`")
public class Area {

    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @JsonProperty
    @Id
    public String id;

    @Column
    @JsonProperty
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "area")
    public List<AreaTop3Profession> areaTop3Professions = new ArrayList<>();

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

    public List<AreaTop3Profession> getAreaTop3Professions() {
        return areaTop3Professions;
    }

    public void setAreaTop3Professions(List<AreaTop3Profession> areaTop3Professions) {
        this.areaTop3Professions = areaTop3Professions;
    }
}
