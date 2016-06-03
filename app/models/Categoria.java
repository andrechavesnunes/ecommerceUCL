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
public class Categoria extends Model{

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String Nome;
	
	public static Finder<Long,Categoria> find = new Finder<Long,Categoria>(Categoria.class);
	
}
