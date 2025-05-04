public class Arqueiro extends Personagem {
    public Arqueiro(String nome) {
        super(nome, 90, 20, 10);
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
        this.inventario.add("Poção de Cura");
    }

    @Override
    protected int calcularDano() {
        // Arqueiros são mais precisos (variação menor de dano)
        return ataque + (int)(Math.random() * 3) + 2;
    }
}