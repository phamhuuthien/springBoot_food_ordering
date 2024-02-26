package com.IT.osahaneat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity(name="roles")
@ToString(exclude = "invoice")
@EqualsAndHashCode(exclude = "invoice")
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="role_name")
    private String roleName ;
    @Column(name="create_date")
    private Date createDate ;

    @OneToMany(mappedBy="role")
    private Set<Users> listUser;

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
