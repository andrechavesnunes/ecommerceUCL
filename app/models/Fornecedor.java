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
public class Fornecedor extends Model{
	@Id
	@GeneratedValue
	public long id;
	
	@Required
	public String nome;
	
	@Required
	public String cnpj;
	
	public String ie;
	
	public String endereco;
	
	public String cidade;
	
	public String uf;
	
	public String bairro;
	
	public static Finder<Long,Fornecedor> find = new Finder<Long,Fornecedor>(Fornecedor.class);
}
