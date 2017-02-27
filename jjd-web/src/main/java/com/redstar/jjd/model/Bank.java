package com.redstar.jjd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by Pengfei on 2015/12/18.
 */
@Entity
@Table(name = "T_BANK")
public class Bank implements Serializable {
    /**
     * serialVersionUID:TODO
     */
    private static final long serialVersionUID = 2571501920030857840L;
    private Long id;
    private String name;
    private String logo;

    @Id
    @SequenceGenerator(name = "bankSequence", sequenceName = "SEQ_BANK")
    @GeneratedValue(generator = "bankSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "LOGO")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
