import java.awt.Graphics;
import javax.swing.ImageIcon;

public class CampoDeCultivo implements Runnable{
    private int tiempoGen;//en milisegundos
    public int rens;
    public int cols;
    public int xi;
    public int yi;
    public int anchoCelda;
    public int altoCelda;
    public Graphics area;
    public boolean [][] cultivo;
    public ImageIcon bacteria, vacio;
    public Controlador ctrl;

    CampoDeCultivo(int xi, int yi, int rens, int cols, int anchoC, int altoC, int tiempo, Graphics g, Controlador c){
      tiempoGen=tiempo;
      this.rens=rens; this.cols=cols;
      this.xi=xi; this.yi=yi;
      anchoCelda = anchoC;
      altoCelda = altoC;
      area=g;
      cultivo = new boolean[rens][cols];
      bacteria=new ImageIcon("bacteria.gif");
      vacio= new ImageIcon("vacio.jpg");
      ctrl = c;
      inicializarCampo();
    }

    public void pintarCelda(int ren, int col){
        area.drawImage(cultivo[ren][col]?bacteria.getImage():vacio.getImage() ,xi+col*(anchoCelda+1), yi+ren*(altoCelda+1), anchoCelda,altoCelda,null);
    }

    public void inicializarCampo(){
      for(int ren=0; ren<rens; ren++)
        for(int col=0; col<cols; col++)
          cultivo[ren][col]=false;
    }

    public boolean haySerVivo(int ren, int col){
      return cultivo[ren][col];
    }

    public int numVecinos(int ren, int col){
      int nVec=0;
      for(int r=ren-1; r<= ren+1; r++)
        for(int c=col-1; c<=col+1; c++){
          if ( ((r >= 0) && (r < rens)) && ((c >= 0) && (c < cols)) && ((ren != r) || (c != col)) && (haySerVivo(r,c)))
            nVec++;
        }
      return nVec;
    }

    public void run() {
        while(true){  // ejecuta siempre
          pintar();
          //Thread.currentThread().yield();
			try{
				Thread.sleep(tiempoGen);
			}catch(Exception e){}
        }
    }//metodo run()

    public void pintar(){
      int ren,col;
      boolean [][] copiaCeldas= new boolean [rens][cols];
        for(ren=0;ren<rens;ren++)
          for(col=0;col<cols;col++)
            if (!ctrl.estaDetenido()){
              int nVs=numVecinos(ren,col);
              boolean vivo = haySerVivo(ren,col);
              if (vivo && (nVs > 3 || nVs < 2) )
                copiaCeldas[ren][col]=false;
              else if ( (vivo && (nVs == 2 || nVs == 3) ) ||
                      (!vivo && (nVs == 3) ) )
                copiaCeldas[ren][col]=true;
            }
       if(!ctrl.estaDetenido()){
           cultivo=copiaCeldas;
       }
       for(ren=0;ren<rens;ren++)
          for(col=0;col<cols;col++)
            pintarCelda(ren, col);
     }

}

