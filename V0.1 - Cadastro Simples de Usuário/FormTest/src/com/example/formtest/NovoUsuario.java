package com.example.formtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NovoUsuario extends Activity {
	
	UserDatabaseHelper helper = new UserDatabaseHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Liberando visualização para o usuário
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);
		
		// Botões, TextosEditáveis e o que foi digitado
		final Button btVoltar = (Button) findViewById(R.id.cancelar1);
		final Button btSalvar = (Button) findViewById(R.id.salvar1);
		
		// Encerra Cadastro
		btVoltar.setOnClickListener(new View.OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		
		// Confirma Cadastro
		btSalvar.setOnClickListener(new View.OnClickListener()
		{			
			@Override
			public void onClick(View v) {
				Usuario c = new Usuario();
				String passAdmSecurity = c.getSecurityPassword();
				
				EditText ETnome = (EditText) findViewById(R.id.nome);
				EditText ETsenha = (EditText) findViewById(R.id.senha);
				EditText ETsenhaadm = (EditText) findViewById(R.id.senhaadm);
				
				String nomestr = ETnome.getText().toString();
				String senhastr = ETsenha.getText().toString();
				String senhaadmstr = ETsenhaadm.getText().toString();
								
				if(!senhaadmstr.equals(passAdmSecurity))
				{
					// Senha Administrativa Incorreta!
					Toast mensagem = Toast.makeText(NovoUsuario.this, "SENHA ADMINISTRATIVA INCORRETA", Toast.LENGTH_SHORT);
					mensagem.show();
				}
				else
				{
					// Inserir os detalhes no Banco de Dados
					c.setNome(nomestr);
					c.setPassword(senhastr);
					helper.insertUsuario(c);
					Toast mensagem = Toast.makeText(NovoUsuario.this, "Usuário Cadastrado!", Toast.LENGTH_SHORT);
					mensagem.show();
					finish();
				}
				
			}
		});
	
		
		
		
	}

}