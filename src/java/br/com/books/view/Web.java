/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.books.view;

import br.com.books.control.sistema.Client;
import br.com.books.control.sistema.Server;
import eu.webtoolkit.jwt.*;
import eu.webtoolkit.jwt.WVBoxLayout;
import eu.webtoolkit.jwt.servlet.UploadedFile;
import eu.webtoolkit.jwt.servlet.WebRequest;
import eu.webtoolkit.jwt.servlet.WebResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Windows
 */
public class Web extends WContainerWidget implements Client {

    private Server server;
    private String client;
    private WVBoxLayout box;

    public Web(String client) {

        resize(new WLength(100, WLength.Unit.Percentage), new WLength(100, WLength.Unit.Percentage));
        this.server = new Server();
        this.client = client;

        server.connect(this);
        setLayoutSizeAware(true);
        WApplication.getInstance().enableUpdates(true);

        interPathChanged();
        this.init();
        
      
        
    }
    
    private void init() {

        box = new WVBoxLayout(getInstancia());
        box.setContentsMargins(0, 0, 0, 0);

        //envia para request url
        WApplication.getInstance().setInternalPath(Index.Path_Response.SISTEMA, false);

    }

    public Web getInstancia() {

        return this;
    }

    void interPathChanged() {

        WApplication.getInstance().internalPathChanged().addListener(WApplication.getInstance(), new Signal1.Listener<String>() {
            @Override
            public void trigger(String arg) {

                switch (arg) {

                    case Index.Path_Response.SISTEMA:

                        break;

                    default:
                        System.out.println("Erro page not found");
                        break;

                }// end switch
            }
        });
    }

    @Override
    public void run() {

        WApplication.getInstance().triggerUpdate();
    }

    /**
     * @return the server
     */
    public Server getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(Server server) {
        this.server = server;
    }

    /**
     * @return the client
     */
    public String getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(String client) {
        this.client = client;
    }

    @Override
    protected void layoutSizeChanged(int width, int height) {
        super.layoutSizeChanged(width, height);

    }

    private void importFile(WVBoxLayout boxV) {

        final WFileUpload fu = new WFileUpload();
        fu.setId("btinput");
        fu.resize(100, 80);
        fu.setFileTextSize(50);
        fu.setProgressBar(new WProgressBar());

        fu.changed().addListener(this, new Signal.Listener() {
            public void trigger() {
                fu.upload();

            }
        });

        fu.uploaded().addListener(this, new Signal.Listener() {
            public void trigger() {

                List<UploadedFile> load = fu.getUploadedFiles();

                for (UploadedFile uploadedFile : load) {

                    System.out.println(uploadedFile.getClientFileName() + " : " + uploadedFile.getSpoolFileName());
                }

            }
        });
        fu.fileTooLarge().addListener(this, new Signal.Listener() {
            public void trigger() {
            }
        });
        
        boxV.addWidget(fu);
        
        for (int i = 0; i < fu.getChildren().size(); i++) {
            
            System.out.println("Children: "+fu.getChildren().get(i).getId());
        }

    }
    
}
