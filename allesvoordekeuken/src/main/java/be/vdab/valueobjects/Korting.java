package be.vdab.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;

@Embeddable
public class Korting implements Serializable { 
  private static final long serialVersionUID = 1L;
  private int vanafAantal;
  private BigDecimal kortingsPercentage; 
  
  public Korting(int vanafAantal, BigDecimal kortingsPercentage) {
    this.vanafAantal = vanafAantal;
    this.kortingsPercentage = kortingsPercentage;
  } 
  
  protected Korting() {}

  public int getVanafAantal() {
	return vanafAantal;
  }

  public BigDecimal getKortingsPercentage() {
	return kortingsPercentage;
  }

  @Override 
  public String toString() {
    return String.format("%d %s", vanafAantal,kortingsPercentage.toString());
  } 
  
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Korting)) {
			return false;
		}
		Korting korting = (Korting) obj;
		return (vanafAantal == korting.getVanafAantal() && kortingsPercentage.equals(korting.getKortingsPercentage()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kortingsPercentage == null) ? 0 : kortingsPercentage.hashCode());
		result = prime * result + vanafAantal;
		return result;
	}

 
  
} 