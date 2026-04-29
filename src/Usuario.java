import java.util.ArrayList;

public class Usuario {
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario() {
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public Usuario(String nome, String email) {
        this();
        setNome(nome);
        setEmail(email);
    }

    // esse método vai ser sobrescrito nas subclasses com @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("▶ Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void exibirHistorico() {
        System.out.println("\n--- HISTORICO DE REPRODUCAO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Historico vazio.");
        } else {
            for (Musica m : historicoReproducao) {
                m.exibir();
            }
        }
    }

    // sobrescrito em cada subclasse para retornar o tipo correto
    public String getTipoUsuario() {
        return "Usuario";
    }

    public int getTotalReproducoes() {
        return historicoReproducao.size();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) return playlists.get(indice);
        return null;
    }

    public ArrayList<Playlist> getPlaylists() { return playlists; }

    public void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.nome + " ---");
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
    }
}