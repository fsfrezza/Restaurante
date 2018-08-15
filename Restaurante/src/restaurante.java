import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;

public class restaurante {
	  
	public static void main(String[] args){
		String escolhaCliente = "";
		String periodo = "";
		String opcoesPedido = "";
		String[] pedidosManha = {"ovos", "torrada", "café"};
		String[] pedidosNoite = {"carne", "batata", "vinho", "bolo"};
		String errorMsg = "";

		Object[] opcoes = {"manhã","noite"};
		Object res = JOptionPane.showInputDialog(null, "Escolha o período" , "Período" ,
		JOptionPane.PLAIN_MESSAGE , null ,opcoes,"");
		periodo = res.toString();
			 
		Pattern pattern = Pattern.compile("([1-4]?,)*?[1-4]");
			 				 
		while(escolhaCliente==null || escolhaCliente.equals("") || !errorMsg.equals("")){
			
			if(periodo.equals(opcoes[1])){
				opcoesPedido = "[1] "+pedidosNoite[0] + "\n[2] "+pedidosNoite[1] + "\n[3] "+pedidosNoite[2] + "\n[4] "+pedidosNoite[3];
			} else {
				opcoesPedido = "[1] "+pedidosManha[0] + "\n[2] "+pedidosManha[1] + "\n[3] "+pedidosManha[2];
				pattern = Pattern.compile("([1-3]?,)*?[1-3]");
			}

			escolhaCliente = JOptionPane.showInputDialog(null, "Opções: \n" + opcoesPedido + errorMsg, "Informe os códigos da sua escolha (separados por vírgulas)", 3);

			if(!pattern.matcher(escolhaCliente).matches())
				errorMsg = "\n\nErro: Corrija a digitação.";
			else 
				errorMsg = "";
			
			if(errorMsg.equals("")){
				escolhaCliente += ","; 
						
				String[] arrayEscolha = escolhaCliente.split(",");
				Arrays.sort(arrayEscolha);
				
				for(int i=0; i < arrayEscolha.length && errorMsg.equals(""); i++){
										
					switch(Integer.parseInt(arrayEscolha[i])){
					case 1: 
						if(periodo.equals(opcoes[1]))	arrayEscolha[i]=pedidosNoite[0]; 
						else  arrayEscolha[i]=pedidosManha[0]; 
						break;
					case 2: 
						if(periodo.equals(opcoes[1]))	arrayEscolha[i]=pedidosNoite[1]; 
						else  arrayEscolha[i]=pedidosManha[1]; 
						break;
					case 3: 
						if(periodo.equals(opcoes[1]))	arrayEscolha[i]=pedidosNoite[2]; 
						else  arrayEscolha[i]=pedidosManha[2]; 
						break;
					case 4: 
						arrayEscolha[i]=pedidosNoite[3]; 
						break;
					}
					
					if(i>0){
						if(periodo.equals(opcoes[1]) && arrayEscolha[i]!=pedidosNoite[1] && arrayEscolha[i]==arrayEscolha[i-1] ||
						   periodo.equals(opcoes[0]) && arrayEscolha[i]!=pedidosManha[2] && arrayEscolha[i]==arrayEscolha[i-1]){
							errorMsg = "\n\nErro: Corrija a digitação."; break;		
						}
					}	
				}	
				escolhaCliente = Arrays.toString(arrayEscolha).replace("[","").replace("]", "");
			}			
		}		
			 
		JOptionPane.showMessageDialog(null, "Escolha:\n" + escolhaCliente);		 
			 
	}
}
