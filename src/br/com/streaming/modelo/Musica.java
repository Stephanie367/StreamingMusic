package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;
import br.com.streaming.util.Validador;
import br.com.streaming.util.FormatadorTempo;

public class Musica implements Reproduzivel {

    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    public Musica() {}

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    // ======================== Reproduzivel ========================

    @Override
    public void reproduzir() {
        System.out.println("▶ Reproduzindo: " + titulo + " - " + artista);
    }

    @Override
    public void pausar() {
        System.out.println("⏸ Pausado: " + titulo);
    }

    @Override
    public void parar() {
        System.out.println("⏹ Parado: " + titulo);
    }

    @Override
    public int getDuracaoTotal() {
        return duracaoSegundos;
    }

    // ======================== Getters / Setters ========================

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) {
        if (Validador.validarTexto(titulo)) {
            this.titulo = titulo.trim();
        }
    }

    public String getArtista() { return artista; }

    public void setArtista(String artista) {
        if (Validador.validarTexto(artista)) {
            this.artista = artista.trim();
        }
    }

    public int getDuracaoSegundos() { return duracaoSegundos; }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (Validador.validarDuracao(duracaoSegundos)) {
            this.duracaoSegundos = duracaoSegundos;
        }
    }

    public String getGenero() { return genero; }

    public void setGenero(String genero) {
        this.genero = Validador.normalizarGenero(genero);
    }

    public String getDuracaoFormatada() {
        return FormatadorTempo.formatarSegundos(duracaoSegundos);
    }

    public void exibir() {
        System.out.println(titulo + " - " + artista + " [" + genero + "] | " + getDuracaoFormatada());
    }

    public boolean contemTitulo(String busca) {
        return titulo != null && titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return artista != null && artista.toLowerCase().contains(busca.toLowerCase());
    }
}