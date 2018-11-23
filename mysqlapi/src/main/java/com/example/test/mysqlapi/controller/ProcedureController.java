package com.example.test.mysqlapi.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.mysqlapi.model.Note;

@RestController
@RequestMapping("/api")
public class ProcedureController {
	@PersistenceContext
    private EntityManager em;
	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Note> getByIsbn() {
		StoredProcedureQuery sp = em.createStoredProcedureQuery("Notes_Reports_RetrieveList", Note.class);
		//sp.registerStoredProcedureParameter("isbn", String.class, ParameterMode.IN);
		//sp.setParameter("isbn", isbn);

		boolean result = sp.execute();
		if (result == true) {
			return sp.getResultList();
		} else {
			// Handle the false for no result set returned, e.g.
			throw new RuntimeException("No result set(s) returned from the stored procedure");
		}
	}
}


/**
 * The below class and method can be used as a reference for calling stored procedure from SQL server 
 */

/*@RestController
@RequestMapping("/api")
public class BookService {

    @RequestMapping(value = "/books",
            params = {"isbn"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Book> getByIsbn(@RequestParam(value = "isbn") String isbn){
        StoredProcedureQuery sp = em.createStoredProcedureQuery("name.of.stored.procedure", Book.class);               
        sp.registerStoredProcedureParameter("isbn", String.class, ParameterMode.IN);
        sp.setParameter("isbn", isbn);

        boolean result = sp.execute();
        if (result == true) {
            return sp.getResultList();
        } else {
            // Handle the false for no result set returned, e.g.
            throw new RuntimeException("No result set(s) returned from the stored procedure"); 
        }
}

}*/