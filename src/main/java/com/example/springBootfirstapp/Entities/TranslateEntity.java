package com.example.springBootfirstapp.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "translation_table")
public class TranslateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String langCode;
    private String translation;
    private String fieldName;

    public TranslateEntity(Integer id, String langCode, String translation, String fieldName){
        this.id=id;
        this.langCode=langCode;
        this.translation=translation;
        this.fieldName=fieldName;
    }

    public TranslateEntity() {
    }

    public Integer getId(){
        return id;
    }

    public String getLangCode(){
        return langCode;
    }

    public String getTranslation(){
        return translation;
    }
    public String getFieldName(){
        return fieldName;
    }

    public void setId(Integer id){
        this.id=id;
    }
    public void setTranslation(String translation){
        this.translation=translation;
    }
    public void setLangCode(String langCode){
        this.langCode=langCode;
    }
    public void setFieldName(String field){
        this.fieldName = field;
    }

}
