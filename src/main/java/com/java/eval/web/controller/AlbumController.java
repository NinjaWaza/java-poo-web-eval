package com.java.eval.web.controller;

import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.AlbumRepository;
import com.java.eval.web.repository.ArtistRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/albums")
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    //Je ne peut pas vérifier car je n'arrive pas à accéder au formulaire je ne sais donc pas quel paramèteres je doit fournir dans mon corps de POST, je n'arrive donc pas à essayé
    //ni sur le site ni tu Postman
    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, //Car on retourne un album
            consumes = MediaType.APPLICATION_JSON_VALUE, //Car on récupère en interne les données pour pouvoir les ajouter (crée l'album)
            value = ""
    )
    public Album createAnAlbum(@RequestBody Album album){
        for(Album anAlbum : this.albumRepository.findAll()){
            if(anAlbum.getTitle().equals(album.getTitle())){//On lève une exception (si le nom est déjà pris en base de données, car les albums doivent avoir un nom unique)
                throw new DuplicateRequestException("L'album ayant pour title :" + album.getTitle() + " existe déjà en base de données, choisissé un autre nom s'il vous plait !");
            }
            if(anAlbum.equals(album)){
                throw new DuplicateRequestException("L'album'  id : " + album.getAlbumId() + " | titre : " + album.getTitle() + " existe déjà en base de données !");
            }
        }
        return this.albumRepository.save(album); //Sauvegarder l'album en base de données (ajouter l'album) et retourner l'objet
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT) //Permet de ne rien retourner
    public void deleteAlbum(@PathVariable(value="id") Integer albumId){
        if(this.albumRepository.findById(albumId).get() == null){
            throw new EntityNotFoundException("L'album ayant pour identifiant : " + albumId + " n'a pas été trouvé, donc impossible de le supprimer !");
        }
        this.albumRepository.deleteById(albumId); //Supprimer l'album (cela vas automatiquement le supprimer de la base de donées car nous utilisons le repository (la couche d'accès à la base de données)
    }
}
