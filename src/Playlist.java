import java.util.ArrayList;

public class Playlist {
    String nome;
    ArrayList<Musica> musicas = new ArrayList<>();

    void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    void removerMusica(int indice) {
        if (indice >= 0 && indice < this.musicas.size()) {
            this.musicas.remove(indice);
        }
    }

    void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.nome + " ---");
        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            this.musicas.get(i).exibir();
        }
    }

    int getDuracaoTotal() {
        int total = 0;
        for (Musica m : this.musicas) {
            total += m.duracaoSegundos;
        }
        return total;
    }

    int getQuantidadeMusicas() {
        return this.musicas.size();
    }
}