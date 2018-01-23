public class Controlador{
    private boolean detenido;
    
    public Controlador(boolean d){
        detenido=d;
    }
    
    public void actualizar (boolean d){
        detenido=d;
    }
    
    public boolean estaDetenido(){
        return detenido;
    }
}