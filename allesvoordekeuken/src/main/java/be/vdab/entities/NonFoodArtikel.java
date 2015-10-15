package be.vdab.entities;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel { 
	
	private static final long serialVersionUID = 1L;
	private int garantie;
	
	public NonFoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int garantie) {
		super(naam, aankoopprijs, verkoopprijs);
		setGarantie(garantie);
	}
	
	public NonFoodArtikel() {}

	public int getGarantie() {
		return garantie;
	}

	public void setGarantie(int garantie) {
		this.garantie = garantie;
	}
	
} 