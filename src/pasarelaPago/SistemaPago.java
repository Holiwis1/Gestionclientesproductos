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
		    int[] denominations = {50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

		    System.out.println("Con cuanto dinero vas a pagar?");
		    Scanner sc = new Scanner(System.in);
		    double pago = validarDouble();
		    int pagoC = (int) (pago * 100);
		    int cambio = pagoC - cuenta;

		    if (pagoC >= cuenta) {
		        System.out.println("El cambio es:");
		        for (int i = 0; i < denominations.length; i++) {
		            int numDenomination = cambio / denominations[i];
		            if (numDenomination > 0) {
		                System.out.println(numDenomination + " " + (denominations[i] >= 100 ? "billete/s" : "moneda/s") + " de " + denominations[i]/100.0);
		            }
		            cambio %= denominations[i];
		        }
		    } else {
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


