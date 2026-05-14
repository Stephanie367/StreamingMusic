package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

public abstract class ItemReproducao implements Reproduzivel {

    protected String nome;
    protected boolean reproduzindo;
    protected boolean pausado;

    public ItemReproducao(String nome) {
        this.nome = nome;
        this.reproduzindo = false;
        this.pausado = false;
    }

    @Override
    public void pausar() {
        if (reproduzindo) {
            pausado = true;
            reproduzindo = false;
            System.out.println("⏸ Pausado: " + nome);
        } else {
            System.out.println("Nada está sendo reproduzido.");
        }
    }

    @Override
    public void parar() {
        reproduzindo = false;
        pausado = false;
        System.out.println("⏹ Parado: " + nome);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public boolean isReproduzindo() { return reproduzindo; }
    public boolean isPausado() { return pausado; }
}