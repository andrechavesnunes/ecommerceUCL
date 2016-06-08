package controllers;

import java.util.List;

import models.Categoria;
import models.FormaPagamento;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class FormaPagamentoController extends Controller {
	
	private final Form<FormaPagamento> formFormaPagamento = Form.form(FormaPagamento.class);
	
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<FormaPagamento> formaPagamento = FormaPagamento.find.all();
		List<Categoria> categoria = Categoria.find.all();
		
		return ok(views.html.formaPagamento.formaPagamentoLista.render("Listagem de Formas de Pagamento",categoria,formaPagamento));
	
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result novaFormaPagamento()
	{
		String mensagem = "Nova Forma de Pagamento";
		List<Categoria> categoria = Categoria.find.all();
		
		return ok(views.html.formaPagamento.formaPagamentoDetalhes.render(formFormaPagamento,new Long(0),mensagem,categoria));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result detalhes(long id)
	{
		FormaPagamento formaPagamento = FormaPagamento.find.byId(id);
		List<Categoria> categoria = Categoria.find.all();
		
		String mensagem = "Nova Forma de Pagamento";
		
		if (formaPagamento == null) {
		 return notFound(String.format("Forma de Pagamento %s não existe.", id));
		}
		System.out.println(formaPagamento);
		Form<FormaPagamento> formPreenchido = formFormaPagamento.fill(formaPagamento);

		return ok(views.html.formaPagamento.formaPagamentoDetalhes.render(formPreenchido,formaPagamento.id,mensagem,categoria));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result salvar(Long id){

		Form<FormaPagamento> formEnviado = formFormaPagamento.bindFromRequest();
			
		FormaPagamento formaPagamento = formEnviado.get();
		FormaPagamento formaPagamentoOld = FormaPagamento.find.byId(id);
		if(formaPagamentoOld != null){
			formaPagamento.update();
		} else {
			formaPagamento.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.FormaPagamentoController.lista());
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result remover(long id){
		
		FormaPagamento formaPagamento = new FormaPagamento();
		
		if (formaPagamento == null) {
			 return notFound(String.format("Forma de Pagamento %s não existe.", id));
			}
		else
		{			
			formaPagamento.find.ref(id).delete();
			flash("success", String.format("Forma de Pagamento %s removido", formaPagamento));
		}
		
		return redirect(routes.FormaPagamentoController.lista());
		
	}

}