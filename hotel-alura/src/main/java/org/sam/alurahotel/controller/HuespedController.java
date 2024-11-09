package org.sam.alurahotel.controller;

import java.util.List;

import org.sam.alurahotel.dao.HuespedDao;
import org.sam.alurahotel.factory.ConnectionFactory;
import org.sam.alurahotel.modelo.Huesped;
import org.sam.alurahotel.modelo.Reserva;

public class HuespedController {

	private HuespedDao huespedDao;
	
	public HuespedController() {
		this.huespedDao = new HuespedDao(new ConnectionFactory().recupetaConexion());
	}
	
	public void guardar(Huesped huesped) {
		huespedDao.guardar(huesped);
	}



	public int modificar(String nombre, String apellido, String fecha_nacimiento, String nacionalidad, String telefono, Long id){
		return huespedDao.modificar(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id);	
	}
	
	public int eliminar(Integer id) {		
		return huespedDao.eliminar(id);			
	}
	
	public int eliminarPorIDReserva(Integer id_reserva) {		
		return huespedDao.eliminarPorIDReserva(id_reserva);			
	}

	public List<Huesped> listar(String campo) {
		return huespedDao.listarHuespedes(campo);
	}

	public List<Huesped> listarTodosHuespedes() {
		return huespedDao.obtenerTodosLosHuespedes(); // Método en HuespedDAO que retorna la lista de huéspedes
	}


}
