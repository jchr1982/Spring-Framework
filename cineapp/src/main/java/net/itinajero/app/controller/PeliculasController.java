package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
// import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;

/* Con RequestMapping a nivel de la clase especifico el prefijo
 * de la url para todos los metodos de este controlador, en este
 * caso el prefijo sera "/peliculas". 
*/

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Pelicula> lista = servicePeliculas.buscarTodas();
		model.addAttribute("peliculas", lista);
		
		/*
		 * De esta forma en nuestra vista "listPeliculas.jsp" vamos 
		 * a tener disponible un atributo que va a tener una instancia 
		 * de nuestra lista de peliculas.
		 */
		return "peliculas/listPeliculas";
	}
	
	
	@GetMapping("/create")
	public String crear() {
		
		return "peliculas/formPelicula";
	}

	
	@PostMapping("/save")
	public String guardar(Pelicula pelicula, BindingResult result, RedirectAttributes attributes) {
		
		/*
		 * Verificamos si existieron errores durante la etapa
		 * de DataBinding, si los hay regresa al formulario
		 */ 
		 if (result.hasErrors()) {
			 System.out.println("Existieron errores");
		 	 return "peliculas/formPelicula"; 
		 }
		 
		/*
		 * for (ObjectError error : result.getAllErrors()) {
		 * 
		 * System.out.println(error.getDefaultMessage()); }
		 */
				
		System.out.println("Recibiendo objeto pelicula: " + pelicula);
				
		System.out.println("Elementos en la lista antes de la insercion: " + servicePeliculas.buscarTodas().size());
		
		servicePeliculas.insertar(pelicula);
		
		System.out.println("Elementos en la lista despues de la insercion: " + servicePeliculas.buscarTodas().size());
		
		// model.addAttribute("mensaje", "El registro fue guardado");
		
		// return "peliculas/formPelicula";
		
		/*
		 * los atributos flash son almacenados temporalmente antes de
		 * hacer el redirect (en la sesion). Despues del redirect son
		 * eliminados de la sesion. Esto permite pasar una variable
		 * a la pagina que estamo redireccionado.
		 */
		
		attributes.addFlashAttribute("mensaje", "El registro fue guardado");
				
		/* la redireccion a una "url" actua como una peticion del tipo get 
		 * y no del tipo post, la utilizados cuando hacemos una redireccion
		 * utilizando el patron Post/Redirect/Get.
		 * 
		 * En el metodo "guardar", estamos realizando indirectamente una 
		 * peticion adicional "un redirect". Cuando el usuario guarda el
		 * formulario se ejecuta una peticion post y enseguida una peticion
		 * get
		 */
		
		return "redirect:/peliculas/index";
	}
	
	
	/* 	Aqui tenemos un metodo donde estamos personalizando el 
	 	DataBinding para todas las propiedades que son del tipo
	 	Date.
	 	Aqui configuramos el formato adecuado para la fecha para que
	 	no de error al tratar de convertir una fecha */
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
