package be.vdab.dao;

import java.math.BigDecimal;
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
				.setParameter("naam", "%"+naam+"%")
				.getResultList();
	}
	
	public void prijsverhoging(BigDecimal factor) {
		  getEntityManager().createNamedQuery("Artikel.prijsverhoging")
		    .setParameter("factor", factor)
		    .executeUpdate();
	}
	
	public List<Artikel> findAll() {
		    return getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class)
		    .getResultList();
	}
}