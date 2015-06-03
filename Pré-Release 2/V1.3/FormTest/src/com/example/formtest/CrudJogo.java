package com.example.formtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class CrudJogo extends Activity
{
	Spinner sp;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Liberando visualização para o usuário
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_crud_jogos);
		
		// Alimentando o botão Spinner!
				String[] opTipoSpinnerJogo = new String[] {"...", "Locação", "Venda"};
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opTipoSpinnerJogo);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				sp = (Spinner) findViewById(R.id.spinnerJogo);
				sp.setAdapter(adapter);		
				
				// Botões, TextosEditáveis e o que foi digitado
				final Button btVoltar = (Button) findViewById(R.id.cancelar3);
				final Button btCadastrar = (Button) findViewById(R.id.CadastrarNovoJogo);
				final Button btBuscar = (Button) findViewById(R.id.BuscarJogo);
				
				// Encerra Cadastro
				btVoltar.setOnClickListener(new View.OnClickListener()
				{	
					@Override
					public void onClick(View v)
					{
						finish();
					}
				});
				
				btCadastrar.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						// Chamar a Atividade de Cadastro de novo Jogo
						Intent itCadastrar = new Intent(CrudJogo.this, CadastroJogos.class);
						startActivity(itCadastrar);
					}
				});
				
				btBuscar.setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						// Realiza a Busca e atualiza a listagem
					}
				});
				
		
		
	}
}
