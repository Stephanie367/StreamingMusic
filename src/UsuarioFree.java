public class UsuarioFree extends Usuario {

    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;
    private int limiteReproducoes = 30;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    // sobrescrevo o método da superclasse para adicionar as regras do usuario Free
    @Override
    public void reproduzirMusica(Musica musica) {
        if (contadorReproducoes >= limiteReproducoes) {
            System.out.println("[LIMITE] Limite diario de reproducoes atingido!");
            System.out.println("Assine Premium para reproducoes ilimitadas.");
            return;
        }

        contadorReproducoes++;

        // a cada 3 reproducoes exibo um anuncio
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }

        super.reproduzirMusica(musica);
    }

    @Override
    public String getTipoUsuario() {
        return "Free";
    }

    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("[LIMITE] Limite de playlists atingido para conta Free.");
            System.out.println("Assine o plano Premium para criar playlists ilimitadas.");
            return;
        }
        playlists.add(new PlaylistPersonalizada(nome, this.nome));
        System.out.println("Playlist criada com sucesso.");
    }

    private void exibirAnuncio() {
        System.out.println("\n==================================================");
        System.out.println("ANUNCIO: Assine Premium e ouca sem interrupcoes!");
        System.out.println("==================================================\n");
    }

    public int getContadorReproducoes() { return contadorReproducoes; }
}