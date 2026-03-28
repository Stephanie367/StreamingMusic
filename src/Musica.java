public class Musica {
    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    void exibir() {
        System.out.println(this.titulo + " - " + this.artista + " [" + this.genero + "] | " + getDuracaoFormatada());
    }

    String getDuracaoFormatada() {
        int mm = this.duracaoSegundos / 60;
        int ss = this.duracaoSegundos % 60;
        return String.format("%02d:%02d", mm, ss);
    }

    boolean contemTitulo(String busca) {
        return this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    boolean contemArtista(String busca) {
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }
}