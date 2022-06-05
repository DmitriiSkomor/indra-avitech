package com.indra.avitech.producerconsumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "SUSERS")
public class User {
    @Id
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_GUID")
    private String GUID;
    @Column(name = "USER_NAME")
    private String name;
}
