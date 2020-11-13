package com.java.eval.web.security;

import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Exception si l'utilisateur donne une valeur qui n'est pas trouvé dans la base de donées (recherche)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) //Permet de retourner une erreur 404
    public String handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        //On peut mettre ce que l'on veut, écrire dans un fichier de log, envoyé un mail ... plein de possibilité
        return entityNotFoundException.getMessage();
    }

    //Exception si l'utilisateur donne un nombre interdit
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Permet de retourner une erreur 400
    public String handleIllegalArgumentException(IllegalArgumentException e){
        return e.getMessage();
    }

    //Exception si l'utilisateur donne un nom qui existe déjà (lors de la création/modification)
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) //Permet de retourner une erreur 403
    public String handleEntityExistsException(EntityExistsException entityExistsException){
        //On peut mettre ce que l'on veut, écrire dans un fichier de log, envoyé un mail ... plein de possibilité
        return entityExistsException.getMessage();
    }

    @ExceptionHandler(DuplicateRequestException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //Permet de retourner une erreur 409 (car tentative de duplication)
    public String handleEntityDuplicateException(DuplicateRequestException duplicateRequestException){
        return duplicateRequestException.getMessage();
    }
}
