package com.example.formtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class NovoUsuario extends Activity {
	
	Button btSalvar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_usuario);
		
	}

}