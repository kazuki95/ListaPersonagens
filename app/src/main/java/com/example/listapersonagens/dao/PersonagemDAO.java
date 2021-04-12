package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;


import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();

    public void salva(Personagem personagemSalvo) {
//adiciona personagem
        personagens.add(personagemSalvo);

    }
//criando lista
    public List<Personagem> todos(){

        return new ArrayList<>(personagens);

    }


}
