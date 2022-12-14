package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modelo.DecoratorComercioInternacional;
import modelo.DecoratorComercioLocal;
import modelo.DecoratorSalud;
import modelo.Empleado;
import modelo.Empleador;
import modelo.FormularioDeBusqueda;
import modelo.HomeOffice;
import modelo.Indistinto;
import modelo.PersonaFisica;
import modelo.PersonaJuridica;
import modelo.Presencial;
import modelo.TicketBuscaEmpleo;
import negocio.Sistema;
import vistas.IVista;
import vistas.VentanaPersistir;
import vistas.VentanaThreads;

public class ControladorThreads implements ActionListener{
	private Sistema sistema = Sistema.getInstancia();
	private IVista vista;
	public ControladorThreads() {
		this.vista = new VentanaThreads();
		this.vista.setActionListener(this);
		this.vista.mostrar();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		System.out.println("Comando: "+ comando);
		if (comando.equalsIgnoreCase("IniciarPrueba")) {
			Sistema sistema = Sistema.getInstancia();
			Object objeto = e.getSource();
			JButton boton = (JButton) objeto;
			boton.setEnabled(false);
			BolsaDeTrabajo bolsa = BolsaDeTrabajo.getInstancia((VentanaThreads) this.vista);
			
			Empleado empleado = new Empleado("Aureliano","aureeliano","abc123","123456",21);
			Empleado empleado1 = new Empleado("Nicolas","nicob2022","cafeconmiel","999999",22);
			Empleado empleado2 = new Empleado("Paola","paovicc_8","ark4nne5","88888",23);
			Empleado empleado3 = new Empleado("Kanano","fabkanano","seikooomoriisbad","12121",23);
			
			FormularioDeBusqueda form = new FormularioDeBusqueda();
			form.setLocacion(new HomeOffice());
			empleado.setTicket(new TicketBuscaEmpleo(form));
			
			FormularioDeBusqueda form1 = new FormularioDeBusqueda();
			form1.setLocacion(new Presencial());
			empleado1.setTicket(new TicketBuscaEmpleo(form1));
			
			FormularioDeBusqueda form2 = new FormularioDeBusqueda();
			form2.setLocacion(new Indistinto());
			empleado2.setTicket(new TicketBuscaEmpleo(form2));
			
			FormularioDeBusqueda form3 = new FormularioDeBusqueda();
			form3.setLocacion(new HomeOffice());
			empleado3.setTicket(new TicketBuscaEmpleo(form3));
			
			Empleador empleador = new Empleador("FRAVEGA","fravearg","musimundomalazo", new DecoratorSalud(new PersonaFisica()));
			Empleador empleador1 = new Empleador("CLARO","clarowrld","clarocomoelagua", new DecoratorComercioLocal(new PersonaJuridica()));
			Empleador empleador2 = new Empleador("MUSIMUNDO","musmusnd","serpartedtuwd", new DecoratorComercioInternacional(new PersonaJuridica()));
			
			
			
			Thread thA = new Thread(empleado);
			Thread thA1 = new Thread(empleado1);
			Thread thA2 = new Thread(empleado2);
			Thread thA3 = new Thread(empleado3);
			
			Thread thB = new Thread(empleador);
			Thread thB1 = new Thread(empleador1);
			Thread thB2 = new Thread(empleador2);
			
			thB.start();
			thB1.start();
			thB2.start();
			
			thA.start();
			thA1.start();
			thA2.start();
			thA3.start();		
		} else if (comando.equalsIgnoreCase("Cerrar")) {
			this.vista.cerrar();
		}
		
	}
}
