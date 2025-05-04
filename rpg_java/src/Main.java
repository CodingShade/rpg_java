import java.util.Scanner;
import java.util.Random;

public class Main {
    private static final int MAX_BATALHAS = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== ARENA DE BATALHA ===");
        System.out.println("Bem-vindo ao jogo de batalha por turnos!\n");
        System.out.println("Você enfrentará no máximo " + MAX_BATALHAS + " batalhas.\n");

        // Configuração do personagem
        System.out.print("Digite o nome do seu herói: ");
        String nomeHeroi = scanner.nextLine();

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro (Vida alta, ataque médio, defesa alta)");
        System.out.println("2 - Mago (Vida baixa, ataque alto, defesa baixa)");
        System.out.println("3 - Arqueiro (Vida média, ataque médio-alto, defesa média)");
        System.out.print("Opção: ");

        int escolhaClasse = obterInteiro(scanner, 1, 3);
        Personagem heroi = criarPersonagem(nomeHeroi, escolhaClasse);

        // Sistema de batalha
        int batalhasVencidas = 0;
        boolean jogoAtivo = true;

        while (jogoAtivo && heroi.estaVivo() && batalhasVencidas < MAX_BATALHAS) {
            System.out.println("\n=================================");
            System.out.println("Batalha #" + (batalhasVencidas + 1) + " de " + MAX_BATALHAS);

            Personagem inimigo = gerarInimigo(random, batalhasVencidas);
            System.out.println("Um " + inimigo.nome + " selvagem apareceu!\n");

            // Loop de batalha
            while (heroi.estaVivo() && inimigo.estaVivo()) {
                exibirStatus(heroi, inimigo);
                turnoJogador(scanner, heroi, inimigo);

                if (inimigo.estaVivo()) {
                    turnoInimigo(inimigo, heroi);
                }
            }

            // Verificar resultado da batalha
            if (heroi.estaVivo()) {
                batalhasVencidas++;
                if (batalhasVencidas < MAX_BATALHAS) {
                    System.out.println("\nVocê venceu esta batalha!");
                    System.out.println("Preparando próximo oponente...");
                }
            } else {
                jogoAtivo = false;
                System.out.println("\nVocê foi derrotado...");
            }
        }

        // Fim de jogo
        System.out.println("\n=== FIM DE JOGO ===");
        System.out.println("Batalhas completadas: " + batalhasVencidas + " de " + MAX_BATALHAS);

        if (batalhasVencidas == MAX_BATALHAS) {
            System.out.println("Parabéns! Você completou todas as batalhas!");
            System.out.println("Você é o verdadeiro campeão da arena!");
        } else if (heroi.estaVivo()) {
            System.out.println("Você saiu da arena com vida!");
        } else {
            System.out.println("Você foi derrotado na batalha " + batalhasVencidas);
        }

        scanner.close();
    }

    private static int obterInteiro(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine());
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.print("Valor inválido. Digite entre " + min + " e " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    private static Personagem criarPersonagem(String nome, int classe) {
        switch (classe) {
            case 1: return new Guerreiro(nome);
            case 2: return new Mago(nome);
            case 3: return new Arqueiro(nome);
            default: throw new IllegalArgumentException("Classe inválida");
        }
    }

    private static Personagem gerarInimigo(Random random, int nivelDificuldade) {
        String[] nomes = {"Goblin", "Orc", "Esqueleto", "Bandido", "Lobisomem"};
        String nome = nomes[random.nextInt(nomes.length)];

        int vida = 50 + (nivelDificuldade * 10);
        int ataque = 10 + (nivelDificuldade * 2);
        int defesa = 5 + nivelDificuldade;

        return new Inimigo(nome, vida, ataque, defesa);
    }

    private static void exibirStatus(Personagem heroi, Personagem inimigo) {
        System.out.println("\n=== STATUS ===");
        System.out.println("HERÓI: " + heroi.getStatus());
        System.out.println("INIMIGO: " + inimigo.getStatus() + "\n");
    }

    private static void turnoJogador(Scanner scanner, Personagem heroi, Personagem inimigo) {
        System.out.println("Seu turno:");
        System.out.println("1 - Atacar");
        System.out.println("2 - Defender");
        System.out.println("3 - Usar Poção de Cura");
        System.out.print("Escolha sua ação: ");

        int acao = obterInteiro(scanner, 1, 3);

        switch (acao) {
            case 1:
                heroi.atacar(inimigo);
                break;
            case 2:
                heroi.defender();
                break;
            case 3:
                if (heroi.inventario.contains("Poção de Cura")) {
                    heroi.usarItem("Poção de Cura");
                } else {
                    System.out.println("Você não tem poções de cura!");
                }
                break;
        }
    }

    private static void turnoInimigo(Personagem inimigo, Personagem heroi) {
        System.out.println("\nTurno do inimigo:");
        if (inimigo instanceof Inimigo) {
            ((Inimigo) inimigo).acaoAleatoria(heroi);
        } else {
            inimigo.atacar(heroi);
        }
    }
}