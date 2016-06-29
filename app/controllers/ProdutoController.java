package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;
import models.Categoria;
import models.Fornecedor;
import models.Produto;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.data.DynamicForm;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ProdutoController extends Controller{
	private final Form<Produto> formProduto = Form.form(Produto.class);
	List<Fornecedor> fornecedor = Fornecedor.find.all();
	
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<Produto> produto = Produto.find.all();
		List<Categoria> categorias = Categoria.find.all();
		
		return ok(views.html.produto.produtoLista.render("Produto",produto));	
	}
	
	public Result Index()
	{
		List<Categoria> categorias = Categoria.find.all();
		List<Produto> produtos = Produto.find.all();
		
		return ok(views.html.index.render("Produto",categorias,produtos));	
	}
	
	public Result Vitrine(Long id)
	{
		List<Categoria> categorias = Categoria.find.all();
		List<Produto> produtos = Produto.find.where().eq("Categoria_ID",id).findList();
		return ok(views.html.produto.vitrine.render("Produto",categorias,produtos));	
	}
	public Result VitrineStr()
	{
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		
		String input = dynamicForm.get("search");
		List<Categoria> categorias = Categoria.find.all();
		List<Produto> produtos = Produto.find
										.where()
										.like("descricao","%"+input+"%")
										.findList();
		return ok(views.html.produto.vitrine.render("Produto",categorias,produtos));	
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
		produto.imagem =  this.sendUpload("imagem");	
		produto.imagemBanner = this.sendUpload("imagemBanner");
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
	 //----------------------------------------------------------------------------------------------------------
    public String sendUpload(String param) {
		 MultipartFormData body = request().body().asMultipartFormData();
		 FilePart picture = body.getFile(param);
		 String newPath = "";
		 String ret = "";
		 if(picture != null) {
			  String fileName = picture.getFilename();
			  String contentType = picture.getContentType();
			  File file = picture.getFile();
			  
			  String appDir = System.getProperty("user.dir");
			  
			  newPath = appDir + File.separator + "public" + File.separator + "Vendor" 
			  + File.separator + "images" + File.separator + fileName;
			  ret = "Vendor" + "\\" + "images" + "\\" + fileName;
			  File newFile = new File(newPath);
      try {
          Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      } catch(IOException e) {
    	  e.printStackTrace();
      }
    }
     return ret;
    }
}
