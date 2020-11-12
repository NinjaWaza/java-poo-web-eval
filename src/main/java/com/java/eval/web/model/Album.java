package com.java.eval.web.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "albumid") //Maybe useful because the id isn't id in database but AlbumId
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "artistid")
    private Integer artist;

    //Getters

    public Integer getAlbumId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public Integer getArtistId(){
        return this.artist;
    }

    //Setters

    public void setAlbumId(Integer albumId){
        this.id = albumId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setArtistId(Integer artistId){
        this.artist = artistId;
    }
}
