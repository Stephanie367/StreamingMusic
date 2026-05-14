package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import java.util.ArrayList;

public class GeradorRecomendacoes {

    public static ArrayList<Musica> gerarTop10(ArrayList<Musica> todasMusicas) {
        ArrayList<Musica> resultado = new ArrayList<>();
        int limite = Math.min(10, todasMusicas.size());
        for (int i = 0; i < limite; i++) {
            resultado.add(todasMusicas.get(i));
        }
        return resultado;
    }

    public static ArrayList<Musica> gerarRecomendadas(ArrayList<Musica> todasMusicas) {
        ArrayList<Musica> resultado = new ArrayList<>();
        int inicio = todasMusicas.size() / 2;
        for (int i = inicio; i < todasMusicas.size(); i++) {
            resultado.add(todasMusicas.get(i));
        }
        return resultado;
    }

    public static ArrayList<Musica> gerarRecentes(ArrayList<Musica> todasMusicas) {
        ArrayList<Musica> resultado = new ArrayList<>();
        int inicio = Math.max(0, todasMusicas.size() - 5);
        for (int i = inicio; i < todasMusicas.size(); i++) {
            resultado.add(todasMusicas.get(i));
        }
        return resultado;
    }
}