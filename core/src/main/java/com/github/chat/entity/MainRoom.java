package com.github.chat.entity;

import javax.persistence.*;

@Entity
@Table(name = "main_room_table", schema = "public", catalog = "d5ld3iihtli9rs")
public class MainRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_id")
    private Long id;

    @Column(name = "main_name")
    private String name;

}
