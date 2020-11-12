package com.java.eval.web.controller;

import com.java.eval.web.model.Album;
import com.java.eval.web.model.Artist;
import com.java.eval.web.repository.ArtistRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "/{id}"
    )
    public Artist getArtistById(@PathVariable(value = "id") Integer idArtist){
        Optional<Artist> artist = this.artistRepository.findById(idArtist);
        if(artist.isEmpty()){
            throw new EntityNotFoundException("L'artiste d'identifiant : " + idArtist + " n'a pas été trouvé !");
        }
        return artist.get();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = "name"
    )
    public List<Artist> getArtistByNameLike(@RequestParam(value = "name") String artistName){
        return this.artistRepository.findByNameContaining(artistName);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE,
            params = {"page","size","sortDirection", "sortProperty"}
    )
    public Page<Artist> findAll(
            @RequestParam(defaultValue = "0",value = "page") Integer page,
            @RequestParam(defaultValue = "10",value = "size") Integer size,
            @RequestParam(defaultValue = "0",value = "sortDirection") String direction,
            @RequestParam(defaultValue = "0",value = "sortProperty") String property){
        if(page < 0){
            throw new IllegalArgumentException("La page doit être un entier positif ou null !");
        }
        if(page < 0 || page > 50){
            throw new IllegalArgumentException("La page doit être un entier positif compris entre 0 et 50 !");
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direction), property);
        return artistRepository.findAll(pageRequest);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, //Car on retourne un artiste
            consumes = MediaType.APPLICATION_JSON_VALUE, //Car on récupère en interne les données pour pouvoir les ajouter (crée l'artiste)
            value = ""
    )
    public Artist createAnArtist(@RequestBody Artist artist){
        for(Artist anArtist : this.artistRepository.findAll()){
            if(anArtist.getName().equals(artist.getName())){//On lève une exception (si le nom est déjà pris en base de données)
                throw new DuplicateRequestException("L'artiste ayant pour nom :" + artist.getName() + " existe déjà en base de données, choisissé un autre nom s'il vous plait !");
            }
            if(anArtist.equals(artist)){
                throw new DuplicateRequestException("L'artiste   id : " + artist.getArtistId() + " | nom : " + artist.getName() + " existe déjà en base de données !");
            }
        }
        return this.artistRepository.save(artist); //Sauvegarder l'artiste en base de données et retourner l'objet
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE, //Car on retourne un artiste
            consumes = MediaType.APPLICATION_JSON_VALUE, //Car on récupère en interne les données pour pouvoir les modifiers (modification des informations l'artiste)
            value = ""
    )
    public Artist editAnArtist(@RequestBody Artist artist){
        Artist artistToEdit = this.artistRepository.findById(artist.getArtistId()).get();
        if(artistToEdit == null){
            throw new EntityNotFoundException("L'artiste ayant pour id : " + artistToEdit.getArtistId() + " ne semble pas existé dans la base de données (spoiler il existe pas) veuillez réssayer !");
        }
        for(Artist artistToCompareWith : this.artistRepository.findAll()){
            if(artistToCompareWith.getName().equals(artist.getName())){
                throw new EntityNotFoundException("L'artiste ayant pour nom d'artiste : " + artistToEdit.getName() + " existe déjà, veuillez choisir un autre nom !");
            }
        }
        return this.artistRepository.save(artist); //Sauvegarder l'artiste en base de données (la modification de ces données) et retourner l'objet (modifié)
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT) //Permet de ne rien retourner
    public void deleteArtist(@PathVariable(value = "id") Integer id){
        if(this.artistRepository.findById(id).get() == null){
            throw new EntityNotFoundException("L'artiste pour l'identifiant : " + id + " n'a pas été trouvé, donc impossible de le supprimer !");
        }
        this.artistRepository.deleteById(id); //Supprimer l'artiste (cela vas automatiquement le supprimer de la base de donées car nous utilisons le repository (la couche d'accès à la base de données)
    }
}
