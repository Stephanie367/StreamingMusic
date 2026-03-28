import java.util.ArrayList;

public class Usuario {
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    void criarPlaylist(String nome) {
        Playlist nova = new Playlist();
        nova.nome = nome;
        this.playlists.add(nova);
    }

    Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) {
            return this.playlists.get(indice);
        }
        return null;
    }

    void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.nome + " ---");
        for (int i = 0; i < this.playlists.size(); i++) {
            Playlist p = this.playlists.get(i);
            System.out.println((i + 1) + ". " + p.nome + " (" + p.getQuantidadeMusicas() + " músicas)");
        }
    }
}