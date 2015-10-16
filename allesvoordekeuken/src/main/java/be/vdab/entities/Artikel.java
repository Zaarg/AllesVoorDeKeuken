package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import be.vdab.valueobjects.Korting;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "soort")
@NamedEntityGraph(name = "Artikel.metGroep", attributeNodes = @NamedAttributeNode("artikelgroep"))
public abstract class Artikel implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue 
	private long id;
	private String naam; 
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	@ElementCollection 
	@CollectionTable(name = "kortingen", joinColumns = @JoinColumn(name = "artikelid"))  
	@OrderBy("kortingspercentage") 
	private Set<Korting> kortingen;	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false) 
	@JoinColumn(name = "artikelgroepid") 
	private Artikelgroep artikelgroep;
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, Artikelgroep artikelgroep) {
		kortingen = new LinkedHashSet<>();
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
		setArtikelgroep(artikelgroep);
	}
	
	public Artikel() {}
	
	public Artikelgroep getArtikelgroep() {
		return artikelgroep;
	}

	public void setArtikelgroep(Artikelgroep artikelgroep) {
		  if (this.artikelgroep != null && this.artikelgroep.getArtikels().contains(this)) {   // als de andere kant nog niet bijgewerkt is
		    this.artikelgroep.removeArtikel(this);   									   // werk je de andere kant bij
		  }
		  this.artikelgroep = artikelgroep;
		  if (artikelgroep != null && !artikelgroep.getArtikels().contains(this)) {   // als de andere kant nog niet bijgewerkt is
			  artikelgroep.addArtikel(this);   										// werk je de andere kant bij
		  }
	}
	
	public Set<Korting> getKortingen() {
		  return Collections.unmodifiableSet(kortingen);
	}
	  
	public void addKorting(Korting korting) {
		  kortingen.add(korting);
	} 
		
	public void removeKorting(Korting korting) {
		  kortingen.remove(korting);
	}
	
	public void setNaam(String naam) {
    	if (!isNaamValid(naam)) {
			throw new IllegalArgumentException();
		}
    	this.naam = naam;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs) {
		if (!isPrijsValid(aankoopprijs)) {
			throw new IllegalArgumentException();
		}
		this.aankoopprijs = aankoopprijs;
	}

	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		if (!isPrijsValid(verkoopprijs) || verkoopprijs.compareTo(getAankoopprijs()) < 0) {
			throw new IllegalArgumentException();
		}
		this.verkoopprijs = verkoopprijs;
	}
	
	public static boolean isNaamValid(String naam) { 
		return naam != null && ! naam.isEmpty();
	}
	
	public static boolean isPrijsValid(BigDecimal prijs) {
		return prijs != null && prijs.compareTo(BigDecimal.ZERO) >= 0;
	}
	
	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}
	
	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}
	
	public BigDecimal getWinstPercent() {
		return new BigDecimal(100).multiply(verkoopprijs.subtract(aankoopprijs).divide(aankoopprijs, 2, RoundingMode.HALF_UP));
	}
	
	@Override
	public boolean equals(Object object) {
	  if (!(object instanceof Artikel)) {
	    return false;
	  }
	  Artikel anderArtikel = (Artikel) object;
	  return naam.equalsIgnoreCase(anderArtikel.naam);} 
	
	@Override
	public int hashCode() {
	  return naam.toUpperCase().hashCode();
	} 
} 