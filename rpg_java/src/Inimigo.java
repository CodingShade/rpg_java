public class Inimigo extends Personagem {
    public Inimigo(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    @Override
    protected int calcularDano() {
        // Implementação básica para inimigos
        int danoBase = ataque + (int)(Math.random() * 5);

        // Inimigos têm 10% de chance de dar um golpe mais forte
        if (Math.random() < 0.1) {
            System.out.println(this.nome + " acerta um golpe surpresa!");
            return danoBase + 5;
        }
        return danoBase;
    }

    public void acaoAleatoria(Personagem alvo) {
        double chance = Math.random();

        if (chance < 0.7) { // 70% de chance de atacar
            this.atacar(alvo);
        } else if (chance < 0.9) { // 20% de chance de defender
            this.defender();
        } else { // 10% de chance de não fazer nada
            System.out.println(this.nome + " hesita...");
        }
    }
}