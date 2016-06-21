package controllers;

import java.util.List;

import models.Categoria;
import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UsuarioController extends Controller {
	
	private final Form<Usuario> formUsuario = Form.form(Usuario.class);
	
	//----------------------------------------------------------------------------------------------------------
	public Result lista()
	{
		List<Usuario> usuario = Usuario.find.all();
		
		return ok(views.html.usuario.usuarioLista.render("Usuários",usuario));
	
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

}
