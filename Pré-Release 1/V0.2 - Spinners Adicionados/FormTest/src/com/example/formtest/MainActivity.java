package com.example.formtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity{
	
	UserDatabaseHelper userHelper = new UserDatabaseHelper(this);
	private Spinner sp;
	
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

		// Alimentando o botão Spinner!
		String[] opSpinnerUsuario = new String[] {"...", "Usuário", "Administrador"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, opSpinnerUsuario);
		//his, android.R.layout.simple_spinner_dropdown_item, opSpinnerUsuario);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp = (Spinner) findViewById(R.id.spinnerLogin);
		sp.setAdapter(adapter);
	
		
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
		entrar.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// Campos de Login e Senha
				String loginstr = login.getText().toString();
				String senhastr = senha.getText().toString();
				
				String opSpinner = (String) sp.getSelectedItem();
				if(opSpinner == "Usuário")
				{
					// Iniciando Banco de dados do Usuário
					String DBpassword = userHelper.searchPass(loginstr);
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
				}else if(opSpinner == "Administrador")
				{
					Toast mensagem = Toast.makeText(MainActivity.this, "Cadastro de Administrador", Toast.LENGTH_SHORT);
					mensagem.show();
				}
				else
				{
					Toast mensagem = Toast.makeText(MainActivity.this, "Opção de Usuário Inválida", Toast.LENGTH_SHORT);
					mensagem.show();
				}
		
			}
		});
		
	}
}
