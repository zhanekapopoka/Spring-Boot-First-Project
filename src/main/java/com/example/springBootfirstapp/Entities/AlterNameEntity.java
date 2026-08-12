package com.example.springBootfirstapp.Entities;

import jakarta.persistence.*;


@Entity
@Table(name = "alter_name_table")
public class AlterNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String alterName;

    public AlterNameEntity(){

    }
    public AlterNameEntity(Integer id, String alterName){
        this.id=id;
        this.alterName=alterName;
    }
    public Integer getId(){
        return id;
    }
    public String getAlterName(){
        return alterName;
    }

    public void setAlterName(String alterName) {
        this.alterName = alterName;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
