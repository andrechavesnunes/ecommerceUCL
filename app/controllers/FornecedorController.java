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
		
		return ok(views.html.fornecedor.Lista.render("Listagem de fornecedor",fornecedor));	
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result Novo()
	{
		String mensagem = "Novo Fornecedor";
		
		return ok(views.html.fornecedor.Detalhe.render(formfornecedor,new Long(0)));
	}
	
	//----------------------------------------------------------------------------------------------------------
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
	
	//----------------------------------------------------------------------------------------------------------
	public Result detalhes(long id)
	{
		Fornecedor fornecedor = Fornecedor.find.byId(id);
		
		if (fornecedor == null) {
		 return notFound(String.format("Fornecedor %s não existe.", id));
		}
		
		Form<Fornecedor> formPreenchido = formfornecedor.fill(fornecedor);

		return ok(views.html.fornecedor.Detalhe.render(formPreenchido,fornecedor.id));
	}
	
	//----------------------------------------------------------------------------------------------------------
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
