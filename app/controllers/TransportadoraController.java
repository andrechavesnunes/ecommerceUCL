package controllers;

import java.util.List;

import models.Categoria;
import models.Transportadora;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class TransportadoraController extends Controller{
	
	private final Form<Transportadora> formTransportadora = Form.form(Transportadora.class);
	
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<Transportadora> transportadora = Transportadora.find.all();
		
		return ok(views.html.transportadora.transportadoraLista.render("Listagem de Transportadora",transportadora));
	
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result novaTransportadora()
	{
		String mensagem = "Nova Transportadora";
		
		return ok(views.html.transportadora.transportadoraDetalhes.render(formTransportadora,new Long(0),mensagem));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result detalhes(long id)
	{
		Transportadora transportadora = Transportadora.find.byId(id);
		
		String mensagem = "Nova Transportadora";
		
		if (transportadora == null) {
		 return notFound(String.format("Transportadora %s não existe.", id));
		}
		System.out.println(transportadora);
		Form<Transportadora> formPreenchido = formTransportadora.fill(transportadora);

		return ok(views.html.transportadora.transportadoraDetalhes.render(formPreenchido,transportadora.id,mensagem));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result salvar(Long id){

		Form<Transportadora> formEnviado = formTransportadora.bindFromRequest();
			
		Transportadora transportadora = formEnviado.get();
		Transportadora transportadoraOld = Transportadora.find.byId(id);
		if(transportadoraOld != null){
			transportadora.update();
		} else {
			transportadora.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.TransportadoraController.lista());
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result remover(long id){
		
		Transportadora transportadora = new Transportadora();
		
		if (transportadora == null) {
			 return notFound(String.format("Transportadora %s não existe.", id));
			}
		else
		{			
			transportadora.find.ref(id).delete();
			flash("success", String.format("Transportadora %s removido", transportadora));
		}
		
		return redirect(routes.TransportadoraController.lista());
		
	}

}
