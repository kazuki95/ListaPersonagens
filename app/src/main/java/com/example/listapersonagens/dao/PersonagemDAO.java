package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;


import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();
//numerando personagens
    private static int contadorDeId = 1;

    public void salvar(Personagem personagemSalvo) {


        personagemSalvo.setId(contadorDeId);
//adiciona personagem
        personagens.add(personagemSalvo);
        contadorDeId++;

    }
//codigo para edição do personagem
    public void editar(Personagem personagem){
        Personagem personagemEscolhido = null;
        for (Personagem p:
                personagens) {
            if(p.getId() == personagem.getId()){
                personagemEscolhido = p;
            }
        }
        if(personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem, personagem);

        }
        

    }

//criando lista
    public List<Personagem> todos(){

        return new ArrayList<>(personagens);

    }


}
