package com.java.eval.web.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "artistid") //Maybe useful because the id isn't id in database but ArtistId
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<Album> albums = new HashSet();
    //private Set<Album> albums = new HashSet();

    //Getters

    public Integer getArtistId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public Set<Album> getAlbums(){
        return this.albums;
    }

    //Setters

    public void setArtistId(Integer artisteId){
        this.id = artisteId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAlbums(Set<Album> albumsList){
        this.albums = albumsList;
    }

}
