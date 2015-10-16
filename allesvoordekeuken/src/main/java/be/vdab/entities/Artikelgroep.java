package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "artikelgroepen")
public class Artikelgroep implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	private String naam; 
	
	@OneToMany (mappedBy = "artikelgroep")  
	@OrderBy("naam") 
	private Set<Artikel> artikels;
			
	public Artikelgroep(String naam) {
		artikels = new HashSet<>();
		setNaam(naam);
	}
	
	protected Artikelgroep() {}
		
	public void setNaam(String naam) {
    	if (!isNaamValid(naam)) {
			throw new IllegalArgumentException();
		}
    	this.naam = naam;
	}
		
	public static boolean isNaamValid(String naam) { 
		return naam != null && ! naam.isEmpty();
	}
		
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public Set<Artikel> getArtikels() {
	    return Collections.unmodifiableSet(artikels);
    } 
  
    public void addArtikel(Artikel artikel) {
	  artikels.add(artikel);
	  if (artikel.getArtikelgroep() != this) { // als de andere kant nog niet bijgewerkt is
	    artikel.setArtikelgroep(this);         // werk je de andere kant bij
	  }
    }  
  
    public void removeArtikel(Artikel artikel) {
	  artikels.remove(artikel);
	  if (artikel.getArtikelgroep() == this) { // als de andere kant nog niet bijgewerkt is
	    artikel.setArtikelgroep(null);         // werk je de andere kant bij
	  }
    }
	
    @Override
    public boolean equals(Object object) {
      if (!(object instanceof Artikelgroep)) {
        return false;
      }
      Artikelgroep andereArtikelgroep = (Artikelgroep) object;
      return naam.equalsIgnoreCase(andereArtikelgroep.naam);
    } 
    
    @Override
    public int hashCode() {
      return naam.toUpperCase().hashCode();
    }
	
} 