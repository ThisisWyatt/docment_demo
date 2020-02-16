package com.smart.demo.domain;

import javax.persistence.*;

/**
 * Description TODO
 * Author Cloudr
 * Date 2020/2/15 15:23
 **/
@Entity
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "logo", columnDefinition = "longblob",nullable = true)
    private byte[] logo;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getLogo() {
        return logo;
    }
}
