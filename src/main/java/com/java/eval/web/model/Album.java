package com.java.eval.web.model;

import javax.persistence.*;


@Entity
@Table(name="album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "AlbumId") //Maybe useful because the id isn't id in database but AlbumId
    private Integer albumId;

    @Column(name = "Title")
    private String title;

    @Column(name = "ArtisteId")
    private Integer artistId;

    //Getters

    public Integer getAlbumId(){
        return this.albumId;
    }

    public String getTitle(){
        return this.title;
    }

    public Integer getArtistId(){
        return this.artistId;
    }

    //Setters

    public void setAlbumId(Integer albumId){
        this.albumId = albumId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setArtistId(Integer artistId){
        this.artistId = artistId;
    }
}
