package net.itinajero.app.controller;

// import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
// import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;


@Controller
public class HomeController {
	
	/*
	 * Aqui estamos viendo en acción la Inyección de Dependencias de
	 * Spring.
	 * 
	 * En ningun momento utilizamos el operador "new" por ejemplo:
	 * servicePeliculas = new PeliculasServiceImpl();
	 * 
	 * y sin embargo, Spring creó una instancia de nuestra clase de
	 * servicio de forma automática y la inyectó en nuestro controlador
	 * con la anotación @Autowired en nuestra variable servicePeliculas.
	 * 
	 * Nota: las clases de servicio Spring las crea con el alcance
	 * Singleton, es decir, se va crear una sola instancia
	 */
	
	@Autowired
	private IPeliculasService servicePeliculas;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome(){
		return "home";
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String buscar (@RequestParam("fecha") String fecha, Model model) {
		
		// obtengo las siguientes 5 fechas contado la de hoy
		List<String> listaFechas = Utileria.getNextDays(4);
				
		// lista de objetos del tipo Pelicula
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
								
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechaBusqueda", fecha);
		model.addAttribute("fechas", listaFechas);
			
		System.out.println ("Buscando todas las peliculas en exhibicion para la fecha: " + fecha);
		return "home";
	}
	
	
	// lo tenemos mapeado a la raiz de la aplicacion
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model){
		
		// obtengo las siguientes 5 fechas contado la de hoy
		List<String> listaFechas = Utileria.getNextDays(4);
		// System.out.println (listaFechas);
				
		// lista de objetos del tipo Pelicula
		// List<String> peliculas = new LinkedList<>();
		List<Pelicula> peliculas = servicePeliculas.buscarTodas();
		
		//peliculas.add("Rapido y furioso");
		//peliculas.add("El aro 2");
		//peliculas.add("Aliens");
		
		/* el controlador agrega las peliculas (lista), la fecha
		   de busqueda y las fechas al modelo */
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		model.addAttribute("fechas", listaFechas);

		// se va a redenderizar el archivo home.jsp 
		return "home";
	}
	
	
	//  @RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	//  public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha) {
	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
	
		System.out.println("Buscando Horarios para la Pelicula: " + idPelicula);
		System.out.println("Para la fecha: " + fecha);
		
		// agregamos un atributo a la vista
		model.addAttribute("pelicula", servicePeliculas.buscarPorId(idPelicula));
		
		
		// TODO: Buscar en la base de datos los horarios

		/*
		 * String tituloPelicula = "Rapidos y furiosos"; int duracion = 136; double
		 * precioEntrada = 50;
		 * 
		 * Agregamos los objetos al modelo:  
		 * model.addAttribute("titulo", tituloPelicula); 
		 * model.addAttribute("duracion", duracion);
		 * model.addAttribute("precio", precioEntrada);
		 */

		return "detalle";  // nombre de la vista 
		
	}
		
		
}