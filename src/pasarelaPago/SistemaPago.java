package pasarelaPago;
import java.util.Scanner;
public class SistemaPago {
	
	/**
	 * 
	 * @author joaquin
	 *
	 */

	
		/**
		 * variables
		 */
		double importe;
		/**
		 * Constructor
		 */
		public SistemaPago(double importe){
			this.importe = importe;
		}
		/**
		 * Metodo de sistema de pago y eleccion de efectivo,cuenta corriente y tarjeta
		 */
		public void elegirPago() {
			int metodoPago;
			metodoPago = 0;
			System.out.println("Como va a pagar?");
			System.out.println("0. Efectivo");
			System.out.println("1. Cuenta corriente");
			System.out.println("2. Tarjeta");

			Scanner sc = new Scanner(System.in);
			metodoPago = sc.nextInt();

			switch (metodoPago) {
			case 0:
				 efectivo();
				break;
			case 1:
				cuentaCorriente();
				break;
			case 2: 
				tarjeta();
			}
			
		}
		/**
		 * metodo para pagar con efectivo
		 */
		public void efectivo() {
			int cuenta = (int) (this.importe * 100);
			double pago = 0;
			int billete500 = 0;
			int billete200 = 0;
			int billete100 = 0;
			int billete50 = 0;
			int billete20 = 0;
			int billete10 = 0;
			int billete5 = 0;
			int moneda2 = 0;
			int moneda1 = 0;
			double centimo50 = 0;
			double centimo20 = 0;
			double centimo10 = 0;
			double centimo5 = 0;
			double centimo_2 = 0;
			double centimo_1 = 0;

			System.out.println("Con cuanto dinero vas a pagar?");
			Scanner sc = new Scanner(System.in);
			
			pago=validarDouble();
			int pagoC = (int) (pago * 100);

			int cambio = pagoC - cuenta;

			if (pagoC >= cuenta) {

				System.out.println("El cambio es:");
				if (cambio >= 50000) {
					System.out.println(cambio / 50000 + " billete/s 500");
					cambio %= 50000;
				}
				if (cambio >= 20000) {
					System.out.println(cambio / 20000 + " billete/s 200");
					cambio %= 20000;
				}
				if (cambio >= 10000) {
					System.out.println(cambio / 50000 + " billete/s de 100");
					cambio %= 10000;
				}
				if (cambio >= 5000) {
					System.out.println(cambio / 5000 + " billete/s de 50");
					cambio %= 5000;
				}
				if (cambio >= 2000) {
					System.out.println(cambio / 2000 + " billete/s de 20");
					cambio %= 2000;
				}
				if (cambio >= 1000) {
					System.out.println(cambio / 1000 + " billete/s de 10");
					cambio %= 1000;
				}
				if (cambio >= 500) {
					System.out.println(cambio / 500 + " billete/s de 5");
					cambio %= 500;
				}
				if (cambio >= 200) {
					System.out.println(cambio / 200 + " moneda/s de 2");
					cambio %= 200;
				}
				if (cambio >= 100) {
					System.out.println(cambio / 100 + " moneda/s de 1");
					cambio %= 100;
				}
				if (cambio >= 50) {
					System.out.println(cambio / 50 + " moneda/s de 50 centimos");
					cambio %= 50;
				}
				if (cambio >= 20) {
					System.out.println(cambio / 20 + " moneda/s de 20 centimos");
					cambio %= 20;
				}
				if (cambio >= 10) {
					System.out.println(cambio / 10 + " moneda/s de 10 centimos");
					cambio %= 10;
				}
				if (cambio >= 5) {
					System.out.println(cambio / 5 + " moneda/s de 5 centimos");
					cambio %= 5;
				}
				if (cambio >= 2) {
					System.out.println(cambio / 2 + " moneda/s de 2 centimos");
					cambio %= 2;
				}
				if (cambio >= 1) {
					System.out.println(cambio / 1 + " moneda/s de 1 centimos");
					cambio %= 1;
				}
			}			
			else {
				System.out.println("Pago insuficinte");
			}		
		}

		/**
		 * metodo para realizar el cambio por tarjeta
		 */
		public void cuentaCorriente() {
			String cuenta;
			Scanner sc = new Scanner(System.in);

			System.out.println("Introduzca su cuenta corriente");
			System.out.println("Formato ejemplo: ES21 1465 0100 72 2030876293");
			System.out.print("Insertar :");
			cuenta = sc.nextLine();

			cuenta = cuenta.replaceAll(" ", "");
			if (cuenta.matches(
					 "^ES[0-9]{2}[0-9]{20}$"
					) == true) {
				System.out.println("Formato correcto");
			}

			if (cuenta.matches(
					 "^ES[0-9]{2}[0-9]{20}$"
					) == false) {
				do {
					System.out.println("Formato erroneo , introduzca otra vez su cuenta :");
					cuenta = sc.nextLine();
				} while (cuenta.matches(
						 "^ES[0-9]{2}[0-9]{20}$"
						)== false);
				System.out.println("Formato correcto");
			}

		
		}
		
		public Double validarDouble() {
			
			boolean numDouble = false;
			double num = 0;
			Scanner sc = new Scanner(System.in);
			do {
				try {
					String cadena = sc.nextLine();
					num = Double.parseDouble(cadena);
					numDouble = true;
				} catch (NumberFormatException e) {
					System.err.println("No admito numeros decimales :" + e.getMessage());
				}
			} while (!numDouble);
			return num;
		}
		/*
		 * metodo para pagar con tarjeta
		 */
		public void tarjeta() {
			String tarjeta;
			Scanner sc = new Scanner(System.in);

			System.out.println("Introduzca su tarjeta");
			System.out.println("Formato ejemplo: 1212 3434 5656 7878");
			System.out.print("Insertar :");
			tarjeta = sc.nextLine();

			tarjeta = tarjeta.replaceAll(" ", "");
			if (tarjeta.matches(
					 "^[0-9]{16}$"
					) == true) {
				System.out.println("Formato correcto");
			}

			if (tarjeta.matches(
					 "^[0-9]{16}$"
					) == false) {
				do {
					System.out.println("Formato erroneo , introduzca otra vez su tarjeta :");
					tarjeta = sc.nextLine();
				} while (tarjeta.matches(
						 "^[0-9]{16}$"
						)== false);
				System.out.println("Formato correcto");
			}

		
		} 


}


