package org.unitec.elementos1801;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Elementos1801Application implements CommandLineRunner{

    
@Autowired RepositorioMensajito repoMensa;
@Autowired RepositorioUsuario repoUsu;
@Autowired RepositorioDireccion repoDir;


	public static void main(String[] args) {
		SpringApplication.run(Elementos1801Application.class, args);
	}
        
      @Override

      public void run(String... strings) throws Exception {
          
          
          //vamos a generar un usuario
          
          Usuario u= new Usuario(1521L, "Viridiana Hernandez","ndzmviridiana@hotmail.com");
          
          //la guardamos
          
         // repoUsu.save(u);
          
          //GENERAMOS LA DIRECCION QUE VAMOS A GUARDAR
          
         Direccion d = new Direccion(new Usuario(1521L),"Calle 13", 55120, "Ecatepec");
         //repoDir.save(d);
         
         
         //AQUI HAREMOS EL JOIN
         
         
        Direccion d2= repoDir.findOne(2L);
          System.out.println("Correo:"+d2.getU().getEmail()+ " municipio "+d2.getMunicipio());
          
          
          
          
          
          
        //repoMensa.save (new Mensajito("Primero","Mi primera vez con hibernate"))
        /*
        Mensajito m= repoMensa.findOne(1);
        System.out.println(m.getTitulo());
        
        
        
       // repoMensa.save(new Mensajito("17 de octubre","No temblo"));
        System.out.println("vamos a uscar todos");
        
        for (Mensajito mensa:repoMensa.findAll()){
        System.out.println(mensa);
        
        }
        
        //para buscar por id FINDONE
        System.out.println("vamos a buscar por id");
        System.out.println(repoMensa.findOne(1));
       
       
        //  Actualizar 
        repoMensa.save(new Mensajito(1,"nuevo titulo","nuevo cuerpo"));
        System.out.println(repoMensa.findOne(1));
*/
    }
        
}
