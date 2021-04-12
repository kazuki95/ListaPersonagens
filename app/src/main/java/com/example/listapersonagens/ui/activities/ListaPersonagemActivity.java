package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

    private final PersonagemDAO dao = new PersonagemDAO();


    //criando um override para a lista de personagens
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
// setando o titulo
        setTitle("Lista de Personagens");
        dao.salvar(new Personagem("Ken","1,80","02041979"));
        dao.salvar(new Personagem("Ryu","1,80","02041979"));



// lista criada
     //   List<String> personagem = new ArrayList<>(Arrays.asList("Alex" , "Ken" , "Ryu" , "Guile" ));


//pegando o FloatingActionButton que possui a chamada setOnCLickListener que irá direcionar o usuario ao Formulario
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });
    }
// fazendo proteção aos dados para eles não apagarem quando der back no app
    @Override
    protected void onResume() {
        super.onResume();

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
//referenciar dao.todos como personagem para acessar os dados
        List<Personagem> personagens = dao.todos();
//setando os personagens na lista no app
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));

        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//Metodo para seleção de personagem
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao);
//entrar no formulario
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra("personagem", personagemEscolhido);
                startActivity(vaiParaFormulario);




            }
        });
    }
}
