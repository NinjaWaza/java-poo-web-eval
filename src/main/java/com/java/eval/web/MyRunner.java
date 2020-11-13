package com.java.eval.web;

import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void run(String... strings) throws Exception{
        System.out.println("Hello world !");
//        Artist myArtiste = artistRepository.findById(1).get();
//        System.out.println(myArtiste.getName());

//        for(Artist a : this.artistRepository.findAll()){
//            System.out.println(a.getArtistId() + " " + a.getName());
//        }

//        for(Album unAlbum : this.albumRepository.findAll()){
//            System.out.println(unAlbum.getAlbumId() + " " +unAlbum.getTitle());
//        }

//        for(Album unAlbum : myArtiste.getAlbums()){
//            System.out.println(unAlbum.getAlbumId() + " " +unAlbum.getTitle());
//        }

        for(Artist artist : this.artistRepository.findByNameContaining("Aerosmith")){
            System.out.println(artist.getName());
        }

    }
}
