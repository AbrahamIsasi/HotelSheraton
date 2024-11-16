package org.sam.alurahotel.modelo;

public class Reserva {	
	
	private Long id;
	private String fechaEntrada;
	private String fechaSalida;
	private Long valor;
	private String tipoHabitacion;
	private String numeroHabitacion;
	private String formaPago;
	private String estadoReserva;



			
	public Reserva(String fechaEntrada, String fechaSalida, Long valor, String tipoHabitacion,String numeroHabitacion, String formaPago, String estadoReserva) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.tipoHabitacion = tipoHabitacion;
		this.numeroHabitacion = numeroHabitacion;
		this.formaPago = formaPago;
		this.estadoReserva = estadoReserva;

	}	

	public Reserva(Long id, String fechaEntrada, String fechaSalida, Long valor,String tipoHabitacion,String numeroHabitacion, String formaPago, String estadoReserva) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.tipoHabitacion = tipoHabitacion;
		this.numeroHabitacion = numeroHabitacion;
		this.formaPago = formaPago;
		this.estadoReserva = estadoReserva;


	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public String getNumeroHabitacion(){
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}


	@Override
	public String toString() {
		return "Reserva{" +
				"id=" + id +
				", fechaEntrada='" + fechaEntrada + '\'' +
				", fechaSalida='" + fechaSalida + '\'' +
				", valor=" + valor +
				", tipoHabitacion='" + tipoHabitacion + '\'' +
				", numeroHabitacion='" + numeroHabitacion + '\'' +
				", formaPago='" + formaPago + '\'' +
				", estadoReserva='" + estadoReserva + '\'' +
				'}';
	}
}
