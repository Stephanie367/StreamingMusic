import java.util.ArrayList;

public class Usuario {
    private String nome;
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public Usuario() {}

    public Usuario(String nome) {
        setNome(nome);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public void criarPlaylist(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.playlists.add(new Playlist(nome));
        }
    }

    public void adicionarPlaylist(Playlist p) {
        if (p != null) {
            this.playlists.add(p);
        }
    }

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) {
            return this.playlists.get(indice);
        }
        return null;
    }

    public void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.nome + " ---");
        for (int i = 0; i < this.playlists.size(); i++) {
            Playlist p = this.playlists.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + " (" + p.getQuantidadeMusicas() + " músicas)");
        }
    }
}