package com.example.formtest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AcoesLocadora extends Activity
{
	JogosDatabaseHelper JogoHelper = new JogosDatabaseHelper(this);
	Spinner sp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acoes_locadora_adm);
		
		// Alimentando o botão Spinner!
		String[] opTipoSpinnerJogo = new String[] {"Tipo de Produto", "Locação", "Venda"};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, opTipoSpinnerJogo);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp = (Spinner) findViewById(R.id.spinnerAcoes1);
		sp.setAdapter(adapter);		
		

		Button btVoltar = (Button) findViewById(R.id.cancelar5);
		Button btPesquisar = (Button) findViewById(R.id.pesquisar1);
		final EditText ETnome = (EditText)findViewById(R.id.editTextNomeJogo);
		
		btVoltar.setOnClickListener(new View.OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				finish();
			}
		});
		
		btPesquisar.setOnClickListener(new View.OnClickListener()
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
				else
				{
					if(opSpinner == "Locação")
					{
						// PROCURA POR USUÁRIOS
						cursor = JogoHelper.buscarJogo(nomestr, opSpinner);
						
					}else if(opSpinner == "Venda")
					{
						cursor = JogoHelper.buscarJogo(nomestr, opSpinner);
					}
					else
					{
						//OPÇÃO INVÁLIDA
						Toast mensagem = Toast.makeText(AcoesLocadora.this, "Tipo de Produto Inválido", Toast.LENGTH_SHORT);
						mensagem.show();
					}
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
				SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(AcoesLocadora.this, R.layout.jogos, cursor, fromFieldNames, toViewIDs);
				
				// Set the adapter for the list view
				final ListView myList = (ListView) findViewById(R.id.listView1);
				myList.setAdapter(myCursorAdapter);
				myList.setOnItemClickListener(new OnItemClickListener()
				{
				
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, final long id)
					{	
						final String[] lista = {"Registrar Compra", "Registrar Venda", "Registrar Aluguel"};
						AlertDialog.Builder alert = new AlertDialog.Builder(AcoesLocadora.this);                 
						alert.setTitle("Escolha um item");
						alert.setItems(lista, new DialogInterface.OnClickListener()
						{
							
							@Override
							public void onClick(DialogInterface dialog, int item)
							{
							// TODO Auto-generated method stub
								switch (item)
								{
									case 0: // Compra
										// Chama Activity de Compra
										setContentView(R.layout.registrar_compra);
										Button btConfirmar = (Button) findViewById(R.id.confirmar1);
										Button btCancelar = (Button) findViewById(R.id.cancelar6);
										final TextView TVnomeJogo = (TextView)findViewById(R.id.textViewAcoesLocadora1);
										final TextView TVprecoUn = (TextView)findViewById(R.id.textViewAcoesLocadora2);
										final TextView TVsubTotal = (TextView)findViewById(R.id.textViewAcoesLocadora3);
										final EditText ETquantidade = (EditText)findViewById(R.id.editTextAcoesLocadora1);
										final EditText ETdata = (EditText)findViewById(R.id.editTextAcoesLocadora2);							
										
										// Transforma quantidade e preço em float, faz a conta e passa para String e, TVsubTotal
										Jogos auxText = new Jogos();
										auxText = JogoHelper.buscarDados(id); 
										
										TVnomeJogo.setText(auxText.getNome());
										TVprecoUn.setText(auxText.getPrecoCompra());
										TVsubTotal.setText("Cálculo Subtotal!");
										
										// Botão Cancelar
										btCancelar.setOnClickListener(new View.OnClickListener()
										{	
											@Override
											public void onClick(View v)
											{
												finish();
											}
										});
										
										// Botão Confirmar Compra
										btConfirmar.setOnClickListener(new View.OnClickListener()
										{	
											@Override
											public void onClick(View v)
											{
												// Envia mensagem perguntando se tem certeza da escolha;
												// Se sim, insere no Banco de Dados;
												// Se não, finish;
												AlertDialog.Builder alert = new AlertDialog.Builder(AcoesLocadora.this);                 
												alert.setTitle("Confirmação de Compra");  
												alert.setMessage("Realmente deseja realizar o cadastro da compra deste jogo?"); 												
												
											    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
											    {  
											    	public void onClick(DialogInterface dialog, int whichButton)
											    	{  
											    		AcoesLocadoraVariaveis acoesLV = new AcoesLocadoraVariaveis();
														AcoesLocadoraDatabaseHelper acoesLHelper = new AcoesLocadoraDatabaseHelper(AcoesLocadora.this);
														
														String quantidadestr = ETquantidade.getText().toString();
														String datastr = ETdata.getText().toString();
														
														acoesLV.setNomeJogo(TVnomeJogo.getText().toString());
														acoesLV.setData(datastr);
														acoesLV.setPrecoUn(TVprecoUn.getText().toString());
														acoesLV.setQuantidade(quantidadestr);
														acoesLV.setSubTotal(TVsubTotal.getText().toString());
														
														acoesLHelper.insertAcao(acoesLV);
														finish();
											    	}  
											    });  

											    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
											    {
											    	public void onClick(DialogInterface dialog, int which)
											    	{
											    		return;   
											    	}
											    });
											    alert.show();
											}
										});
										break;
										
									case 1: // Venda
										if(!JogoHelper.buscarTipoJogo(id).equals("Venda"))
										{
											AlertDialog.Builder dialogo = new AlertDialog.Builder(AcoesLocadora.this);
											// GERANDO O BOX DE MENSAGEM
											dialogo.setTitle("Atenção");
											dialogo.setMessage("Não é Possível realizar a VENDA deste tipo de Produto!");
											dialogo.setNeutralButton("Ok", null);
											dialogo.show();
										}
										else
										{
											// Do Stuff, chamando Activity de Venda
										}
										
									
										break;
									case 2: // Aluguel
										if(!JogoHelper.buscarTipoJogo(id).equals("Locação"))
										{
											AlertDialog.Builder dialogo = new AlertDialog.Builder(AcoesLocadora.this);
											// GERANDO O BOX DE MENSAGEM
											dialogo.setTitle("Atenção");
											dialogo.setMessage("Não é Possível realizar a LOCAÇÃO deste tipo de Produto!");
											dialogo.setNeutralButton("Ok", null);
											dialogo.show();
										}
										else
										{
											// Do Stuff, chamando Activity de Venda
										}
										
										break;
								}
							}
						});
						alert.show();
					}
				});
			}
		});
	}
}
