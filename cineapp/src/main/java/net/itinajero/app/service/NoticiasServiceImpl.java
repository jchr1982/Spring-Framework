package net.itinajero.app.service;

import net.itinajero.app.model.Noticia;

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
