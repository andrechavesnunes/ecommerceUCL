package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;
import models.Categoria;
import models.Fornecedor;
import models.Produto;

public class ProdutoController extends Controller{
	private final Form<Produto> formProduto = Form.form(Produto.class);
	List<Fornecedor> fornecedor = Fornecedor.find.all();
	
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<Produto> produto = Produto.find.all();
		List<Categoria> categorias = Categoria.find.all();
		
		return ok(views.html.produto.produtoLista.render("Produto",produto,categorias));	
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result Novo()
	{
		String mensagem = "Novo Produto";
		List<Categoria> categoria = Categoria.find.all();
		List<Fornecedor> fornecedor = Fornecedor.find.all();
		
		return ok(views.html.produto.produtoDetalhes.render(formProduto,new Long(0),"Produtos",categoria,fornecedor));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result salvar(Long id){

		Form<Produto> formEnviado = formProduto.bindFromRequest();
			
		Produto produto = formEnviado.get();
		Produto produtoOld = Produto.find.byId(id);
		if(produtoOld != null && produto != null){
			produto.update();
		} else {
			produto.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.ProdutoController.lista());
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result detalhes(long id)
	{
		Produto produto = Produto.find.byId(id);
		
		if (produto == null) {
		 return notFound(String.format("Produto %s não existe.", id));
		}
		
		Form<Produto> formPreenchido = formProduto.fill(produto);
		List<Categoria> categoria = Categoria.find.all();
		List<Fornecedor> fornecedor = Fornecedor.find.all();

		return ok(views.html.produto.produtoDetalhes.render(formPreenchido,produto.id,"Produto",categoria,fornecedor));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result delete(long id){
		
		Produto produto = new Produto();
		
		if (produto == null) {
			 return notFound(String.format("Produto %s não existe.", id));
			}
		else
		{			
			Produto.find.ref(id).delete();
			flash("success", String.format("Produto %s removido", produto));
		}
		
		return redirect(routes.ProdutoController.lista());
		
	}
}
