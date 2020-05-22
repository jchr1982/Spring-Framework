package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	/*
	 * El metodo "guardar" responde a peticiones tipo POST y esta mapeado a
	 * la url "/save". En los parametros de este metodo estamos utilizando 
	 * la anotacion "RequestParam" para extraer los valores de nuestro 
	 * formulario HTML para crear una nueva noticia.
	 *  
	 * Nota: Aqui no estamos utilizando DATA BINDING
	 * 
	 * Posteriormente, creamos un objeto de modelo del tipo Noticia, y
	 * por medio de los valores del formulario html poblamos este
	 * objeto. 
	 * 
				
	@PostMapping(value = "/save")
	public String guardar(@RequestParam("titulo") String titulo, @RequestParam("estatus") String estatus, @RequestParam("detalle") String detalle) {
		
 		Noticia noticia = new Noticia();
	  	noticia.setTitulo(titulo);
	  	noticia.setEstatus(estatus);
	  	noticia.setDetalle(detalle);
				
	  	serviceNoticias.guardar(noticia);
		
	  	// Pendiente: Guardar el objeto noticia en la base de datos
						
	  	System.out.println (noticia);
		
	  	return "noticias/formNoticia";
	}
	
	*/
	
	
	/* Ahora cambiamos la implementacion del metodo "guardar" 
	 * utilizando DATA BINDING:
	 * quitamos los argumentos anteriores y es reemplazado por 
	 * "Noticia noticia", en este caso automaticamente Spring MVC 
	 * con esta declaracion va a crear una nueva instancia.
	 * 
	 * Spring MVC va a comparar cada input "name" de nuestro formulario
	 * con los nombres de las propiedades de nuestro modelo y verifica
	 * que tengan los mismos nombres, al detectar que coinciden va a
	 * llamar a los metodos "set" para cada propiedad para asignarle los
	 * valores, evitando tener que llamar a los metodos "set" de forma 
	 * manual.
	 * 
	 * Con ello, estamos usando una sintaxis mas concreta y mas limpia
	 * para extraer los datos de entrada del usuario del formulario y
	 * lo estamos mapeando a un objeto de modelo de nuestra aplicacion.
	 */
	
	@PostMapping(value = "/save")
	public String guardar(Noticia noticia) {
								
		serviceNoticias.guardar(noticia);
		
		// Pendiente: Guardar el objeto noticia en la base de datos
						
		System.out.println (noticia);
		
		return "noticias/formNoticia";
	}
	
	
}
