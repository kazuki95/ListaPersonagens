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

public class FormularioPersonagemActivity extends AppCompatActivity {
//pegando os ID dos campos para a classe
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;

//criando um bando de data do personagem
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem Personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);
// setando o titulo
        setTitle("Formulário de Personagens");

        inicializacaoCampos();
        configuraBotao();

        Intent dados = getIntent();
                if(dados.hasExtra("personagem")) {
                    Personagem personagem = (Personagem) dados.getSerializableExtra("personagem");
                    campoNome.setText(personagem.getNome());
                    campoAltura.setText(personagem.getAltura());
                    campoNascimento.setText(personagem.getNascimento());
                }

                else{

                    Personagem = new Personagem();

                }

    }

    private void configuraBotao() {
        //Pegando o botao e colocando um listener de açoes nele(Click)
        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//pegando os dados e colocando em variaveis
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
//salvando os parametros em dao atraves do salvar
                dao.salvar(personagemSalvo);
//terminando formulário
                finish();
//setta e edita os dados
                personagemSalvo.setNome(nome);
                personagemSalvo.setAltura(altura);
                personagemSalvo.setNascimento(nascimento);
                dao.editar(personagemSalvo);

            }
        });
    }

    private void inicializacaoCampos() {
        // setando editexts com dados da hud
        campoNome = findViewById(R.id.edittext_nome);
        campoAltura = findViewById(R.id.edittext_altura);
        campoNascimento = findViewById(R.id.edittext_nascimento);
    }

}