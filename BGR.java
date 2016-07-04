package pdi_tarea_1;

public class BGR {
    public int B, G, R, A;
    
    public BGR(){
        this.B = 0;
        this.G = 0;
        this.R = 0;
        this.A = 0;
    }
    
    public void Cargar_BGR(int B1, int G1, int R1, int A1){
        this.B = (int) B1;
        this.G = (int) G1;
        this.R = (int) R1;
        this.A = (int) A1;
    }
    
    public void Cargar_BGR(int B1, int G1, int R1){
        this.B = (int) B1;
        this.G = (int) G1;
        this.R = (int) R1;
    }
    
    public void Aplicar_negativo(){
        this.B = (int) (255-B);
        this.G = (int) (255-G);
        this.R = (int) (255-R);
        //this.A = (int) (255-A);
    }
    
    public int Retornar_B(){
        return this.B;
    }
    
    public int Retornar_G(){
        return this.G;
    }
    
    public int Retornar_R(){
        return this.R;
    }
    
    public int Retornar_A(){
        return this.A;
    }
    
}
