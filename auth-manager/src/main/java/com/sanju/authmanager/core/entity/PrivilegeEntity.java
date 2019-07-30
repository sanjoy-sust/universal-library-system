package com.sanju.authmanager.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "privilege")
public class PrivilegeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name ="name")
    private String name;

    @ManyToMany(mappedBy = "privileges")
    List<RoleEntity> roles;
}
