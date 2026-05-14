package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import java.util.ArrayList;

public class UsuarioPremium extends Usuario implements Baixavel {

    private String plano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("[ALTA QUALIDADE] Reproduzindo: " + musica.getTitulo());
        historicoReproducao.add(musica);
    }

    @Override
    public String getTipoUsuario() {
        return "Premium";
    }

    // ======================== Baixavel ========================

    @Override
    public void baixar(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("Musica '" + musica.getTitulo() + "' baixada com sucesso.");
        } else {
            System.out.println("Musica ja esta baixada!");
        }
    }

    @Override
    public void removerDownload(Musica musica) {
        if (musicasBaixadas.remove(musica)) {
            System.out.println("Download de '" + musica.getTitulo() + "' removido.");
        } else {
            System.out.println("Musica nao encontrada nos downloads.");
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return musicasBaixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return musicasBaixadas.size();
    }

    // ======================== Métodos legados (compatibilidade) ========================

    public void baixarMusica(Musica musica) {
        baixar(musica);
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

    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
    public ArrayList<Musica> getMusicasBaixadas() { return musicasBaixadas; }
}