package controllers;

import java.util.List;
import java.util.ArrayList;
import play.mvc.Result;
import models.Categoria;
import models.Carrinho;
import models.FormaPagamento;
import models.Transportadora;

import models.ItensCarrinho;
import models.Produto;
import play.mvc.Controller;
import play.api.libs.json.Reads;
import org.json.*;
import org.json.JSONException;
import play.data.Form;
import play.data.DynamicForm;



public class CarrinhoController extends Controller{
	private final Form<Carrinho> formCarrinho = Form.form(Carrinho.class);
	public  Result listaCarrinho() {
    	String carrinho = session("carrinho");
    	List<ItensCarrinho> listaItens = new ArrayList<ItensCarrinho>();
    	try
    	{
			if(carrinho != null) {
		    	JSONArray jsa = new JSONArray(carrinho);
	    		for(int i = 0; i < jsa.length(); i++) {
		    		JSONObject jsoItem = jsa.getJSONObject(i);
		    		ItensCarrinho item = new ItensCarrinho();
		    		item.Produto =  Produto.find.byId(jsoItem.getLong("idProduto"));
		    		item.Qtd =  jsoItem.getLong("quantidade");
		    		item.Desconto = 0;
		    		item.Valor = (item.Produto.preco * item.Qtd); 
		    		listaItens.add(item);
		    	}
		   }
		  return ok(views.html.carrinho.carrinhoCompras.render(listaItens));
	 	} catch (JSONException e) {
	 		return ok("404");
	    }  	
    }
    public Result addItemAoCarrinho(Long idProduto) {
    	String carrinho = session("carrinho");
    	try
    	{
		    	if(carrinho == null) {
		    		carrinho = "[]"; //Recebe string JSON de array vazio
		    	}
		    	boolean aux = false;
		    	JSONArray jsa = new JSONArray(carrinho);
		    	loop : for(int i = 0; i < jsa.length(); i++) {
		    		if (jsa.getJSONObject(i).getLong("idProduto") == idProduto){
		    			JSONObject obj =  jsa.getJSONObject(i);
		    			Long qty = obj.getLong("quantidade") + 1;	
		    			obj.put("quantidade",qty);
		    			session("carrinho",jsa.toString());
		    			return listaCarrinho();
		    		}
		    	}
		    	if (aux != true){
		    		JSONObject jsoNovoProduto = new JSONObject();
		    		jsoNovoProduto.put("idProduto", idProduto);
		        	jsoNovoProduto.put("quantidade", 1);
		    		jsa.put(jsoNovoProduto);
		    	}
		    	session("carrinho", jsa.toString());
		    	return listaCarrinho();
    	} catch (JSONException e) {
    		return ok("404");
    	}
    }
    public Result remItemDoCarrinho(Long idProduto) {
    	String carrinho = session("carrinho");
    	try
    	{
	    	JSONArray jsa = new JSONArray(carrinho);
		    	
		    for(int i = 0; i < jsa.length(); i++) {
		    	JSONObject jsoItem = jsa.getJSONObject(i);
		    		
		    	if(jsoItem.getLong("idProduto") == idProduto){
		    		jsa.remove(i);
		    	}
		    }
		    session("carrinho", jsa.toString());
		    return listaCarrinho();
    	} catch (JSONException e) {
    		return ok("404");
    	}
    }
    public Result alterQtdCarrinho(Long id){
    	String carrinho = session("carrinho");
    	DynamicForm dynamicForm = Form.form().bindFromRequest();
    	String input = dynamicForm.get("item");
    	try
    	{
	    	JSONArray jsa = new JSONArray(carrinho);
	    	loop : for(int i = 0; i < jsa.length(); i++) {
	    		if (jsa.getJSONObject(i).getLong("idProduto") == id){
	    			JSONObject obj =  jsa.getJSONObject(i);
	    			obj.put("quantidade",Long.parseLong(input.replace(".0", "")));
	    			session("carrinho",jsa.toString());
	    			return listaCarrinho();
	    		}
	    	}
    	} catch (JSONException e) {
    		return ok("404");
    	}
    	return ok(input);
    }
    public Result checkin(){
    	String carrinho = session("carrinho");
    	List<ItensCarrinho> listaItens = new ArrayList<ItensCarrinho>();
    	try
    	{
			if(carrinho != null) {
		    	JSONArray jsa = new JSONArray(carrinho);
	    		for(int i = 0; i < jsa.length(); i++) {
		    		JSONObject jsoItem = jsa.getJSONObject(i);
		    		ItensCarrinho item = new ItensCarrinho();
		    		item.Produto =  Produto.find.byId(jsoItem.getLong("idProduto"));
		    		item.Qtd =  jsoItem.getLong("quantidade");
		    		item.Desconto = 0;
		    		item.Valor = (item.Produto.preco * item.Qtd); 
		    		listaItens.add(item);
	    		}
	    	} else {
	    		if (session("carrinho") == null){
	    			return 
	    		}
	    		flash("error", String.format("Carrinho Vazio!!"));
		    }
	 	} catch (JSONException e) {
	 		return ok("404");
	    }  	
    	
    	List<FormaPagamento> formas = FormaPagamento.find.all();
    	List<Transportadora> trans = Transportadora.find.all();
    	return ok(views.html.carrinho.checkin.render(formCarrinho,"Check - in",formas,trans,listaItens));
    }
    public Result salvar(){
    	String carrinho = session("carrinho");
    	Carrinho car = new Carrinho();
    	try
    	{
		    	if(carrinho != null) {
		    		//car.
		    	}
		    	boolean aux = false;
		    	JSONArray jsa = new JSONArray(carrinho);
		    	loop : for(int i = 0; i < jsa.length(); i++) {
		    		JSONObject obj =  jsa.getJSONObject(i);
		    		Long qty = obj.getLong("quantidade") + 1;	
		    		obj.put("quantidade",qty);
		    		session("carrinho",jsa.toString());
		    	}
		    	session("carrinho", jsa.toString());
    	} catch (JSONException e) {
    		return ok(session("carrinho"));
    	}
    	return ok(session("carrinho"));
    }
}
