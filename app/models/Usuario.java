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
public class Usuario extends Model{
	@Id
	@GeneratedValue
	public long Id;
	
	@Required
	public String Nome;
	
	@Required
	public String Cnpjcpf;
	
	@Required
	public String Endereco;
	
	@Required
	public String Cidade;
	
	@Required
	public String UF;
	
	@Required
	public String Bairro;
	
	@Required
	public String email;
	
	@Required
	public String senha;
	
	public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(Usuario.class);
}
