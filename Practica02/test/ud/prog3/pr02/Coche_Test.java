package ud.prog3.pr02;

import static org.junit.Assert.*;

import org.junit.Test;

public class Coche_Test {

	@Test
	public void testFuerzaAceleracionAdelante() {
	}

	@Test
	public void testFuerzaAceleracionAtras() {
		Coche c = new Coche();
		double[] tablaVel = { -500, -425, -300, -250, -200, -100, 0, 125, 250,
				500, 1100 };
		double[] tablaFuerza = { 0, 0.5, 1, 1, 1, 0.65, 0.3, 0.575, 0.85, 0.85,
				0.85 };
		for (int i = 0; i < tablaVel.length; i++) {
			c.setVelocidad(tablaVel[i]);
			assertEquals("Velocidad " + tablaVel[i], tablaFuerza[i]	* Coche.F_BASE_ATRAS, c.fuerzaAceleracionAtras(), 0.0000001);
		}
		fail("Not yet implemented");
	}

}
