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
	public long Id;
	
	@Required
	public String Nome;
	
	@Required
	public String Cnpj;
	
	public String Ie;
	
	public String Endereco;
	
	public String Cidade;
	
	public String Uf;
	
	public String Bairro;
	
	public static Finder<Long,Fornecedor> find = new Finder<Long,Fornecedor>(Fornecedor.class);
}
