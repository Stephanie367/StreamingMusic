package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

import java.util.ArrayList;


public class Playlist implements Reproduzivel {

    protected String nome;
    protected ArrayList<br.com.streaming.modelo.Musica> musicas = new ArrayList<>();
    protected String descricao;

    public Playlist() {}

    public Playlist(String nome) {
        setNome(nome);
    }

    // ======================== Reproduzivel ========================

    @Override
    public void reproduzir() {
        System.out.println("🎵 Reproduzindo playlist: " + nome);
        for (Musica m : musicas) {
            System.out.println("  ▶ " + m.getTitulo());
        }
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Playlist pausada: " + nome);
    }

    @Override
    public void parar() {
        System.out.println("⏹ Playlist parada: " + nome);
    }

    @Override
    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    // ======================== Métodos da Playlist ========================

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

    public void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.nome + " ---");
        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            this.musicas.get(i).exibir();
        }
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }

    // ======================== Getters / Setters ========================

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
}