package controllers;

import java.util.List;

import models.Categoria;
import models.Usuario;
import models.Login;
import models.Produto;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.DynamicForm;
import play.data.Form;
import java.io.*;

public class UsuarioController extends Controller {
	
	private final Form<Usuario> formUsuario = Form.form(Usuario.class);
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<Usuario> usuario = Usuario.find.all();
		List<Categoria> categorias = Categoria.find.all();
		List<Produto> produtos = Produto.find.all();
		if (session("userId") != null)
		{
			return ok(views.html.usuario.usuarioLista.render("Usuários",usuario));
		}	
		else
		{
			flash("error", String.format("Usuario não esta Logado!!!"));
			return ok(views.html.index.render("Usuários",categorias,produtos));
		}
	
	}
	//----------------------------------------------------------------------------------------------------------
	public Result Logout(){
		List<Categoria> categorias = Categoria.find.all();
		List<Produto> produtos = Produto.find.all();
		session("userId","0");
		session("userName","0");
		session("userPerfil","0");
		return ok(views.html.index.render("Produto",categorias,produtos));	
	}
	//----------------------------------------------------------------------------------------------------------
	public Result Autenticar(){
		DynamicForm dynamicForm = Form.form().bindFromRequest();
		Usuario user = Usuario.find.where()
								   .eq("email",dynamicForm.get("username"))
					               .eq("senha",dynamicForm.get("password"))
					               .findUnique();
		if (user!= null)
		{
			session("userId",String.valueOf(user.id));
			session("userName",getPrimeiroNome(user.nome));
			session("userPerfil",String.valueOf(user.administrativo));
			flash("success", String.format("Usuario logado!!!"));	
		}
		else{
			flash("error", String.format("Falha no login!!!"));
		}
		return redirect(routes.ProdutoController.Index());
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result novoUsuario()
	{
		String mensagem = "Usuário";
		
		return ok(views.html.usuario.usuarioDetalhes.render(formUsuario,new Long(0),mensagem));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result detalhes(long id)
	{
		Usuario usuario = Usuario.find.byId(id);
		
		String mensagem = "Usuário";
		
		if (usuario == null) {
		 return notFound(String.format("Usuário %s não existe.", id));
		}
		System.out.println(usuario);
		Form<Usuario> formPreenchido = formUsuario.fill(usuario);

		return ok(views.html.usuario.usuarioDetalhes.render(formPreenchido,usuario.id,mensagem));
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result salvar(Long id){
		
		Form<Usuario> formEnviado = formUsuario.bindFromRequest();
			
		Usuario usuario = formEnviado.get();
		Usuario usuarioOld = Usuario.find.byId(id);
		if(usuarioOld != null){
			usuario.update();
		} else {
			usuario.save();
		}
		
		flash("success", String.format("Salvo com sucesso!!!"));
		return redirect(routes.UsuarioController.lista());
	}
	
	//----------------------------------------------------------------------------------------------------------
	public Result remover(long id){
		
		Usuario usuario= new Usuario();
		
		if (usuario == null) {
			 return notFound(String.format("Usuário %s não existe.", id));
			}
		else
		{			
			usuario.find.ref(id).delete();
			flash("success", String.format("Usuário %s removido", usuario));
		}
		
		return redirect(routes.UsuarioController.lista());
		
	}
	private String getPrimeiroNome(String nome){
		String primeiroNome= "";
		nome = nome.trim();
		for (int i=0;i<nome.length();i++){
			if ((i==0) && (nome.substring(i, i+1).equalsIgnoreCase(" "))){
				break;
			}
			else if (!nome.substring(i, i+1).equalsIgnoreCase(" ")){
				primeiroNome += nome.substring(i, i+1);
			}
			else
				break;
		}
		return primeiroNome;
	}

}
