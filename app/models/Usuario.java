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
	public long id;
	
	@Required
	public String nome;
	
	@Required
	public String cpf;
	
	@Required
	public String logradouro;
	
	@Required
	public String numero;
	
	@Required
	public String bairro;
	
	@Required
	public String cidade;
	
	@Required
	public String uf;
	
	@Required
	public String cep;
	
	@Required
	public String email;

	@Required
	public String senha;
	
	@Required
	public boolean administrativo;
	
	public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(Usuario.class);	
	
}
