package com.github.chat.entity;

import javax.persistence.*;

@Entity
@Table(name = "main_room_table", schema = "public", catalog = "d4p4rtluej9295")
public class MainRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_id")
    private Long id;

    @Column(name = "main_name")
    private String name;

}