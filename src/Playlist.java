import java.util.ArrayList;

public class Playlist {
    protected String nome;
    protected ArrayList<Musica> musicas = new ArrayList<>();
    protected String descricao;

    public Playlist() {}

    public Playlist(String nome) {
        setNome(nome);
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            this.nome = "Sem Título";
        }
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            this.musicas.add(musica);
        }
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < this.musicas.size()) {
            this.musicas.remove(indice);
        }
    }

    // método base que será sobrescrito nas subclasses
    public void reproduzir() {
        System.out.println("🎵 Reproduzindo playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("  ▶ " + m.getTitulo());
        }
    }

    public void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.nome + " ---");
        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            this.musicas.get(i).exibir();
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : this.musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }
}