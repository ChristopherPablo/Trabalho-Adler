package com.example.formtest;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class CrudJogo extends Activity
{
	
	JogosDatabaseHelper JogoHelper = new JogosDatabaseHelper(this);
	Spinner sp;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Liberando visualização para o usuário
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_crud_jogos);
		
		// Alimentando o botão Spinner!
				String[] opTipoSpinnerJogo = new String[] {"Tipo de Produto", "Locação", "Venda"};
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opTipoSpinnerJogo);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				sp = (Spinner) findViewById(R.id.spinnerJogo);
				sp.setAdapter(adapter);		
				
				// Botões, TextosEditáveis e o que foi digitado
				final Button btVoltar = (Button) findViewById(R.id.cancelar3);
				final Button btCadastrar = (Button) findViewById(R.id.CadastrarNovoJogo);
				final Button btBuscar = (Button) findViewById(R.id.BuscarJogo);
				final EditText ETnome = (EditText)findViewById(R.id.ETNomeJogo);
				
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
						String nomestr = ETnome.getText().toString();
						String opSpinner = (String) sp.getSelectedItem();
						
						// Cria um cursor e o faz receber o resultado das funções de Busca de Usuários
						Cursor cursor = null;
						
						if(nomestr.length() == 0 && opSpinner == "Tipo de Produto")
						{
							cursor = JogoHelper.buscarTodosJogos();	
						}
						
						
						
						// Utiliza o Cursor obtido para mostrar o resultado
						
						// Setup mapping from cursor to view fields:
						String[] fromFieldNames = new String[] 
								{JogoHelper.KEY_NAME,  JogoHelper.KEY_TYPE, JogoHelper.KEY_FABRICANTE, JogoHelper.KEY_PRECOCOMPRA, JogoHelper.KEY_PRECOLOCADORA,
								JogoHelper.KEY_QUANTIDADE, JogoHelper.KEY_ESTILOJOGO, JogoHelper.KEY_FAIXAETARIA};
						int[] toViewIDs = new int[]
								{R.id.TVJOGOnome, R.id.TVJOGOtipo, R.id.TVJOGOfabricante, R.id.TVJOGOprecocompra,
								R.id.TVJOGOprecovenda, R.id.TVJOGOquantidade, R.id.TVJOGOestiloJogo, R.id.TVJOGOfaixaEtária};
						
						// Create adapter to may columns of the DB onto elements in the UI.
						
						@SuppressWarnings("deprecation")
						SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(CrudJogo.this, R.layout.jogos, cursor, fromFieldNames, toViewIDs);
						
						// Set the adapter for the list view
						final ListView myList = (ListView) findViewById(R.id.listView1);
						myList.setAdapter(myCursorAdapter);
						
					}
				});
				
		
		
	}
}
