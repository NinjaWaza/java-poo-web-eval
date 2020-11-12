package com.java.eval.web.model;

import javax.persistence.*;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "ArtistId") //Maybe useful because the id isn't id in database but ArtistId
    private Integer artistId;

    @Column(name = "Name")
    private String name;

    //Getters

    public Integer getArtistId(){
        return this.artistId;
    }

    public String getName(){
        return this.name;
    }

    //Setters

    public void setArtistId(Integer artisteId){
        this.artistId = artisteId;
    }

    public void setName(String name){
        this.name = name;
    }
}
