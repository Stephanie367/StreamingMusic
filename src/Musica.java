import java.util.Arrays;
import java.util.List;

public class Musica {
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

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        }
    }

    public String getArtista() { return artista; }
    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
            this.artista = artista.trim();
        }
    }

    public int getDuracaoSegundos() { return duracaoSegundos; }
    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos > 0 && duracaoSegundos < 3600) {
            this.duracaoSegundos = duracaoSegundos;
        }
    }

    public String getGenero() { return genero; }
    public void setGenero(String genero) {
        this.genero = null;
        List<String> validos = Arrays.asList("Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica");
        for (String g : validos) {
            if (g.equalsIgnoreCase(genero)) {
                this.genero = g;
                return;
            }
        }
    }


    public void exibir() {
        System.out.println(this.titulo + " - " + this.artista + " [" + this.genero + "] | " + getDuracaoFormatada());
    }

    public String getDuracaoFormatada() {
        int mm = this.duracaoSegundos / 60;
        int ss = this.duracaoSegundos % 60;
        return String.format("%02d:%02d", mm, ss);
    }

    public boolean contemTitulo(String busca) {
        return this.titulo != null && this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return this.artista != null && this.artista.toLowerCase().contains(busca.toLowerCase());
    }
}