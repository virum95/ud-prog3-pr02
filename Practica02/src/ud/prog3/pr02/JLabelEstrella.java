package ud.prog3.pr02;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/** Clase para crear las estrellas que aparecen en pantalla
 * @author Gaizka
 *
 */
public class JLabelEstrella extends JLabel{


	private static final long serialVersionUID = 10L;  // Para serializaci�n
	public static final int TAMANYO_ESTRELLA = 40;  // p�xels (igual ancho que algo)
	public static final int RADIO_ESFERA_ESTRELLA = 17;  // Radio en p�xels del bounding circle de la estrella (para choques)
	private static final boolean DIBUJAR_ESFERA_ESTRELLA = true;  // Dibujado (para depuraci�n) del bounding circle de choque del estrella
	private long horaDeCreaccion;
	
	/** Construye y devuelve el JLabel de la estrella con su gr�fico y tama�o
	 */
	public JLabelEstrella() {
		// Esto se hace para acceder tanto por recurso (jar) como por fichero
		try {
			setIcon( new ImageIcon( JLabelCoche.class.getResource( "img/estrella.png" ).toURI().toURL() ) );
		} catch (Exception e) {
			System.err.println( "Error en carga de recurso: coche.png no encontrado" );
			e.printStackTrace();
		}
		setBounds( 0, 0, TAMANYO_ESTRELLA, TAMANYO_ESTRELLA );
		// Esto ser�a �til cuando hay alg�n problema con el gr�fico: borde de color del JLabel
		// setBorder( BorderFactory.createLineBorder( Color.yellow, 4 ));
		horaDeCreaccion = System.currentTimeMillis();
//		setVisible(true);
	}
	
	public long getHoraDeCreaccion() {
		return horaDeCreaccion;
	}
	
	
	// giro
	private double miGiro = Math.PI/2;
	/** Cambia el giro del JLabel
	 * @param gradosGiro	Grados a los que tiene que "apuntar" la estrella,
	 * 						considerados con el 0 en el eje OX positivo,
	 * 						positivo en sentido antihorario, negativo horario.
	 */
	public void setGiro( double gradosGiro ) {
		// De grados a radianes...
		miGiro += gradosGiro/180*Math.PI;
		// El giro en la pantalla es en sentido horario (inverso):
		miGiro = -miGiro;  // Cambio el sentido del giro
		// Y el gr�fico del coche apunta hacia arriba (en lugar de derecha OX)
		miGiro = miGiro + Math.PI/2; // Sumo 90� para que corresponda al origen OX
	}

	// Redefinici�n del paintComponent para que se escale y se rote el gr�fico
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);   // En este caso no nos sirve el pintado normal de un JLabel
		Image img = ((ImageIcon)getIcon()).getImage();
		Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
		// Escalado m�s fino con estos 3 par�metros:
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		// Prepara rotaci�n (siguientes operaciones se rotar�n)
        g2.rotate( miGiro, TAMANYO_ESTRELLA/2, TAMANYO_ESTRELLA/2 ); // getIcon().getIconWidth()/2, getIcon().getIconHeight()/2 );
        // Dibujado de la imagen
        g2.drawImage( img, 0, 0, TAMANYO_ESTRELLA, TAMANYO_ESTRELLA, null );
        if (DIBUJAR_ESFERA_ESTRELLA) g2.drawOval( TAMANYO_ESTRELLA/2-RADIO_ESFERA_ESTRELLA, TAMANYO_ESTRELLA/2-RADIO_ESFERA_ESTRELLA,
        		RADIO_ESFERA_ESTRELLA*2, RADIO_ESFERA_ESTRELLA*2 );
	}
}
