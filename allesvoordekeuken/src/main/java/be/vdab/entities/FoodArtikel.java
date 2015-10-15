package be.vdab.entities;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel { 
	
	private static final long serialVersionUID = 1L;
	private int houdbaarheid;
	
	public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, int houdbaarheid) {
		super(naam, aankoopprijs, verkoopprijs);
		setHoudbaarheid(houdbaarheid);
	}
	
	public FoodArtikel() {}

	public int getHoudbaarheid() {
		return houdbaarheid;
	}

	public void setHoudbaarheid(int houdbaarheid) {
		this.houdbaarheid = houdbaarheid;
	}
	
} 