public class Mago extends Personagem {
    public Mago(String nome) {
        super(nome, 80, 25, 8);
    }

    @Override
    protected int calcularDano() {
        // Magos têm chance de ataque mágico aumentado
        if (Math.random() < 0.3) {
            System.out.println(this.nome + " canaliza poder mágico!");
            return ataque + 15 + (int)(Math.random() * 10);
        }
        return ataque + (int)(Math.random() * 8);
    }
}