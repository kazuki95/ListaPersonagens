package com.example.listapersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {

    //pegando as variaveis
    private String nome;
    private String altura;
    private String nascimento;
    private int id = 0;

    public Personagem(String nome, String altura, String nascimento) {


//setando variaveis
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }


    public Personagem() {

    }

    // convertendo o nome para uma string de exibição
    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    // Get e Set do Id
    public void setId(int id) {
        this.id = id;
    }

    //Posicionamento na localização da lista
    public int getId() {
        return id;
    }


    public boolean idValido(){
        return id > 0;
    }
}
