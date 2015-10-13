package be.vdab.dao;

import java.util.List;

import be.vdab.entities.Artikel;

public class ArtikelDAO extends AbstractDAO {
	
	public Artikel read(long id) {
		return getEntityManager().find(Artikel.class, id);      
	}
	
	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}
	
	public List<Artikel> findNaam(String naam) {
		return getEntityManager().createNamedQuery("Artikel.findByNaam", Artikel.class)
				.setParameter("naam", naam)
				.getResultList();
	}
}