package TrabalhoC1_01_LPOO;

import java.util.Random;
import java.util.Scanner;

public class Aplicativo {

	public static Alvo[][] alvo = new Alvo[5][5];
	static Scanner entrada = new Scanner(System.in);
	static Random randomico = new Random();

	public static void main (String[] args) {
		
		preencherMatriz(alvo);

		boasVindas();

		atirar();
	}

	private static void preencherMatriz (Alvo[][] alvo) {
        
		boolean matrizCompleta = false;
		int posicaoRandomicaX, posicaoRandomicaY;
		int numeroBrancos = 0, numeroPretos = 0, numeroNulos = 0;
		char cor = ' ';
	
			do {
				posicaoRandomicaX = randomico.nextInt(5);
				posicaoRandomicaY = randomico.nextInt(5);
	
				if (numeroBrancos < 3 && alvo[posicaoRandomicaX][posicaoRandomicaY] == null) {
					cor = 'B';
					alvo[posicaoRandomicaX][posicaoRandomicaY] = new Alvo(posicaoRandomicaX, posicaoRandomicaY, cor);
					numeroBrancos++;

				} else if (numeroBrancos == 3 && numeroPretos < 12 && alvo[posicaoRandomicaX][posicaoRandomicaY] == null) {
					cor = 'P';
					alvo[posicaoRandomicaX][posicaoRandomicaY] = new Alvo(posicaoRandomicaX, posicaoRandomicaY, cor);
					numeroPretos++;

				} else if (numeroBrancos == 3 && numeroPretos == 12 && alvo[posicaoRandomicaX][posicaoRandomicaY] == null) {
					cor = 'N';
					alvo[posicaoRandomicaX][posicaoRandomicaY] = new Alvo(posicaoRandomicaX, posicaoRandomicaY, cor);
					numeroNulos++;
				}
	
				if (numeroBrancos == 3 && numeroPretos == 12 && numeroNulos == 10) {
					matrizCompleta = true;
				}
	
			} while (matrizCompleta != true);
	
		}
	
	private static void boasVindas() {

		System.out.print("\nSeja bem-vindo ao jogo Tiro ao Alvo!"
						+"\n\nEste jogo foi desenvolvido pelo grupo Neumann Software. Os desenvolvedores deste jogo são: "
						+"\n	Matheus de Paula Barbosa"
						+"\n	Gabriel Cardozo Ansequi Rangel"
						+"\n	Nathan da Silva Bravin"
						+"\n	Rafael Victor de Oliveira Lopes");

		System.out.print("\n\nO jogo é composto de bolas brancas e pretas. Sua missão é acertar todas as 3 bolas brancas." 
						+"\nA cada bola preta acertada, uma vida é perdida e uma nova bola preta será criada."
						+"\nVocê terá somente 10 tentativas."
						+"\n\nBoa sorte!\n");
	}

	private static void atirar() {

		int posicaoX, posicaoY;
		int quantidadeBalas = 10;
		int numeroBranco = 3;
		char opcao;

		do {
			statusJogo(quantidadeBalas, numeroBranco);

			do {
				System.out.print("\nInsira a posição X (De 1 a 5): ");
				posicaoX = entrada.nextInt();
			} while (posicaoX <= 0 || posicaoX > 5);

			do {
				System.out.print("Insira a posição Y (De 1 a 5): ");
				posicaoY = entrada.nextInt();
			} while (posicaoY <= 0 || posicaoY > 5);

			opcao = Alvo.atira(posicaoX, posicaoY, alvo);

			switch (opcao) {
			
			case 'B':
					System.out.println("\nVocê acertou... um alvo branco e recebeu mais uma vida!");
					alvo[posicaoX-1][posicaoY-1].setCor('N');
					numeroBranco--;
					quantidadeBalas--;
				break;

			case 'P':
					System.out.println("\nVocê acertou... um alvo preto e tomou um dano! Um novo alvo preto foi gerado...");
					acertarPreto(posicaoX, posicaoY, alvo);
					quantidadeBalas--;
				break;

			case 'N':
					System.out.println("\nVocê acertou... nada!");
					quantidadeBalas--;
				break;
			}

		} while (Alvo.getVida() != 0 && quantidadeBalas != 0 && numeroBranco != 0);

		fimJogo(quantidadeBalas, numeroBranco);
	}

	private static boolean acertarPreto(int posicaoX, int posicaoY, Alvo[][] alvo) {

		alvo[posicaoX-1][posicaoY-1].setCor('N');
        boolean novoAlvo = false;
        int posicaoRandomicaX, posicaoRandomicaY;

            do {
                posicaoRandomicaX = randomico.nextInt(5);
                posicaoRandomicaY = randomico.nextInt(5);

                if (alvo[posicaoRandomicaX][posicaoRandomicaY].getCor() == 'N') {
                    alvo[posicaoRandomicaX][posicaoRandomicaY].setCor('P');
                    novoAlvo = true;
                }

			} while (novoAlvo != true);

			return novoAlvo;
    }

	private static void statusJogo (int bala, int numeroBranco) {

		System.out.println("\nVidas:" + mostrarVida()
						  +"\nBalas:" + mostrarBalas(bala)
						  +"\nAlvos brancos restantes:" + mostrarBrancos(numeroBranco));
	}

	private static String mostrarVida() {

		String vidasRestantes = " ";	
		
		for (byte contador = 0; contador < Alvo.getVida(); contador++) {
			vidasRestantes = vidasRestantes.concat("🤍 ");
		}

		return vidasRestantes;
	}

	private static String mostrarBrancos (int numeroBrancos) {

		String brancosRestantes = " ";

		for (byte contador = 0; contador < numeroBrancos; contador++) {
            brancosRestantes = brancosRestantes.concat("⚪ ");
        }

		return brancosRestantes;
    }

	private static String mostrarBalas (int balas) {

		String balasRestantes = " ";

		for (byte contador = 0; contador < balas; contador++) {
			balasRestantes = balasRestantes.concat("🡅  ");
		}

		return balasRestantes;
	}

	private static void fimJogo (int bala, int numeroBranco) {
	
		statusJogo(bala, numeroBranco);

		if (bala == 0) {
			System.out.print(
				 "\n                             ⡠⣄⡀⠀⠀⡠⠞⠛⢦⣠⢤⡀⠀       "
				 +"\n            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢠⠏⠀⠀⢱⡀⣸⠁⠀⡴⠋⠀⠀⣹   "
				 +"\n            ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠋⠉⢿⢀⡤⠶⣴⠇⣯⠀⣼⠁⠀⢀⡴⠷⣄  "
				 +"\n           ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠁⠀⣀⡾⠋⠀⠀⢹⣼⠁⢠⡇⠀⡴⠋⠀⠀⡼   "
				 +"\n          ⠀⠀⠀⠀⢠⠊⠑⢦⠀⡴⠋⢀⣠⠞⠉⠀⠀⠀⣠⣿⠧⣄⡾⠁⡼⠁⣀⣤⠾⡁    "
				 +"\n         ⠀⠀⠀⠀⢸⠀⠀⣨⠟⠁⢠⡞⠁⠀⠀⠀⣠⡾⠛⠁⠀⣿⠃⣰⠃⣴⠋⠀⠀⣷     "
				 +"\n         ⠀⠀⠀⠀⣸⢠⠞⠁⠀⢠⠏⠀⠀⢀⡴⠋⠁⠀⢀⣠⡴⠿⣶⡇⢰⠇⠀⠀⢠⠇     "
				 +"\n         ⠀⠀⠀⢠⢿⠏⠀⠀⠀⠉⠀⠀⣠⠞⠁⠀⡴⠚⠉⠁⠀⢀⡟⠀⣼⠀⠀⠀⢸      "
				 +"\n         ⠀⠀⠀⡾⣼⢀⠀⠀⠀⠀⠀⠈⠉⠀⣠⠞⠁⠀⠀⢀⡴⠋⠙⢼⠃⠀⠀⠀⣸      "
				 +"\n         ⠀⠀⠀⡇⠉⡎⠀⣰⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⡼⠉⠀⠀⠀⠘⠂⠀⠀⣠⠇      "
				 +"\n         ⠀⠀⠀⡇⢸⠀⣰⠃⠀⡴⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⣠⠖⠁       "
				 +"\n          ⠀⠀⢸⠁⡏⢠⠃⢀⠞⠀⠀⠀⠀⠀⠀⢸⠁⠀⠀⠀⠀⢀⣠⠖⠋⠁        "
				 +"\n          ⠀⠀⡞⠀⠃⡎⢀⠏⠀⠀⠀⠀⠀⠀⢀⡏⠀⣀⡤⠴⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀   " 
				 +"\n          ⡴⢺⠇⠀⠀⠀⠞⠀⠀⠀⠀⠀⠀⢀⡾⠒⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   "
				 +"\n          ⡇⠘⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   "
				 +"\n          ⢳⡀⠘⢦⡀⠀⠀⠀⠀⠀⠀⡰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   "
				 +"\n          ⠀⠳⣄⠀⠙⠲⣤⣀⣠⠴⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   "
				 +"\n          ⠀⠀⠈⠓⠦⣄⣀⡠⠎⠀                           ");  

		
			System.out.print("\n▒█▀▀█ ░█▀▀█ ▒█▀▀█ ░█▀▀█ ▒█▀▀█ ▒█▀▀▀ ▒█▄░▒█ ▒█▀▀▀█ ░░   ▒█░░▒█ ▒█▀▀▀█ ▒█▀▀█ ▒█▀▀▀  ▒█▀▀█ ▒█▀▀▀ ▒█▀▀█ ▒█▀▀▄ ▒█▀▀▀ ▒█░▒█"
							+"\n▒█▄▄█ ▒█▄▄█ ▒█▄▄▀ ▒█▄▄█ ▒█▀▀▄ ▒█▀▀▀ ▒█▒█▒█ ░▀▀▀▄▄ ▄▄   ░▒█▒█░ ▒█░░▒█ ▒█░░░ ▒█▀▀▀  ▒█▄▄█ ▒█▀▀▀ ▒█▄▄▀ ▒█░▒█ ▒█▀▀▀ ▒█░▒█"
							+"\n▒█░░░ ▒█░▒█ ▒█░▒█ ▒█░▒█ ▒█▄▄█ ▒█▄▄▄ ▒█░░▀█ ▒█▄▄▄█ ░█   ░░▀▄▀░ ▒█▄▄▄█ ▒█▄▄█ ▒█▄▄▄  ▒█░░░ ▒█▄▄▄ ▒█░▒█ ▒█▄▄▀ ▒█▄▄▄ ░▀▄▄▀\n");
		}
		 
		if (Alvo.getVida() == 0) {
			System.out.print("\n▒█▀▀█ ░█▀▀█ ▒█▄░▒█ ▒█░▒█ ▒█▀▀▀█ ▒█░▒█  █"
							+"\n▒█░▄▄ ▒█▄▄█ ▒█▒█▒█ ▒█▀▀█ ▒█░░▒█ ▒█░▒█  ▀"
							+"\n▒█▄▄█ ▒█░▒█ ▒█░░▀█ ▒█░▒█ ▒█▄▄▄█ ░▀▄▄▀  ▄\n");
		}
	}

}