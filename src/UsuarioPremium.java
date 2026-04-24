import java.util.ArrayList;

public class UsuarioPremium extends Usuario {
    private String tipoPlano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.tipoPlano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("[ALTA QUALIDADE] Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("Musica '" + musica.getTitulo() + "' baixada com sucesso.");
        } else {
            System.out.println("Musica ja esta baixada!");
        }
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MUSICAS BAIXADAS (OFFLINE) ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma musica baixada.");
        } else {
            for (Musica m : musicasBaixadas) {
                m.exibir();
            }
        }
    }

    public String getTipoPlano() { return tipoPlano; }
    public void setTipoPlano(String tipoPlano) { this.tipoPlano = tipoPlano; }
}