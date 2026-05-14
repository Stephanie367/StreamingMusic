package br.com.streaming.modelo;

import br.com.streaming.servico.GeradorRecomendacoes;
import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {

    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("Playlist Automática: " + nome);
        System.out.println("Critério: " + criterio);
        super.reproduzir();
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();
        switch (criterio) {
            case "top":
                musicas.addAll(GeradorRecomendacoes.gerarTop10(todasMusicas));
                break;
            case "recomendadas":
                musicas.addAll(GeradorRecomendacoes.gerarRecomendadas(todasMusicas));
                break;
            case "recentes":
                musicas.addAll(GeradorRecomendacoes.gerarRecentes(todasMusicas));
                break;
            default:
                System.out.println("Critério inválido.");
        }
    }

    public String getCriterio() { return criterio; }
    public void setCriterio(String criterio) { this.criterio = criterio; }
}