package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;
import models.Categoria;
import models.Fornecedor;

public class FornecedorController extends Controller{
	private final Form<Fornecedor> formfornecedor = Form.form(Fornecedor.class);
	
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<Fornecedor> fornecedor = Fornecedor.find.all();
		List<Categoria> categoria = Categoria.find.all();
		
		return ok(views.html.fornecedor.Lista.render("Listagem de fornecedor",categoria,fornecedor));
	
	}
	public Result Novo()
	{
		String mensagem = "Novo Fornecedor";
		List<Categoria> categoria = Categoria.find.all();
		
		return ok(views.html.fornecedor.Detalhe.render(formfornecedor,categoria,new Long(0)));
	}
	public Result salvar(Long id){

		Form<Fornecedor> formEnviado = formfornecedor.bindFromRequest();
			
		Fornecedor fornecedor = formEnviado.get();
		Fornecedor fornecedorOld = Fornecedor.find.byId(id);
		if(fornecedorOld != null && fornecedor != null){
			fornecedor.update();
		} else {
			fornecedor.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.FornecedorController.lista());
	}
	
	public Result detalhes(long id)
	{
		Fornecedor fornecedor = Fornecedor.find.byId(id);
		List<Categoria> categoria = Categoria.find.all();
		
		if (fornecedor == null) {
		 return notFound(String.format("Fornecedor %s não existe.", id));
		}
		
		Form<Fornecedor> formPreenchido = formfornecedor.fill(fornecedor);

		return ok(views.html.fornecedor.Detalhe.render(formPreenchido,categoria,fornecedor.id));
	}
	public Result delete(long id){
		
		Fornecedor fornecedor = new Fornecedor();
		
		if (fornecedor == null) {
			 return notFound(String.format("Fornecedor %s não existe.", id));
			}
		else
		{			
			Fornecedor.find.ref(id).delete();
			flash("success", String.format("Fornecedor %s removido", fornecedor));
		}
		
		return redirect(routes.FornecedorController.lista());
		
	}
}
