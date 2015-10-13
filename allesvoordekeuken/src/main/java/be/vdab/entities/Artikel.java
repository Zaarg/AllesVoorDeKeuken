package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artikels")
public class Artikel implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue 
	private long id;
	private String naam; 
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
	}
	
	public Artikel() {}
	
	public static long getSerialversionuid() {
	return serialVersionUID;
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
	
} 