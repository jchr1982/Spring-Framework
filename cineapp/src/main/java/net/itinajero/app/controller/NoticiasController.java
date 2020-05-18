package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	/* 	Aqui tenemos una instancia de la clase de servicio inyectada 
	 * 	en el controlador para manipular nuestros objetos de modelo 
	 * 	del tipo noticia	*/
	@Autowired
	private INoticiasService serviceNoticias;
	
	
	@GetMapping(value = "/create")
	public String crear() {
		
		return "noticias/formNoticia";
	}
	
	
	@PostMapping(value = "/save")
	public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus, @RequestParam("detalle") String detalle) {
		
		Noticia noticia = new Noticia();
		noticia.setTitulo(titulo);
		noticia.setEstatus(estatus);
		noticia.setDetalle(detalle);
				
		serviceNoticias.guardar(noticia);
		
		// Pendiente: Guardar el objeto noticia en la base de datos
		
		// System.out.println ("Titulo: " + titulo);
		// System.out.println ("Estatus: " + estatus);
		// System.out.println ("Detalle: " + detalle);
		
		System.out.println (noticia);
		
		return "noticias/formNoticia";
	}
	
	
}
