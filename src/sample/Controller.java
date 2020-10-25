package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML TextField tfPregunta;
    @FXML VBox conversacion;
    boolean encontropieza=false;
    String[] MARCAS={"Chevrolet","Nissan","BMW","Subaru","Toyota","Mitsubishi","Lamborghini","Audi","Plymouth","Dodge"};
    String[] MODELOS=generarModelos(2000,2020);
    String[] PIEZAS={"Motor","Llanta","Balata","Rin","Batería","Direccional","Filtro de aire","Espejo"};
    @FXML
    protected void initialize(){
        System.out.println("Marcas: "+Arrays.toString(MARCAS));
        System.out.println("Modelos: "+Arrays.toString(MODELOS));
        System.out.println("Piezas: "+Arrays.toString(PIEZAS));
    }
    public void comandoTeclado(KeyEvent event){
        if(event.getCode()== KeyCode.ENTER){
            String pregunta=tfPregunta.getText().trim();
            String preguntaFormateada=String.format("Usuario dice: \n%s",tfPregunta.getText().trim());
            String respuesta="";
            if(encontropieza){
                int pos1marca=0,pos2marca=0;
                int pos1modelo=0,pos2modelo=0;
                int pos1pieza=0,pos2pieza=0;
                boolean encontromarca=false,encontromodelo=false,encontropz=false;
                String marca="",modelo="",pieza="";
                for(int i=0;i<MARCAS.length;i++){
                    if (pregunta.toLowerCase().contains(MARCAS[i].toLowerCase())) {
                        pos1marca=pregunta.toLowerCase().indexOf(MARCAS[i].toLowerCase());
                        pos2marca=pos1marca+MARCAS[i].length();
                        encontromarca=true;
                        break;
                    }
                }
                for(int i=0;i<MODELOS.length;i++){
                    if(pregunta.toLowerCase().contains(MODELOS[i].toLowerCase())){
                        pos1modelo=pregunta.toLowerCase().indexOf(MODELOS[i].toLowerCase());
                        pos2modelo=pos1modelo+MODELOS[i].length();
                        encontromodelo=true;
                        break;
                    }
                }
                for(int i=0;i<PIEZAS.length;i++){
                    if(pregunta.toLowerCase().contains(PIEZAS[i].toLowerCase())){
                        pos1pieza=pregunta.toLowerCase().indexOf(PIEZAS[i].toLowerCase());
                        pos2pieza=pos1pieza+PIEZAS[i].length();
                        encontropz=true;
                        break;
                    }
                }
                if(encontromarca&&encontromodelo&&encontropz){
                    respuesta=String.format("Sí tenemos la pieza %s para el auto %s %s. \nPuede pasar a visitarnos a nuestra sucursal en un horario de 9 a 5 para comprar la pieza.", pregunta.substring(pos1pieza,pos2pieza), pregunta.substring(pos1marca,pos2marca), pregunta.substring(pos1modelo,pos2modelo));
                    encontropieza=false;
                }
                else{
                    String errorMarca=(!encontromarca)?"\n\tNo se encontró la marca solicitada en mi programación.":"";
                    String errorModelo=(!encontromodelo)?"\n\tNo se encontro el modelo solicitado en mi programación.":"";
                    String errorPieza=(!encontropz)?"\n\tNo se encontro la pieza solicitada en mi programación.":"";
                    respuesta=String.format("Lo sentimos, pero no podemos obtener un resultado por las siguientes razones: %s%s%s \nPregunta lo que quieras sobre piezas; estaré escuchando. Introduce marca, modelo y nombre de la pieza.",errorMarca,errorModelo,errorPieza);
                }
            }
            else{
                if(pregunta.toLowerCase().contains("hola")){
                    respuesta=String.format("Hola, %s.", System.getProperty("user.name"));
                }
                else if(pregunta.toLowerCase().contains("adios")||pregunta.toLowerCase().contains("adiós")){
                    respuesta=String.format("Adiós, %s. Que tengas un buen día. Este programa se cerrará en 3 segundos.",System.getProperty("user.name"));
                    Timer t=new Timer();
                    t.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            System.exit(0);
                        }
                    },3000);
                }
                else if(pregunta.toLowerCase().contains("como estas")||pregunta.toLowerCase().contains("cómo estás")){
                    respuesta="Muy bien, ¿y tú? ¿Qué necesitabas?";
                }
                else if(pregunta.toLowerCase().contains("pieza")){
                    respuesta="Pregunta lo que quieras sobre piezas; estaré escuchando. Introduce marca, modelo y nombre de la pieza.";
                    encontropieza=true;
                }
                else{
                    respuesta="Disculpa. Eso no se encuentra dentro de mi programación.";
                }
            }
            String respuestaFormateada=String.format("Chatbot dice: \n%s",respuesta);
            conversacion.getChildren().add(new Label(preguntaFormateada));
            conversacion.getChildren().add(new Label(respuestaFormateada));
            tfPregunta.clear();
        }
    }
    public String[] generarModelos(int anoinicio,int anofinal){
        String[] modelos=new String[(anofinal-anoinicio)+1];
        for(int i=anoinicio;i<=anofinal;i++){
            String modelo=String.valueOf(i);
            modelos[i-anoinicio]=modelo;
        }
        return modelos;
    }
}
