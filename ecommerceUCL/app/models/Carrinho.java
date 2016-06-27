package models;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import play.data.validation.Constraints.Required;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Carrinho extends Model{
	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public double Valor_Total;
	
	@ManyToOne
	public Usuario Usuario;
	
	@ManyToOne
	public Transportadora Transportadora;
	
	@ManyToOne
	public FormaPagamento FormaPagamento;
	
	@Required
	public Date Data;
	
	@Required
	public double Quantidade_Total;

}
