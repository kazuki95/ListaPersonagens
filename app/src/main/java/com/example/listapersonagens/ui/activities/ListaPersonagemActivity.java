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

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

    // setando o titulo
    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();


    //criando um override para a lista de personagens
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();

    }

    private void configuraFabNovoPersonagem() {
        //pegando o FloatingActionButton que possui a chamada setOnCLickListener que irá direcionar o usuario ao Formulario
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab_add);
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormulario();
            }
        });
    }

    private void abreFormulario() {
        startActivity(new Intent(this, FormularioPersonagemActivity.class));
    }

// fazendo proteção aos dados para eles não apagarem quando der back no app
    @Override
    protected void onResume() {
        super.onResume();

        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);
//referenciar dao.todos como personagem para acessar os dados
        final List<Personagem> personagens = dao.todos();

        listaDePersonagens(listaDePersonagens, personagens);

        configuraItemPorClique(listaDePersonagens);
    }

    private void configuraItemPorClique(ListView listaDePersonagens) {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//Metodo para seleção de personagem
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioEditar(personagemEscolhido);


            }
        });
    }

    private void abreFormularioEditar(Personagem personagemEscolhido) {
        //entrar no formulario
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);//ver formulario do personagem
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido); // utilizando gets
        startActivity(vaiParaFormulario); // chama o formulário
    }

    private void listaDePersonagens(ListView listaDePersonagens, List<Personagem> personagens) {
        //setando os personagens na lista no app
        listaDePersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personagens));
    }
}
