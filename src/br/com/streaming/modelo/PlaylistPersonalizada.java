package br.com.streaming.modelo;

public class PlaylistPersonalizada extends Playlist {

    private String criador;

    public PlaylistPersonalizada(String nome, String criador) {
        super(nome);
        this.criador = criador;
    }

    @Override
    public void reproduzir() {
        System.out.println("Playlist Personalizada: " + getNome() + " (criada por " + criador + ")");
        for (Musica m : getMusicas()) {
            System.out.println("  ▶ " + m.getTitulo());
        }
    }

    public String getCriador() { return criador; }
    public void setCriador(String criador) { this.criador = criador; }
}