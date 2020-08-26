package com.example.accessingdatajpa;

import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;

@Component
@Log
@Transactional
public class Controller {

    @PersistenceContext
    private EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    public void start(ApplicationReadyEvent event) {
        log.warning("IT WORKS");

        Author a1 = new Author(null, "Mike", new HashSet<>());


        Book b1 = new Book(null, a1);
        Book b2 = new Book(null, a1);
        Book b3 = new Book(null, a1);

       a1.getBooks().add(b1);
       a1.getBooks().add(b2);
       a1.getBooks().add(b3);

        em.persist(a1);

        a1.getBooks().remove(b3);
        b3.setAuthor(null);

        em.merge(a1);

       em.flush();

//        Author a2 = em.find(Author.class, new Long(1l));
//        System.out.println(a2.getBooks().size());
//
//        Book b = em.find(Book.class, b3.getId());
//        System.out.println(b);




    }
}
