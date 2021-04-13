package com.example.listapersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

import static com.example.listapersonagens.ui.activities.ConstantesActivities.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem"; // setando o titulo
    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagem"; // setando o titulo


    //pegando os ID dos campos para a classe
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

//criando um bando de data do personagem
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
        inicializacaoCampos();
        configuraBotaoSalvar();
        carregaPersonagem();

    }

    private void carregaPersonagem() {
        Intent dados = getIntent();
        if(dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        }

        else{
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();

        }
    }

    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }

    private void configuraBotaoSalvar() {
        //Pegando o botao e colocando um listener de açoes nele(Click)
        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finalizarFormulario();

            }
        });
    }

    private void finalizarFormulario() {
        preencherPersonagem();

        if(personagem.idValido()){ // se id foi valido
            dao.editar(personagem); // edita personagem
            finish();  // finaliza
        }
        else{ // caso id não for valido
            dao.salvar(personagem); // salva personagem
        }
        finish(); //finaliza
    }

    private void inicializacaoCampos() {
        // setando editexts com dados da hud
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }

    private void preencherPersonagem(){

        //pegando os dados e colocando em variaveis
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        //setta e edita os dados
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);


    }



}