package ud.prog3.pr02;

/** Clase para definir instancias l�gicas de coches con posici�n, direcci�n y velocidad.
 * @author Andoni Egu�luz
 * Facultad de Ingenier�a - Universidad de Deusto (2014)
 */
public class Coche {
	protected double miVelocidad;  // Velocidad en pixels/segundo
	protected double miDireccionActual;  // Direcci�n en la que estoy mirando en grados (de 0 a 360)
	protected double posX;  // Posici�n en X (horizontal)
	protected double posY;  // Posici�n en Y (vertical)
	protected String piloto;  // Nombre de piloto
	protected final static double MASA = 1; // Peso del coche en kg
	protected final static double F_BASE_ADELANTE = 2000; // Fuerza Base del coche hacia adelante
	protected final static double F_BASE_ATRAS = -1000; // Fuerza Base del coche hacia atras
	protected final static double ROZAMIENTO_SUELO = 15.5; // Coeficiente de rozamiento con el suelo
	protected final static double ROZAMIENTO_AIRE = 0.35; // Coeficiente de rozamiento con el aire

	// Constructores

	public Coche() {
		miVelocidad = 0;
		miDireccionActual = 0;
		posX = 300;
		posY = 300;
	}

	/** Devuelve la velocidad actual del coche en p�xeles por segundo
	 * @return	velocidad
	 */
	public double getVelocidad() {
		return miVelocidad;
	}

	/** Cambia la velocidad actual del coche
	 * @param miVelocidad
	 */
	public void setVelocidad( double miVelocidad ) {
		this.miVelocidad = miVelocidad;
	}

	public double getDireccionActual() {
		return miDireccionActual;
	}

	public void setDireccionActual( double dir ) {
		// if (dir < 0) dir = 360 + dir;
		if (dir > 360) dir = dir - 360;
		miDireccionActual = dir;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosicion( double posX, double posY ) {
		setPosX( posX );
		setPosY( posY );
	}

	public void setPosX( double posX ) {
		this.posX = posX; 
	}

	public void setPosY( double posY ) {
		this.posY = posY; 
	}

	public String getPiloto() {
		return piloto;
	}

	public void setPiloto(String piloto) {
		this.piloto = piloto;
	}


	/** Cambia la velocidad actual del coche
	 * @param aceleracion	Incremento/decremento de la velocidad en pixels/segundo
	 * @param tiempo	Tiempo transcurrido en segundos
	 */
	public void acelera( double aceleracion, double tiempo ) {
		miVelocidad = MundoJuego.calcVelocidadConAceleracion( miVelocidad, aceleracion, tiempo );
	}

	/** Cambia la direcci�n actual del coche
	 * @param giro	Angulo de giro a sumar o restar de la direcci�n actual, en grados (-180 a +180)
	 * 				Considerando positivo giro antihorario, negativo giro horario
	 */
	public void gira( double giro ) {
		setDireccionActual( miDireccionActual + giro );
	}

	/** Cambia la posici�n del coche dependiendo de su velocidad y direcci�n
	 * @param tiempoDeMovimiento	Tiempo transcurrido, en segundos
	 */
	public void mueve( double tiempoDeMovimiento ) {
		setPosX( posX + MundoJuego.calcMovtoX( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
		setPosY( posY + MundoJuego.calcMovtoY( miVelocidad, miDireccionActual, tiempoDeMovimiento ) );
	}

	/** Devuelve la fuerza de aceleraci�n del coche, de acuerdo al motor definido en la pr�ctica 2 
	 * @return Fuerza de aceleraci�n en Newtixels 
	 */ 
	public double fuerzaAceleracionAdelante() { 
		if (miVelocidad<=-150) return F_BASE_ADELANTE; 
		else if (miVelocidad<=0) 
			return F_BASE_ADELANTE*(-miVelocidad/150*0.5+0.5); 
		else if (miVelocidad<=250) 
			return F_BASE_ADELANTE*(miVelocidad/250*0.5+0.5); 
		else if (miVelocidad<=750) 
			return F_BASE_ADELANTE; 
		else return F_BASE_ADELANTE*(-(miVelocidad-1000)/250); 
	} 


	public double fuerzaAceleracionAtras(){
		if (miVelocidad<=-500) return 0;
		else if (miVelocidad<=-350)
			return F_BASE_ATRAS*((miVelocidad+500)/150); 
		else if (miVelocidad<=-200)  
			return F_BASE_ATRAS; 
		else if (miVelocidad<=0) 
			return F_BASE_ATRAS*((0.7*(miVelocidad+200)/200)+1); 
		else if (miVelocidad<=250) 
			return F_BASE_ATRAS*(0.55*(miVelocidad-250)/-250+3); 
		else if (miVelocidad<=1000) 
			return F_BASE_ATRAS; 
		else return F_BASE_ATRAS*0.85; 		
	}

	@Override
	public String toString() {
		return piloto + " (" + posX + "," + posY + ") - " +
				"Velocidad: " + miVelocidad + " ## Direcci�n: " + miDireccionActual; 
	}
}
