package com.example.listapersonagens.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
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
    private ArrayAdapter<Personagem> adapter;


    //criando um override para a lista de personagens
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPersonagem();
        configuraLista();
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
        atualizaPersonagem();

    }

    private void atualizaPersonagem() {
//limpa lista
        adapter.clear();
//construir lista novamente
        adapter.addAll(dao.todos());
    }

    private void remove(Personagem personagem){
//remover do dao
        dao.remove(personagem);
        adapter.remove(personagem);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//menu para remover item
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagens_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        configuraMenu(item);

//retorna valor atual para o ContextItem
        return super.onContextItemSelected(item);
    }

    private void configuraMenu(@NonNull MenuItem item) {
        //buscando titulo por id
        int itemId = item.getItemId();

//Se titulo do menu for Remover execute
        if(itemId == R.id.activity_lista_personagem_menu_remover) {

//pop ups perguntando se deseja deletar item
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
// caso escolha SIM para deletar
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//passar posição na lista
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
//remover o item
                            remove(personagemEscolhido);
                        }
                    })
//caso escolha NÃO para deletar
                    .setNegativeButton("Não", null)
//mostrar as janelas
                    .show();


        }
    }

    private void configuraLista() {
//carrega listagem
        ListView listaDePersonagens = findViewById(R.id.activity_main_lista_personagem);

        listaDePersonagens(listaDePersonagens);
//configuração do click
        configuraItemPorClique(listaDePersonagens);
//registro aplicado dentro do conteudo
        registerForContextMenu(listaDePersonagens);
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

    private void listaDePersonagens(ListView listaDePersonagens) {
//setando os personagens na lista no app
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }
}
