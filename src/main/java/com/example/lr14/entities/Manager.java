package com.example.lr14.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "manager_name")
    private String manager_name;

    @Column(name = "manager_phone_num")
    private String manager_phone_num;

    @Column(name = "manager_email")
    private String manager_email;

}
