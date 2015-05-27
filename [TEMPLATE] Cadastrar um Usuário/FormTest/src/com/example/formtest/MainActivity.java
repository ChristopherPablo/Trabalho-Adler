package com.example.formtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//"Chamando" os objetos
		/*final EditText cadastrar = (EditText)findViewById(R.id.cadastrar);
		final EditText login = (EditText)findViewById(R.id.login);
		final EditText senha = (EditText)findViewById(R.id.senha);
		Button entrar = (Button) findViewById(R.id.entrar);
		Button limpar = (Button) findViewById(R.id.limpar);*/
		Button registrar = (Button) findViewById(R.id.cadastrar);
		
		
		
	
		// Resposta ao botão Entrar
		registrar.setOnClickListener(new View.OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				Intent itCadastrar = new Intent(MainActivity.this, NovoUsuario.class);
				startActivity(itCadastrar);
			}
		});
	
	}
}
