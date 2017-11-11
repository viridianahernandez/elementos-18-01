/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unitec.elementos1801;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import static com.vaadin.ui.themes.ValoTheme.LABEL_H2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author elect
 */
@SpringUI
@Theme("valo")
public class MiUI extends UI {
    
    @Autowired RepositorioMensajito repoMensa; 

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout= new VerticalLayout();
         
         //Generar una etiqueta
         Label etiqueta= new Label("Aplicacion con mensajitos bonitos");
         etiqueta.addStyleNames(ValoTheme.LABEL_H1);
         layout.addComponent(etiqueta);
         setContent(layout);
         
         Label etiquetaGuardar= new Label("Aplicacion con mensajitos bonitos");
         etiquetaGuardar.addStyleName(ValoTheme.LABEL_H2);
         TextField textoTitulo=new TextField();
         //
         textoTitulo.setPlaceholder("Escribe el titulo");
         TextArea textoCuerpo=new TextArea();
         
         textoCuerpo.setPlaceholder("Escribe el cuerpo del mensaje");
         Button boton=new Button("Guardar el mensaje");
         
         //Manejamos el evento del boton
         boton.addClickListener(evento->{
         
             if(textoTitulo.getValue().equals("")||textoTitulo.getValue().equals("")){
                 
             Notification.show("Se requieren los campos",Notification.TYPE_ERROR_MESSAGE);
             }
             else
             {
               Notification.show("Se guardo el mensaje",Notification.TYPE_ERROR_MESSAGE);
                    repoMensa.save(new Mensajito(textoTitulo.getValue(),textoCuerpo.getValue()));
             }
         });
         // Have some data


        // Create a grid bound to the list
        Grid<Mensajito> grid = new Grid<>();
        grid.setItems((List)repoMensa.findAll());
        grid.addColumn(Mensajito::getId).setCaption("Id del mensaje");
        grid.addColumn(Mensajito::getTitulo).setCaption("Titulo del mensaje");
        grid.addColumn(Mensajito::getCuerpo).setCaption("Cuerpo del mensaje");


         //Agregamos el layout todo
         
         layout.addComponent(etiqueta);
         layout.addComponent(etiquetaGuardar);
         layout.addComponent(textoTitulo);
         layout.addComponent(textoCuerpo);
         layout.addComponent(boton);
         layout.addComponent(grid);
     
         
         
     
         //PRIMERO CREAMOS UN HORIZONTAL LAYOUT
         HorizontalLayout layout1=new HorizontalLayout(); 
         TextField textoId=new TextField();
         textoId.setPlaceholder("introduce el id");
         Button botonBuscarId=new Button("Buscar");
         botonBuscarId.addStyleName(ValoTheme.BUTTON_PRIMARY);
         
         //VAMOS A BUSCAR POR ID
         
       
         
         layout1.addComponent(textoId);
         layout1.addComponent(botonBuscarId);
         
         layout.addComponent(layout1);
         
         
         //CREAMOS OTRO LAYOUT PARA LOS CAMPOS DE TEXTO
         HorizontalLayout layout2=new HorizontalLayout();
         TextField textoBuscarId=new TextField();
         TextField textoBuscarTitulo=new TextField();
         TextArea textoBuscarCuerpo=new TextArea();
         layout2.addComponent(textoBuscarId);
         layout2.addComponent(textoBuscarTitulo);
         layout2.addComponent(textoBuscarCuerpo);
         layout.addComponent(layout2);
        
         Button botonActualizar=new Button("Actualizar");
         botonActualizar.addStyleName(ValoTheme.BUTTON_FRIENDLY);
         layout.addComponent(botonActualizar);
         
         setContent(layout); 
         botonBuscarId.addClickListener(evento->{
         
          Mensajito mensa= repoMensa.findOne(Integer.parseInt(textoId.getValue()));
        
        //AJUSTAMOS LOS TRES CAMPOS CON LOS DATOS
        //PRIMERO EL ID
        
        textoBuscarId.setValue(""+mensa.getId());
        textoBuscarTitulo.setValue(mensa.getTitulo());
        textoBuscarCuerpo.setValue(mensa.getCuerpo());
             
        
             
         });
       
                
    }//CIERRE DEL MTODO INIT
    }//CIERRE DE LA CLASE
    
    
    

