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
//setando elas
        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
    }



    public Personagem(){

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


// convertendo o nome para uma string de exibição
    @NonNull
    @Override
    public String toString() {
        return nome;
    }


// Get e Set do Id
    public void setId(int id){
    this.id = id;
    }
//Posicionamento na localização da lista
    public int getId(){
        return id;
    }



/*    public String getNome() {
        return nome;
    }

    public String getAltura() {
        return altura;
    }

    public String getNascimento() {
        return nascimento;
    }*/
}
