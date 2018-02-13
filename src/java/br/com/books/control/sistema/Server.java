/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.books.control.sistema;

import br.com.books.control.sistema.Client;
import br.com.books.view.Index;
import eu.webtoolkit.jwt.WApplication;
import eu.webtoolkit.jwt.WtServlet;

/**
 *
 * @author Windows
 */
public class Server implements Runnable{
    
    private Conexao conn;
    
    public void connect(Client client){
        
       this.conn = new Conexao(client,WApplication.getInstance().getSessionId());
    }
    
    public void postAll(){
        
    WtServlet server = WtServlet.getInstance();

    /* This is where we notify all connected clients. */
    for (int i = 0; i < Sistema.clientes.size(); i++) {

        Conexao c = Sistema.clientes.get(i);
        Client client = c.getClient();

        server.post(Sistema.application.get(i), client,this);
    }
    
}
  @Override
    public void run() {

        System.out.println("Expirou a sessao: "+WApplication.getInstance().getSessionId());

    }
  
}