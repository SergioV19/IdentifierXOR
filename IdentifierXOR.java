package models;

/**
 * Clase encargada del funcionamiento de la red neuronal para aprender la
 * funcion xor
 * 
 *
 */
public class IdentifierXOR {

	public static double APRENDIZAJE = 0.25;
	public static int[][] dataSetNeurona1 = { { 0, 0, 0 }, { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
	public static int[][] dataSetNeurona2 = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 1, 1, 1 } };
	public double weightNeuronOneX1, weightNeuronOneX2, weightNeuronTwoX1, weightNeuronTwoX2, baiasOne, baiasTwo;

	/**
	 * Constructor de la clase aqui se inicializan los valores de los pesos para
	 * empezar a iterarlos
	 */
	public IdentifierXOR() {
		weightNeuronOneX1 = 0.20;
		weightNeuronOneX2 = 1;
		weightNeuronTwoX1 = 0.20;
		weightNeuronTwoX2 = -0.20;
		baiasOne = 0.05;
		baiasTwo = 0.05;
	}

	/**
	 * Funcion de activacion
	 * 
	 * @param value valor a evaluar con hardlimit
	 * @return 0 o 1 segun corresponda
	 */
	private int hardLimit(double value) {
		if (value >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Metodo encargado de realizar el aprendizaje de la neurona llama a la funcion
	 * evaluar pesos
	 * 
	 */

	/**
	 * Se encarga de evaluar el los data set de cada neurona, con ello evalua cuando
	 * hay un error y calcula de nuevo los valores de los pesos y el baias
	 * dependiendo del factor de aprendizaje
	 * 
	 * @param matriz  data set de la neurona 1
	 * @param matriz2 data set de la neurona 2
	 */
	private void evaluarPesos(int[][] matriz, int[][] matriz2) {

		for (int i = 0; i < matriz.length; i++) {

			double y1 = matriz[i][0] * weightNeuronOneX1 + matriz[i][1] * weightNeuronOneX2 - baiasOne;
			double y2 = matriz2[i][0] * weightNeuronTwoX1 + matriz2[i][1] * weightNeuronTwoX2 - baiasTwo;

			int hardLimit1 = hardLimit(y1);
			int hardLimit2 = hardLimit(y2);

			int error1 = matriz[i][2] - hardLimit1;
			int error2 = matriz2[i][2] - hardLimit2;

			double delta1 = APRENDIZAJE * error1 * matriz[i][0];
			double delta2 = APRENDIZAJE * error1 * matriz[i][1];

			double delta3 = APRENDIZAJE * error2 * matriz2[i][0];
			double delta4 = APRENDIZAJE * error2 * matriz2[i][1];

			weightNeuronOneX1 = weightNeuronOneX1 + delta1;
			weightNeuronOneX2 = weightNeuronOneX2 + delta2;
			baiasOne = baiasOne - (APRENDIZAJE * error1);

			weightNeuronTwoX1 = weightNeuronTwoX1 + delta3;
			weightNeuronTwoX2 = weightNeuronTwoX2 + delta4;
			baiasTwo = baiasTwo - (APRENDIZAJE * error2);
		}
	}

	/**
	 * Metodo encargado de mostrar por consola los valores de las ecuaciones de las
	 * neuronas
	 */
	public void mostrarEcuaciones() {
		System.out.println(
				"Ecuacion neurona 1: " + weightNeuronOneX1 + " * X1 + " + weightNeuronOneX2 + " * X2 - " + baiasOne);
		System.out.println(
				"Ecuacion neurona 2: " + weightNeuronTwoX1 + " * X1 + " + weightNeuronTwoX2 + " * X2 - " + baiasTwo);
	}

	public void aprendizaje() {

		for (int i = 0; i < 100; i++) {
			evaluarPesos(dataSetNeurona1, dataSetNeurona2);
		}
		imprimirSalida();
	}

	private void imprimirSalida() {
		for (int i = 0; i < dataSetNeurona1.length; i++) {
			int hardLimitN1 = hardLimit(
					dataSetNeurona1[i][0] * weightNeuronOneX1 + dataSetNeurona1[i][1] * weightNeuronOneX2 - baiasOne);
			int hardLimitN2 = hardLimit(
					dataSetNeurona2[i][0] * weightNeuronTwoX1 + dataSetNeurona2[i][1] * weightNeuronTwoX2 - baiasTwo);

			if (i == 1) {
				System.out.println(hardLimitN2 + " | " + hardLimitN1 + " = " + resultado(hardLimitN2, hardLimitN1));
			} else {
				System.out.println(hardLimitN1 + " | " + hardLimitN2 + " = " + resultado(hardLimitN1, hardLimitN2));
			}
		}
	}

	private int resultado(int x1, int x2) {
		return x1 == 1 && x2 == 0 || x1 == 0 && x2 == 1 ? 1 : 0;
	}

	public static void main(String[] args) {
		IdentifierXOR xor = new IdentifierXOR();
		xor.aprendizaje();
		xor.mostrarEcuaciones();
	}

}
