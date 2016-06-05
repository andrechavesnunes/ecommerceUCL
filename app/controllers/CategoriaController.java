package controllers;
import play.mvc.Result;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.Request;
import java.util.List;

import models.Categoria;

public class CategoriaController extends Controller{
	private final Form<Categoria> formCategoria = Form.form(Categoria.class);
	public Result lista(){
		List<Categoria> categoria = Categoria.find.all();
		return ok(views.html.categoria.Lista.render("Listagem de Categorias",categoria));
	}
	public Result delete(Long id){
		Categoria categoria = new Categoria();
		categoria.find.ref(id).delete();

		if (categoria == null) {
		 return notFound(String.format("Categoria não existe.", id));
		}
		else{
			flash("success", String.format("Categoria Excluida com Sucesso!!", categoria));
			return redirect(routes.CategoriaController.lista());
		}
	}
	public Result Novo(){
		List<Categoria> categorias = Categoria.find.all();
		return ok(views.html.categoria.Detalhe.render(formCategoria,categorias,new Long(0)));
	}
	public Result detalhes(Long id){
		Categoria categoria = Categoria.find.byId(id);
		List<Categoria> categorias = Categoria.find.all();
		if (categoria == null) {
			return notFound(String.format("Categoria não existe.", id));
		}
		
		Form<Categoria> formPreenchido = formCategoria.fill(categoria);
		return ok(views.html.categoria.Detalhe.render(formPreenchido,categorias,categoria.id));
	}
	public Result salvar(Long id)
	{
		Form<Categoria> formEnviado = formCategoria.bindFromRequest();
		Categoria categoria = formEnviado.get();
		Categoria categoriaOld = Categoria.find.byId(id);
		if(categoriaOld != null)
		{
			categoria.update();
		} else {
			categoria.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.CategoriaController.lista());
	}
}
