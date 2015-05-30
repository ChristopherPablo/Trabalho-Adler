package com.example.formtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	UserDatabaseHelper helper = new UserDatabaseHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//"Chamando" os objetos
		final EditText login = (EditText)findViewById(R.id.login);
		final EditText senha = (EditText)findViewById(R.id.senha);
		Button entrar = (Button) findViewById(R.id.entrar);
		Button registrar = (Button) findViewById(R.id.cadastrar);
	
		// Resposta ao botão Cadastrar
		registrar.setOnClickListener(new View.OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				Intent itCadastrar = new Intent(MainActivity.this, NovoUsuario.class);
				startActivity(itCadastrar);
			}
		});
		
		// Resposta ao Botão de Login
		entrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String loginstr = login.getText().toString();
				String senhastr = senha.getText().toString();
				
				String DBpassword = helper.searchPass(loginstr);
				if(senhastr.equals(DBpassword))
				{
					Toast sucess = Toast.makeText(MainActivity.this, "Você entrou no sistema!", Toast.LENGTH_SHORT);
					sucess.show();
				}
				else
				{
					Toast temp = Toast.makeText(MainActivity.this, "Usuario e Senha inválidos", Toast.LENGTH_SHORT);
					temp.show();
				}
				
			}
		});
		
		
	
	}
}
