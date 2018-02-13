/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.books.control.sistema;

import br.com.books.control.sistema.Conexao;
import eu.webtoolkit.jwt.WApplication;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Sistema {
    
    public static List<Conexao> clientes = new ArrayList<>();
    public static List<WApplication> application = new ArrayList<>();
}
