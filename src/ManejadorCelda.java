
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedList;

public class ManejadorCelda implements MouseListener{
    LinkedList<CampoDeCultivo> campos;
    
    public ManejadorCelda (CampoDeCultivo... cs){
        campos= new LinkedList<CampoDeCultivo>();
        campos.addAll(Arrays.asList(cs));
    }
    
    public CampoDeCultivo enCual(int x, int y){
        CampoDeCultivo campo=null;
        for (CampoDeCultivo c:campos)
            if((x>=c.xi) && (x<=(c.xi + (c.cols * (c.anchoCelda + 1)) ) ) &&
                   (y >= c.yi) && (y<=(c.yi + (c.rens * (c.altoCelda + 1)) ) ) ) campo=c;
    return campo;
    }
    
    public void mouseClicked(MouseEvent e){
        int r, c, x=e.getX(),y=e.getY();
        CampoDeCultivo campo=enCual(x,y);
        if (campo!=null){
            r=(y-campo.yi)/(campo.altoCelda + 1);
            c=(x-campo.xi)/(campo.anchoCelda + 1);
            
           if (campo.haySerVivo(r, c)) campo.cultivo [r][c]=false; else campo.cultivo [r][c]= true;
           campo.pintarCelda(r, c);
        }
        
    }
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
}