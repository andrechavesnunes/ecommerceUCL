package controllers;

import play.*;
import play.mvc.*;
import play.data.Form;
import views.html.*;

import java.util.List;

import models.*;

public class Application extends Controller {
	private final Form<Categoria> formIndex = Form.form(Categoria.class);
    public Result index() {
    	List<Categoria> categorias = Categoria.find.all();
    	return ok(index.render("Bem vindo ao Planeta Geek",categorias));
    	
    	
    }

}
