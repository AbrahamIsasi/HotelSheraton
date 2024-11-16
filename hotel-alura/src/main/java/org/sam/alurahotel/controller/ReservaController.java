package org.sam.alurahotel.controller;

import java.util.List;
import org.sam.alurahotel.dao.ReservaDao;
import org.sam.alurahotel.factory.ConnectionFactory;
import org.sam.alurahotel.modelo.Reserva;

public class ReservaController {

	private ReservaDao reservaDao;

	public ReservaController() {
		this.reservaDao = new ReservaDao(new ConnectionFactory().recupetaConexion());
	}

	public void guardar(Reserva reserva) {
		reservaDao.guardar(reserva);
	}


	public int modificar(String fecha_entrada, String fecha_salida, Double valor, String tipo_habitacion, String forma_pago, String num_habitacion ,String id_pago, Long id) {
		return reservaDao.modificar(fecha_entrada, fecha_salida, valor, tipo_habitacion, forma_pago,num_habitacion,id_pago, id);
	}

	public int eliminar(Integer id) {
		return reservaDao.eliminar(id);
	}

	public List<Reserva> listar(String campo) {
		return reservaDao.listarReservas(campo);
	}

	public List<Reserva> listarTodasReservas() {
		return reservaDao.listarTodasReservas();
	}


}
