package be.vdab.services;

import java.util.List;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;

public class ArtikelService {
	
	private final ArtikelDAO artikelDAO = new ArtikelDAO();   
	
	public Artikel read(long id) { 
		return artikelDAO.read(id);     
		
	}
	
	public void create(Artikel artikel) {
		artikelDAO.beginTransaction();
		artikelDAO.create(artikel);
		artikelDAO.commit();
	}
	
	public List<Artikel> findNaam(String naam) {
		return artikelDAO.findNaam(naam);
	}
} 