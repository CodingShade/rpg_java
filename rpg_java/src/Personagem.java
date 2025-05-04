import java.util.ArrayList;
import java.util.List;

public abstract class Personagem {
    // Atributos básicos
    protected String nome;
    protected int vida;
    protected int vidaMaxima;
    protected int ataque;
    protected int defesa;
    protected List<String> inventario;
    protected boolean defendendo;

    // Construtor
    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.inventario = new ArrayList<>();
        this.defendendo = false;

        // Adiciona poções de cura padrão
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
    }

    // Métodos de combate
    public void atacar(Personagem alvo) {
        int dano = calcularDano();
        System.out.println(this.nome + " ataca " + alvo.nome + "!");
        alvo.tomarDano(dano);
        this.defendendo = false; // Defesa só vale por um turno
    }

    public void defender() {
        System.out.println(this.nome + " está se defendendo!");
        this.defendendo = true;
    }

    public void tomarDano(int dano) {
        if (defendendo) {
            dano = Math.max(1, dano - defesa); // Reduz o dano recebido
            System.out.println(this.nome + " defendeu parte do ataque!");
        }

        vida -= dano;
        System.out.println(this.nome + " sofreu " + dano + " de dano!");

        if (vida <= 0) {
            vida = 0;
            System.out.println(this.nome + " foi derrotado!");
        }
    }

    // Métodos de status
    public boolean estaVivo() {
        return vida > 0;
    }

    public String getStatus() {
        return nome + " - Vida: " + vida + "/" + vidaMaxima +
                " | Ataque: " + ataque + " | Defesa: " + defesa +
                " | Poções: " + contarPocoes();
    }

    // Métodos de inventário
    public void usarItem(String item) {
        if (inventario.contains(item)) {
            inventario.remove(item);
            if (item.equals("Poção de Cura")) {
                vida = Math.min(vidaMaxima, vida + 20);
                System.out.println(nome + " usou Poção de Cura e recuperou 20 de vida!");
            }
        }
    }

    private int contarPocoes() {
        int count = 0;
        for (String item : inventario) {
            if (item.equals("Poção de Cura")) {
                count++;
            }
        }
        return count;
    }

    // Método abstrato para cálculo de dano (será implementado nas subclasses)
    protected abstract int calcularDano();
}