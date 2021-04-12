package com.example.listapersonagens.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listapersonagens.R;
import com.example.listapersonagens.dao.PersonagemDAO;
import com.example.listapersonagens.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        PersonagemDAO dao = new PersonagemDAO(); //instanciando objeto
// puxando a ID dos campos do app
        EditText campoNome = findViewById(R.id.edittext_nome);
        EditText campoAltura = findViewById(R.id.edittext_altura);
        EditText campoNascimento = findViewById(R.id.edittext_nascimento);



        Button botaoSalvar = findViewById(R.id.button_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // quando clickar


//pegando os dados e colocando em variaveis
                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();

                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);

                dao.salva(personagemSalvo); //salvando os parametros em dao atraves do salva

//pega informações do campos e joga no construtor (personagemSalvo), passando os parametros(nome, altura, nascimento) com salva para o dao, assim uma vez como salvo será enviado para a listagem, remetendeo ao outro formulário e caindo lá
                startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));




           /*     Toast.makeText(FormularioPersonagemActivity.this,
                        personagemSalvo.getNome() + " - " +
                                personagemSalvo.getAltura() + " - " +
                                personagemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();*/


                new Personagem(nome, altura, nascimento); //personagem sendo criado

               // Toast.makeText(FormularioPersonagemActivity.this,"Personagem Salvo",Toast.LENGTH_SHORT).show(); // mensagem informando que foi salvo os dados


            }
        });

    }
}