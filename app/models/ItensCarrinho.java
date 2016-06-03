package models;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import play.data.validation.Constraints.Required;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ItensCarrinho extends Model {
	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public Carrinho Carrinho;
	
	@Required 
	public double Valor;
	
	@Required 
	public double Qtd = 1;
	
	@Required
	public double Desconto = 0;
	
	@Required
	public Produto Produto;
	
	
	
	
	
	

}
