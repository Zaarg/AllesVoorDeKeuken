package be.vdab.services;


import java.util.List;

import be.vdab.dao.ArtikelgroepDAO;
import be.vdab.entities.Artikelgroep;

public class ArtikelgroepService {
	
	private final ArtikelgroepDAO artikelgroepDAO = new ArtikelgroepDAO();   
	
	public Artikelgroep read(long id) { 
		return artikelgroepDAO.read(id);     	
	}
	
	public void create(Artikelgroep artikelgroep) {
		artikelgroepDAO.beginTransaction();
		artikelgroepDAO.create(artikelgroep);
		artikelgroepDAO.commit();
	}
		
	public List<Artikelgroep> findAll() {
	    return artikelgroepDAO.findAll();
	}
	
} 