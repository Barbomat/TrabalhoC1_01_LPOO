package TrabalhoC1_01_LPOO;

public class Alvo {

    private static int vida = 3;
    private int posx, posy;
    private char cor;

    public Alvo(int posx, int posy, char cor) {

        this.posx = posx;
        this.posy = posy;
        this.cor = cor;
    }

    public static int getVida() {

        return vida;
    }

    public int getPosx() {
    
        return this.posx;
    }

    public void setPosx(int posx) {
    
        this.posx = posx;
    }

    public int getPosy() {
    
        return this.posy;
    }

    public void setPosy(int posy) {
    
        this.posy = posy;
    }

    public char getCor() {
    
        return this.cor;
    }

    public void setCor(char cor) {
    
        this.cor = cor;
    }

    public String toString() {

        return "\nX: " +this.posx 
               +"\nY: " +this.posy
               +"\nCor: " +this.cor;
    }

    public static char atira(int x, int y, Alvo[][] alvo) {
    	
    	char cor = ' ';
     
        if (alvo[x-1][y-1].getCor() == 'P') {
    
            cor = 'P';
        	vida--;

        } else if (alvo[x-1][y-1].getCor() == 'B') {
    
            cor = 'B';
        	vida++;

        } else {
    
            cor = 'N';
        }
      
        return cor;
    }

} 