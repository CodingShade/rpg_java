public class Guerreiro extends Personagem {
    public Guerreiro(String nome) {
        super(nome, 120, 18, 15);
    }

    @Override
    protected int calcularDano() {
        // Guerreiros têm 20% de chance de golpe crítico
        if (Math.random() < 0.2) {
            System.out.println("Golpe crítico do guerreiro!");
            return (int)(ataque * 1.5) + (int)(Math.random() * 5);
        }
        // Dano normal do guerreiro
        return ataque + (int)(Math.random() * 5);
    }
}