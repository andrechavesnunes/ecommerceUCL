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
public class Transportadora extends Model{
	@Id
	@GeneratedValue
	public long Id;
	
	@Required
	public String Nome;
	
	public String CNPJ;
	
	public static Finder<Long,Transportadora> find = new Finder<Long,Transportadora>(Transportadora.class);
}
