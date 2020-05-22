package net.itinajero.app.service;

import org.springframework.stereotype.Service;

import net.itinajero.app.model.Noticia;

@Service
public class NoticiasServiceImpl implements INoticiasService{
		
	// Constructor que solo imprime un mensaje al crearse una instancia
	public NoticiasServiceImpl() {
		System.out.println("Creando una instancia de la clase NoticiasServiceImpl");
	}

	@Override
	public void guardar(Noticia noticia) {
		
		System.out.println("Guardando el objeto " + noticia + " en la base de datos");
	}

}
