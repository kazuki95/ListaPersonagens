package com.example.listapersonagens.dao;

import com.example.listapersonagens.model.Personagem;


import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    private final static List<Personagem> personagens = new ArrayList<>();

    private static int contadorDeId = 1; // atribuir contadorDeId para 1

    public void salvar(Personagem personagemSalvo) { // salva item
        personagemSalvo.setId(contadorDeId); // adicionando 1 item por personagem


//adiciona personagem
        personagens.add(personagemSalvo); // adicionando personagem
        atualizaID();

    }

    private int atualizaID() {
        return contadorDeId++; // soma 1
    }

    //codigo para edição do personagem
    public void editar(Personagem personagem) {
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
//caso seja diferente de nulo
        if (personagemEscolhido != null) {
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido); // posicao do personagem
            personagens.set(posicaoDoPersonagem, personagem);

        }


    }

    private Personagem buscaPersonagemId(Personagem personagem) {
        for (Personagem p :
                personagens) { // for each para checar a lista
            if (p.getId() == personagem.getId()) { // caso seja nulo
                return p; // // armazenar informações
            }
        }
        return null;
    }

    //criando lista
    public List<Personagem> todos() {

        return new ArrayList<>(personagens);

    }


}
