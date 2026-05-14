package br.com.streaming.util;

import java.util.Arrays;
import java.util.List;

public class Validador {

    private static final List<String> GENEROS_VALIDOS =
            Arrays.asList("Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica");

    public static boolean validarGenero(String genero) {
        if (genero == null) return false;
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero)) return true;
        }
        return false;
    }

    public static String normalizarGenero(String genero) {
        if (genero == null) return null;
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero)) return g;
        }
        return null;
    }

    public static boolean validarTexto(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarDuracao(int duracao) {
        return duracao > 0 && duracao < 3600;
    }

    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static List<String> getGenerosValidos() {
        return GENEROS_VALIDOS;
    }
}