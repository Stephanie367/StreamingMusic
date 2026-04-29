import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    // sobrescrevo reproduzir() para mostrar o criterio antes de tocar
    @Override
    public void reproduzir() {
        System.out.println("Playlist Automática: " + nome);
        System.out.println("Critério: " + criterio);
        super.reproduzir();
    }

    // atualiza as musicas da playlist de acordo com o criterio escolhido
    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();

        if (criterio.equals("top")) {
            int limite = Math.min(10, todasMusicas.size());
            for (int i = 0; i < limite; i++) {
                musicas.add(todasMusicas.get(i));
            }
        } else if (criterio.equals("recomendadas")) {
            int inicio = todasMusicas.size() / 2;
            for (int i = inicio; i < todasMusicas.size(); i++) {
                musicas.add(todasMusicas.get(i));
            }
        } else if (criterio.equals("recentes")) {
            int inicio = Math.max(0, todasMusicas.size() - 5);
            for (int i = inicio; i < todasMusicas.size(); i++) {
                musicas.add(todasMusicas.get(i));
            }
        }
    }

    public String getCriterio() { return criterio; }
    public void setCriterio(String criterio) { this.criterio = criterio; }
}