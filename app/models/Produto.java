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
public class Produto extends Model{
	@Id
	@GeneratedValue
	public long Id;
	
	@Required
	public String descricao;
	
	@Required
	public String sobre;
	
	@Required
	public Double preco;
	
	public String imagem;
	
	@ManyToOne
	public Categoria categoria;
	
	@ManyToOne
	public Fornecedor fornecedor;
	
	@Required
	public String estoque;
	
	public static Finder<Long,Produto> find = new Finder<Long,Produto>(Produto.class);
}
