package models;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import com.avaje.ebean.Model;
import com.avaje.ebean.Model.Finder;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FormaPagamento extends Model{
	@Id
	@GeneratedValue
	public long id;
	
	@Required
	public String nome;
	
	@Required
	public Integer parcelas;
	
	public static Finder<Long,FormaPagamento> find = new Finder<Long,FormaPagamento>(FormaPagamento.class);

}
