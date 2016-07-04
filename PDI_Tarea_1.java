package pdi_tarea_1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.Math.pow;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PDI_Tarea_1 {
    
    // ATRIBUTOS 
    public static byte [] Pixeles_Min;
    public static BGR [] Paleta_BGR;
    public static int [] [] Pixeles;
    public static BGR [] [] Matriz_BGR;
    public static BGR [] [] Matriz_BGR_auxi;
    public static int width, height;
    public static String nombre_final;
    public static byte [] byte_array; 
    public static BGR color_24, opcion;
    public static int off_set;
    public static int num_bits;
    public static BGR [] Pixel_aux;
    public static int Padding_min;
    
    // FUNCION PRINCIPAL
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, FileNotFoundException, IOException, Throwable {
        
        Interfaz_inicio hola = new Interfaz_inicio();
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        hola.getContentPane().setBackground((new java.awt.Color(153, 204, 255)));
        hola.setVisible(true);
        hola.parada = false;
        String nombre_archi;
        
        while (true){
            if (hola.parada){
                nombre_archi = hola.valor_text;
                hola.dispose();
                break;
            }
        }
      
        // PARA LOGRAR LA BUSQUEDA DEL ARCHIVO, TODAS LAS IMAGENES DEBEN ESTAR EN MI CARPETA RAIZ
        File dir = new File("test");
        String [] ficheros = dir.list();
        
        if (ficheros==null){
            System.out.println("La carpeta test se encuentra vacía");
            Directorio_vacio hola3 = new Directorio_vacio();
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            hola3.getContentPane().setBackground((new java.awt.Color(153, 204, 255)));
            hola3.setVisible(true);
            
        } else {
            ArrayList <String> archivos = new ArrayList();
            String aux;
            //String ext;
            Boolean parada;
            
            for (int i=0; i<ficheros.length; i++){
                aux = ficheros[i];
                parada = Comparar_extension(aux);
                
                if (parada){
                    archivos.add(aux);
                }
            }
            
            boolean encontrado = false;
            String archi_fin = null;
            
            for (int j=0; j<archivos.size(); j++){
                
                if ((nombre_archi+".bmp").equals(archivos.get(j))){
                    System.out.println("El archivo pedido es: "+archivos.get(j));
                    archi_fin = archivos.get(j);
                    encontrado = true;
                    break;
                }
            }
            
            if (encontrado){
                
                // LECTURA DEL ARCHIVO BINARIO .BMP
                FileInputStream archi_bin = null;
                archi_bin = new FileInputStream("test/"+archi_fin);
                BufferedInputStream buffer = new BufferedInputStream(archi_bin);
                DataInputStream datos = new DataInputStream(buffer);
                nombre_final = archi_fin;
                // LEO LOS PRIMEROS 14 BYTES 
                byte_array = new byte[54];
                byte [] b2 = new byte[54];
                datos.read(b2);
                
                int [] b = new int[54];
                //byte_array = b;
                int help;
                // Libero todos aquellos que fueron sentenciados por su signo
                for (int i=0; i<b2.length; i++){
                    byte_array[i] = b2[i];
                    help = (int) b2[i];
                    
                    System.out.println(help);
                    if (help<0){
                        //System.out.println("Es negativo");
                        help =(short) (help+256);
                        b[i] = (int) help;
                        //System.out.println(help);
                    } else {
                        b[i] = (int) help;
                    }
                }
               
                short type;
                int size;
                short reserved1;
                short reserved2;
                int offset;
                
                type = (short) ((b[1]<<8)+b[0]);
                System.out.println("Tipo: "+type);
                
                size = (int) ((((((b[5]<<8)+b[4])<<8)+b[3])<<8)+b[2]);
                System.out.println("Tamaño de archivo: "+size);
                
                reserved1 = (short) ((b[7]<<8)+b[6]);
                System.out.println("Reservado1: "+reserved1);
                
                reserved2 = (short) ((b[9]<<8)+b[8]);
                System.out.println("Reservado2: "+reserved2);
                
                offset = (int) ((((((b[13]<<8)+b[12])<<8)+b[11])<<8)+b[10]);
                System.out.println("Offset: "+offset);
                
                off_set = offset;
                // LEO LOS SIGUIENTES 40 BYTES 
                
                int size_i;
                short planes;
                short bits;
                int compression;
                int imagesize;
                int xresol;
                int yresol;
                int ncolours;
                int importantcolours;
                
                size_i = (int) ((((((b[17]<<8)+b[16])<<8)+b[15])<<8)+b[14]);
                System.out.println("Tamaño: "+size_i);
                
                width = (int) ((((((b[21]<<8)+b[20])<<8)+b[19])<<8)+b[18]);
                System.out.println("Width: "+width);
                
                height = (int) ((((((b[25]<<8)+b[24])<<8)+b[23])<<8)+b[22]);
              
                System.out.println("Height: "+height);
                
                height = (((int)b[25]&0xff)<<24) | (((int)b[24]&0xff)<<16) | (((int)b[23]&0xff)<<8) | (int)b[22]&0xff;
                System.out.println("Height: "+height);
                planes = (short) ((b[27]<<8)+b[26]);
                System.out.println("Planes: "+planes);
                        
                bits = (short) ((b[29]<<8)+b[28]);
                num_bits = bits;
                //bits = (short) ((((int)b[29]&0xff)<<8) | (int)b[28]&0xff);
                System.out.println("Bits: "+bits);
                
                compression = (int) ((((((b[33]<<8)+b[32])<<8)+b[31])<<8)+b[30]);
                System.out.println("Compresion: "+compression);
                
                imagesize = (int) ((((((b[37]<<8)+b[36])<<8)+b[35])<<8)+b[34]);
                System.out.println("Tamaño de Imagen: "+imagesize);
                
                xresol = (int) ((((((b[41]<<8)+b[40])<<8)+b[39])<<8)+b[38]);
                System.out.println("Resol X: "+xresol);
                
                yresol = (int) ((((((b[45]<<8)+b[44])<<8)+b[43])<<8)+b[42]);
                System.out.println("Resol Y: "+yresol);
                
                ncolours = (int) ((((((b[49]<<8)+b[48])<<8)+b[47])<<8)+b[46]);
                System.out.println("N Colours: "+ncolours);
                
                importantcolours = (int) ((((((b[53]<<8)+b[52])<<8)+b[51])<<8)+b[50]);
                System.out.println("Important Colours: "+importantcolours);
                
                // Luego de leer el header y el infoheader, queda determinar que tipo de imagen es
                
                if (bits>=24){
                    // LA IMAGEN NO TIENE PALETA DE COLORES
                    System.out.println("La imagen es de 24 bits");
                    int B_aux, G_aux, R_aux;
                    
                    Matriz_BGR = new BGR [height] [width];
                    color_24 = new BGR();
                    //Declaro mi matriz donde almacenaré mi imagen
                    
                    Interfaz_modificacion hola2 = new Interfaz_modificacion();
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    hola2.getContentPane().setBackground((new java.awt.Color(153, 204, 255)));
                    
                    for (int kj=0; kj<height; kj++){
                        for (int ki=0; ki<width; ki++){
                            color_24 = new BGR();
                            B_aux = (int) datos.read(); // Blue
                            G_aux = (int) datos.read(); // Green
                            R_aux = (int) datos.read(); // Red
                            color_24.Cargar_BGR(B_aux, G_aux, R_aux);
                            Matriz_BGR[kj][ki] = color_24;
                          
                        }
                    }

                    // HASTA AQUI YA TENGO TODO LO REFERENTE A MI IMAGEN DE 24 BITS EN MIS MANOS
                    // AHORA SOLO ME QUEDA INVOCAR LA INTERFAZ DE MODIFICACION DE IMAGEN
                    datos.close();
                    buffer.close();
                    archi_bin.close();
                    
                    hola2.setVisible(true);     
                    hola2.parada=-2;
                    while (hola2.parada<=0){
                        
                    }
       
                    System.exit(0);
                    
                    
                } else {
                    // LA IMAGEN POSEE PALETA DE COLORES
                    System.out.println("La imagen es menor de 24 bits");
                    System.out.println("El numero de bits es: "+bits);
                    
                    int tam_palette = (int) (4*pow(2,bits));
                    System.out.println("El tamaño de la paleta es: "+tam_palette);
                    
                    int B_aux, G_aux, R_aux, A_aux;
                    Matriz_BGR = new BGR [height] [width];
                    Pixeles = new int [height][width];
                    Paleta_BGR = new BGR [tam_palette];
                    
                    BGR color_menor;
                    
                    for (int iter=0; iter<tam_palette; iter++){
                        color_menor = new BGR();
                        B_aux = (int) datos.read(); // Blue
                        G_aux = (int) datos.read(); // Green
                        R_aux = (int) datos.read(); // Red
                        A_aux = (int) datos.read(); // Reservado Alpha
                        color_menor.Cargar_BGR(B_aux, G_aux, R_aux, A_aux);
                        Paleta_BGR[iter] = color_menor;
                    }
                    
                    System.out.println("Ancho: "+width+" Alto: "+height);
                    int pixel, pixel2;
                    
                    if (num_bits==1){
                        
                        Padding_min = 0;
                        if ((width/8)%4!=0){
                            Padding_min = 4-((width/8)%4);
                        }
                        Pixeles_Min = new byte[(width*height)/8];   
                        datos.read(Pixeles_Min);
                      
                    } else if(num_bits==4){
                        
                        Padding_min = 0;
                        if ((width/2)%4!=0){
                            Padding_min = 4-((width/2)%4);
                        }
                        Pixeles_Min = new byte[(width*height)/2];   
                        datos.read(Pixeles_Min);
                        
                    } else if(num_bits==8){
                        
                        /*Padding_min = 0;
                        if ((width)%4!=0){
                            Padding_min = 4-((width)%4);
                        }*/
                        
                        Padding_min =0;
                        if ((width)%4!=0){
                            Padding_min = ((((width*num_bits)+31) & ~31)>>3)-width;
                        }
                        System.out.println(Padding_min);
                        for (int kj=0; kj<height; kj++){
                            for (int ki=0; ki<width; ki++){
                                pixel = datos.read();
                                //System.out.println(pixel);
                                Pixeles[kj][ki] = pixel;
                            }
                        }
                        
                    } else if(num_bits==16){
                        
                        Padding_min = 0;
                        if ((width*2)%4!=0){
                            Padding_min = 4-((width*2)%4);
                        }
                        for (int kj=0; kj<height; kj++){
                            for (int ki=0; ki<width; ki++){
                                pixel = datos.read();
                                pixel2 = datos.read();
                                pixel2 = (pixel<<8)+pixel2;
                                Pixeles[kj][ki] = pixel2;
                            }
                        } 
                    }
                    
                    datos.close();
                    buffer.close();
                    archi_bin.close();
                    
                    Interfaz_modificacion hola2 = new Interfaz_modificacion();
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    hola2.getContentPane().setBackground((new java.awt.Color(153, 204, 255)));
                    
                    hola2.setVisible(true);     
                    // Envio una señal a la interfaz de que es una imagen menor a 24
                    
                    hola2.parada=-1;
                    while (hola2.parada<=0){
                        
                    }
                    
                     System.exit(0);
                    
                }
            } else {
                Error_archivo hola4 = new Error_archivo();
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                hola4.getContentPane().setBackground((new java.awt.Color(153, 204, 255)));
                hola4.setVisible(true);     
            }
            
        }
        
    }
    
    private static boolean Comparar_extension(String aux) {
        // FUNCION QUE ME AYUDA A DETERMINAR SI ES UN ARCHIVO .BMP O NO
        boolean retorno = false;
        char lector;
        lector = aux.charAt(aux.length()-1);
        
        if (lector=='p'){
            lector = aux.charAt(aux.length()-2);
            if (lector=='m'){
                lector = aux.charAt(aux.length()-3);
                if(lector=='b'){
                    lector = aux.charAt(aux.length()-4);
                    if (lector=='.'){
                        retorno = true;
                    } else {
                        retorno = false;
                    }
                }
            }
        }
        return retorno;
    }

    // ####################### OPERACIONES SOBRE LA IMAGEN ##########################################
    
    //  LISTA
    static void Aplicar_Negativo(int num) throws FileNotFoundException, IOException{
        System.out.println("Aplico funcion Negativo");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_neg = new FileOutputStream("Negativo_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_neg = new BufferedOutputStream(copia_neg);
        DataOutputStream datos_neg = new DataOutputStream(buffer_neg);
        
        int Padding = 0;
        if ((width*3)%4!=0){
             Padding = 4-((width*3)%4);
        }
        
        datos_neg.write(byte_array);
        opcion = new BGR();
        
        int B_neg, G_neg, R_neg;
        
        for (int kj2=0; kj2<height; kj2++){
            for (int ki2=0; ki2<width; ki2++){
                opcion = Matriz_BGR[kj2][ki2];    
                opcion.Aplicar_negativo();
                B_neg = (int) opcion.Retornar_B();
                datos_neg.write(B_neg);
                G_neg = (int) opcion.Retornar_G();
                datos_neg.writeByte(G_neg);
                R_neg = (int) opcion.Retornar_R();
                datos_neg.write(R_neg);               
            }
            for (int h=0; h<Padding; h++){
                datos_neg.write(0);
            }
        }
        datos_neg.flush();
        datos_neg.close();
        buffer_neg.flush();
        buffer_neg.close();
        copia_neg.close();  
    }
    
    //  LISTA
    static void Aplicar_Flip_Hor(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion Flip Horizontal");
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_flip_hor = new FileOutputStream("Flip_Horizontal_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_flip_hor = new BufferedOutputStream(copia_flip_hor);
        DataOutputStream datos_flip_hor = new DataOutputStream(buffer_flip_hor);
        
        int Padding = 0;
        if ((width*3)%4!=0){
             Padding = 4-((width*3)%4);
        }
        
        datos_flip_hor.write(byte_array);
        opcion = new BGR();
        int B_flip_hor, G_flip_hor, R_flip_hor;
        BGR [] Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        
        for (int kj2=0; kj2<height; kj2++){
            BGR [] Pixeles = new BGR [width];
            for (int ki2=0; ki2<width; ki2++){
                opcion = Matriz_BGR[kj2][ki2];   
                Pixeles[ki2] = opcion;
            }
            contenedor.push(Pixeles);   
        }
        
        for (int kjj2=0; kjj2<height; kjj2++){
            Pixel_aux = contenedor.pop();
            for (int kii2=0; kii2<width; kii2++){
                Matriz_BGR[kjj2][kii2] = Pixel_aux[kii2];  
            }
        }
       
        for (int kjjj2=0; kjjj2<height; kjjj2++){
            for (int kiii2=0; kiii2<width; kiii2++){
                opcion = Matriz_BGR[kjjj2][kiii2];                  
                B_flip_hor = (int) opcion.Retornar_B();               
                datos_flip_hor.write(B_flip_hor);              
                G_flip_hor = (int) opcion.Retornar_G();             
                datos_flip_hor.write(G_flip_hor);           
                R_flip_hor = (int) opcion.Retornar_R();
                datos_flip_hor.write(R_flip_hor);               
            }
            for (int h=0; h<Padding; h++){
                datos_flip_hor.write(0);
            }
        }
        datos_flip_hor.flush();
        datos_flip_hor.close();
        buffer_flip_hor.flush();
        buffer_flip_hor.close();
        copia_flip_hor.close();   
    }

    //  LISTA
    static void Aplicar_Flip_Ver(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion Flip Vertical");
        
        int Padding = 0;
        if ((width*3)%4!=0){
             Padding = 4-((width*3)%4);
        }
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_flip_ver = new FileOutputStream("Flip_Vertical_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_flip_ver = new BufferedOutputStream(copia_flip_ver);
        DataOutputStream datos_flip_ver = new DataOutputStream(buffer_flip_ver);
        
        datos_flip_ver.write(byte_array);
        opcion = new BGR();
        int B_flip_ver, G_flip_ver, R_flip_ver;
        BGR [] Pixel_aux = new BGR [height];
        Stack <BGR[]> contenedor = new Stack();
        
        for (int kj2=0; kj2<width; kj2++){
            BGR [] Pixeles = new BGR [height];
            for (int ki2=0; ki2<height; ki2++){
                opcion = Matriz_BGR[ki2][kj2];   
                Pixeles[ki2] = opcion;
            }
            contenedor.push(Pixeles);   
        }
        
        for (int kjj2=0; kjj2<width; kjj2++){
            Pixel_aux = contenedor.pop();
            
            for (int kii2=0; kii2<height; kii2++){
                Matriz_BGR[kii2][kjj2] = Pixel_aux[kii2];  
            }
        }
       
        for (int kjjj2=0; kjjj2<height; kjjj2++){
            for (int kiii2=0; kiii2<width; kiii2++){
                opcion = Matriz_BGR[kjjj2][kiii2];    
                B_flip_ver = (int) opcion.Retornar_B();
                datos_flip_ver.write(B_flip_ver);
                G_flip_ver = (int) opcion.Retornar_G();
                datos_flip_ver.write(G_flip_ver);
                R_flip_ver = (int) opcion.Retornar_R();
                datos_flip_ver.write(R_flip_ver);               
            }
            for (int h=0; h<Padding; h++){
                datos_flip_ver.write(0);
            }
        }
        datos_flip_ver.flush();
        datos_flip_ver.close();
        buffer_flip_ver.flush();
        buffer_flip_ver.close();
        copia_flip_ver.close(); 
        
    }
    
    // LISTA
    static void Girar_CCW_180(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion CCW 180");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_girar_ccw180 = new FileOutputStream("Girar_180_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_girar_ccw180 = new BufferedOutputStream(copia_girar_ccw180);
        DataOutputStream datos_girar_ccw180 = new DataOutputStream(buffer_girar_ccw180);
        
        int Padding = 0;
        if ((width*3)%4!=0){
             Padding = 4-((width*3)%4);
        }
        
        datos_girar_ccw180.write(byte_array);
        
        // #################### APLICO FLIP HORIZONTAL ##########################################################
        opcion = new BGR();
        BGR [] Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        
        for (int kj2=0; kj2<height; kj2++){
            BGR [] Pixeles = new BGR [width];
            for (int ki2=0; ki2<width; ki2++){
                opcion = Matriz_BGR[kj2][ki2];   
                Pixeles[ki2] = opcion;
            }
            contenedor.push(Pixeles);   
        }
        
        for (int kjj2=0; kjj2<height; kjj2++){
            Pixel_aux = contenedor.pop();
            for (int kii2=0; kii2<width; kii2++){
                Matriz_BGR[kjj2][kii2] = Pixel_aux[kii2];
            }
        }
        
        // #################### FIN APLICO FLIP HORIZONTAL ##########################################################
        
        
        // #################### APLICO FLIP VERTICAL ##########################################################
        int B_flip_ver, G_flip_ver, R_flip_ver;
        BGR [] Pixel_aux2 = new BGR [height];
        Stack <BGR[]> contenedor2 = new Stack();
        
        for (int kj22=0; kj22<width; kj22++){
            BGR [] Pixeles2 = new BGR [height];
            for (int ki22=0; ki22<height; ki22++){
                opcion = Matriz_BGR[ki22][kj22];   
                Pixeles2[ki22] = opcion;
            }
            contenedor2.push(Pixeles2);   
        }
        
        for (int kjj22=0; kjj22<width; kjj22++){
            Pixel_aux2 = contenedor2.pop();
            
            for (int kii22=0; kii22<height; kii22++){
                Matriz_BGR[kii22][kjj22] = Pixel_aux2[kii22];
            }
        }
        // #################### APLICO FLIP VERTICAL ##########################################################
        
        for (int kjjj22=0; kjjj22<height; kjjj22++){
            for (int kiii22=0; kiii22<width; kiii22++){
                opcion = Matriz_BGR[kjjj22][kiii22];    
                B_flip_ver = (int) opcion.Retornar_B();
                datos_girar_ccw180.write(B_flip_ver);
                G_flip_ver = (int) opcion.Retornar_G();
                datos_girar_ccw180.write(G_flip_ver);
                R_flip_ver = (int) opcion.Retornar_R();
                datos_girar_ccw180.write(R_flip_ver);               
            }
            for (int h=0; h<Padding; h++){
                datos_girar_ccw180.write(0);
            }
        }
        datos_girar_ccw180.flush();
        datos_girar_ccw180.close();
        buffer_girar_ccw180.flush();
        buffer_girar_ccw180.close();
        copia_girar_ccw180.close(); 
    }
    
     //LISTA
    static void Girar_CW_180(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion CW 180");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_girar_cw180 = new FileOutputStream("Girar_180_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_girar_cw180 = new BufferedOutputStream(copia_girar_cw180);
        DataOutputStream datos_girar_cw180 = new DataOutputStream(buffer_girar_cw180);
        
        datos_girar_cw180.write(byte_array);
        
        int Padding = 0;
        if ((width*3)%4!=0){
             Padding = 4-((width*3)%4);
        }
        // #################### APLICO FLIP HORIZONTAL ##########################################################
        opcion = new BGR();
        BGR [] Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        
        for (int kj2=0; kj2<height; kj2++){
            BGR [] Pixeles = new BGR [width];
            for (int ki2=0; ki2<width; ki2++){
                opcion = Matriz_BGR[kj2][ki2];   
                Pixeles[ki2] = opcion;
            }
            contenedor.push(Pixeles);   
        }
        
        for (int kjj2=0; kjj2<height; kjj2++){
            Pixel_aux = contenedor.pop();
            for (int kii2=0; kii2<width; kii2++){
                Matriz_BGR[kjj2][kii2] = Pixel_aux[kii2];
            }
        }
        
        // #################### FIN APLICO FLIP HORIZONTAL ##########################################################
        
        
        // #################### APLICO FLIP VERTICAL ##########################################################
        int B_flip_ver, G_flip_ver, R_flip_ver;
        BGR [] Pixel_aux2 = new BGR [height];
        Stack <BGR[]> contenedor2 = new Stack();
        
        for (int kj22=0; kj22<width; kj22++){
            BGR [] Pixeles2 = new BGR [height];
            for (int ki22=0; ki22<height; ki22++){
                opcion = Matriz_BGR[ki22][kj22];   
                Pixeles2[ki22] = opcion;
            }
            contenedor2.push(Pixeles2);   
        }
        
        for (int kjj22=0; kjj22<width; kjj22++){
            Pixel_aux2 = contenedor2.pop();
            
            for (int kii22=0; kii22<height; kii22++){
                Matriz_BGR[kii22][kjj22] = Pixel_aux2[kii22];
            }
        }
        // #################### APLICO FLIP VERTICAL ##########################################################
        
        for (int kjjj22=0; kjjj22<height; kjjj22++){
            for (int kiii22=0; kiii22<width; kiii22++){
                opcion = Matriz_BGR[kjjj22][kiii22];    
                B_flip_ver = (int) opcion.Retornar_B();
                datos_girar_cw180.write(B_flip_ver);
                G_flip_ver = (int) opcion.Retornar_G();
                datos_girar_cw180.write(G_flip_ver);
                R_flip_ver = (int) opcion.Retornar_R();
                datos_girar_cw180.write(R_flip_ver);               
            }
            for (int h=0; h<Padding; h++){
                datos_girar_cw180.write(0);
            }
        }
        datos_girar_cw180.flush();
        datos_girar_cw180.close();
        buffer_girar_cw180.flush();
        buffer_girar_cw180.close();
        copia_girar_cw180.close(); 
    }
    
    // LISTA
    static void Aplicar_Negativo_Min(int num) throws IOException {
        System.out.println("Aplico funcion Negativo Imagen menor a 24");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_neg = new FileOutputStream("NegativoMin_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_neg = new BufferedOutputStream(copia_neg);
        DataOutputStream datos_neg = new DataOutputStream(buffer_neg);
        datos_neg.write(byte_array);
        int longitud = Paleta_BGR.length;
        
        int Padding = 0;
        if ((width*num_bits)%4!=0){
             Padding = 4-((width*num_bits)%4);
        }
        
        for (int h=0; h<longitud; h++){
            Paleta_BGR[h].Aplicar_negativo();
            datos_neg.write(Paleta_BGR[h].Retornar_B());
            datos_neg.write(Paleta_BGR[h].Retornar_G());
            datos_neg.write(Paleta_BGR[h].Retornar_R());
            datos_neg.write(Paleta_BGR[h].Retornar_A());
        }
        
        if (num_bits==1){
            
            for (int h2=0; h2<Pixeles_Min.length; h2++){
                datos_neg.write(Pixeles_Min[h2]);
            }
            
        } else if (num_bits==2){
            
            for (int h2=0; h2<Pixeles_Min.length; h2++){
                datos_neg.write(Pixeles_Min[h2]);
            }
            
        } else if (num_bits==4){
            
            for (int h2=0; h2<Pixeles_Min.length; h2++){
                datos_neg.write(Pixeles_Min[h2]);
            }
            
        } else if (num_bits==8){
            
            for (int h1=0; h1<height; h1++){
                for (int h2=0; h2<width; h2++){
                    datos_neg.write(Pixeles[h1][h2]);
                }
                for (int h=0; h<Padding; h++){
                    datos_neg.write(0);
                }
            }
            
        } else if (num_bits==16){
            for (int h1=0; h1<height; h1++){
                for (int h2=0; h2<width; h2++){
                    datos_neg.write(Pixeles[h1][h2]);
                }
                for (int h=0; h<Padding; h++){
                    datos_neg.write(0);
                }
            }
        }
        
        datos_neg.flush();
        datos_neg.close();
        buffer_neg.flush();
        buffer_neg.close();
        copia_neg.close();
    }

    // LISTA
    static void Girar_CCW_90(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion CCW 90");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_ccw90 = new FileOutputStream("CCW_90_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_ccw90 = new BufferedOutputStream(copia_ccw90);
        DataOutputStream datos_ccw90 = new DataOutputStream(buffer_ccw90);
        
        byte mi_aux;
        
        mi_aux = byte_array[21];
        byte_array[21] = byte_array[25];
        byte_array[25] = mi_aux;
        
        mi_aux = byte_array[20];
        byte_array[20] = byte_array[24];
        byte_array[24] = mi_aux;
        
        mi_aux = byte_array[19];
        byte_array[19] = byte_array[23];
        byte_array[23] = mi_aux;
        
        mi_aux = byte_array[18];
        byte_array[18] = byte_array[22];
        byte_array[22] = mi_aux;
  
        datos_ccw90.write(byte_array);
        
        Matriz_BGR_auxi = new BGR [width][height];
        
        int Padding = 0;
        if ((height*3)%4!=0){
             Padding = 4-((height*3)%4);
        }
        
        int B_ccw90, G_ccw90, R_ccw90;
        Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        contenedor.clear();
        
        for (int q4=0; q4<height; q4++){
            Pixel_aux = new BGR[width]; 
            for (int q5=0; q5<width; q5++){
                Pixel_aux[q5] = Matriz_BGR[q4][q5];
            }
            contenedor.push(Pixel_aux);
        }
        
        for (int z=0; z<height; z++){
            Pixel_aux = contenedor.pop();
            for (int z2=0; z2<width; z2++){
                Matriz_BGR_auxi[z2][z] = Pixel_aux[z2];
            }
        }
        
        opcion = new BGR();
        for (int z=0; z<width; z++){
            for (int z2=0; z2<height; z2++){              
                opcion = Matriz_BGR_auxi[z][z2];    
                
                B_ccw90 = (byte) opcion.Retornar_B();
                datos_ccw90.write(B_ccw90);
               
                G_ccw90 = (byte) opcion.Retornar_G();
                datos_ccw90.write(G_ccw90);
                
                R_ccw90 = (byte) opcion.Retornar_R();
                datos_ccw90.write(R_ccw90);   
            }
            
            for (int h=0; h<Padding; h++){
                datos_ccw90.write(0);
            }
        }
        
        int aux;
        aux = height;
        height = width;
        width = aux;
        contenedor.clear();
        Matriz_BGR = Matriz_BGR_auxi;
        
        datos_ccw90.close();
        buffer_ccw90.close();
        copia_ccw90.close();  
    }

    // LISTA
    static void Girar_CW_90(int num) throws IOException {
        System.out.println("Aplico funcion CW 90");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_cw90 = new FileOutputStream("CW_90_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_cw90 = new BufferedOutputStream(copia_cw90);
        DataOutputStream datos_cw90 = new DataOutputStream(buffer_cw90);
        
        byte mi_aux;
        
        mi_aux = byte_array[21];
        byte_array[21] = byte_array[25];
        byte_array[25] = mi_aux;
        
        mi_aux = byte_array[20];
        byte_array[20] = byte_array[24];
        byte_array[24] = mi_aux;
        
        mi_aux = byte_array[19];
        byte_array[19] = byte_array[23];
        byte_array[23] = mi_aux;
        
        mi_aux = byte_array[18];
        byte_array[18] = byte_array[22];
        byte_array[22] = mi_aux;
  
        datos_cw90.write(byte_array);
        
        Matriz_BGR_auxi = new BGR [width][height];
        
        int Padding = 0;
        if ((height*3)%4!=0){
             Padding = 4-((height*3)%4);
        }
        // ############### APLICO GIRO 90 GRADOS CCW #################################################################3
        int B_cw90, G_cw90, R_cw90;
        Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        contenedor.clear();
        
        for (int q4=0; q4<height; q4++){
            Pixel_aux = new BGR[width]; 
            for (int q5=0; q5<width; q5++){
                Pixel_aux[q5] = Matriz_BGR[q4][q5];
            }
            contenedor.push(Pixel_aux);
        }
        
        for (int z=0; z<height; z++){
            Pixel_aux = contenedor.pop();
            for (int z2=0; z2<width; z2++){
                Matriz_BGR_auxi[z2][z] = Pixel_aux[z2];
            }
        }
        
        // #################### APLICO FLIP VERTICAL ##########################################################
        
        BGR [] Pixel_aux2 = new BGR [height];
        Stack <BGR[]> contenedor2 = new Stack();
        
        for (int kj22=0; kj22<width; kj22++){
            BGR [] Pixeles2 = new BGR [height];
            for (int ki22=0; ki22<height; ki22++){
                opcion = Matriz_BGR_auxi[kj22][ki22];   
                Pixeles2[ki22] = opcion;
            }
            contenedor2.push(Pixeles2);   
        }
        
        for (int kjj22=0; kjj22<width; kjj22++){
            Pixel_aux2 = contenedor2.pop();
            
            for (int kii22=0; kii22<height; kii22++){
                Matriz_BGR_auxi[kjj22][kii22] = Pixel_aux2[kii22];
            }
        }
        // #################### APLICO FLIP VERTICAL ##########################################################
        
        // #################### APLICO FLIP HORIZONTAL ##########################################################
        //opcion = new BGR();
        BGR [] Pixel_aux3 = new BGR [width];
        Stack <BGR[]> contenedor3 = new Stack();
        
        for (int kj2=0; kj2<height; kj2++){
            BGR [] Pixeles3 = new BGR [width];
            for (int ki2=0; ki2<width; ki2++){
                opcion = Matriz_BGR_auxi[ki2][kj2];   
                Pixeles3[ki2] = opcion;
            }
            contenedor3.push(Pixeles3);   
        }
        
        for (int kjj2=0; kjj2<height; kjj2++){
            Pixel_aux3 = contenedor3.pop();
            for (int kii2=0; kii2<width; kii2++){
                Matriz_BGR_auxi[kii2][kjj2] = Pixel_aux3[kii2];
            }
        }
        
        // #################### FIN APLICO FLIP HORIZONTAL ##########################################################
        
        opcion = new BGR();
        for (int z=0; z<width; z++){
            for (int z2=0; z2<height; z2++){              
                opcion = Matriz_BGR_auxi[z][z2];    
                
                B_cw90 = (byte) opcion.Retornar_B();
                datos_cw90.write(B_cw90);
               
                G_cw90 = (byte) opcion.Retornar_G();
                datos_cw90.write(G_cw90);
                
                R_cw90 = (byte) opcion.Retornar_R();
                datos_cw90.write(R_cw90);   
            }
            
            for (int h=0; h<Padding; h++){
                datos_cw90.write(0);
            }
        }
        
        int aux;
        aux = height;
        height = width;
        width = aux;
        contenedor2.clear();
        contenedor3.clear();
        Matriz_BGR = Matriz_BGR_auxi;
    
        datos_cw90.close();
        buffer_cw90.close();
        copia_cw90.close();    
    }

    // LISTA
    static void Girar_CCW_270(int num) throws IOException {
        System.out.println("Aplico funcion CCW 270");
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_ccw270 = new FileOutputStream("CCW_270_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_ccw270 = new BufferedOutputStream(copia_ccw270);
        DataOutputStream datos_ccw270 = new DataOutputStream(buffer_ccw270);
        
        byte mi_aux;
        
        mi_aux = byte_array[21];
        byte_array[21] = byte_array[25];
        byte_array[25] = mi_aux;
        
        mi_aux = byte_array[20];
        byte_array[20] = byte_array[24];
        byte_array[24] = mi_aux;
        
        mi_aux = byte_array[19];
        byte_array[19] = byte_array[23];
        byte_array[23] = mi_aux;
        
        mi_aux = byte_array[18];
        byte_array[18] = byte_array[22];
        byte_array[22] = mi_aux;
  
        datos_ccw270.write(byte_array);
        
        Matriz_BGR_auxi = new BGR [width][height];
        
        int Padding = 0;
        if ((height*3)%4!=0){
             Padding = 4-((height*3)%4);
        }
        // ############### APLICO GIRO 90 GRADOS CCW #################################################################3
        int B_ccw270, G_ccw270, R_ccw270;
        Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        contenedor.clear();
        
        for (int q4=0; q4<height; q4++){
            Pixel_aux = new BGR[width]; 
            for (int q5=0; q5<width; q5++){
                Pixel_aux[q5] = Matriz_BGR[q4][q5];
            }
            contenedor.push(Pixel_aux);
        }
        
        for (int z=0; z<height; z++){
            Pixel_aux = contenedor.pop();
            for (int z2=0; z2<width; z2++){
                Matriz_BGR_auxi[z2][z] = Pixel_aux[z2];
            }
        }
        
        // #################### APLICO FLIP VERTICAL ##########################################################
        
        BGR [] Pixel_aux2 = new BGR [height];
        Stack <BGR[]> contenedor2 = new Stack();
        
        for (int kj22=0; kj22<width; kj22++){
            BGR [] Pixeles2 = new BGR [height];
            for (int ki22=0; ki22<height; ki22++){
                opcion = Matriz_BGR_auxi[kj22][ki22];   
                Pixeles2[ki22] = opcion;
            }
            contenedor2.push(Pixeles2);   
        }
        
        for (int kjj22=0; kjj22<width; kjj22++){
            Pixel_aux2 = contenedor2.pop();
            
            for (int kii22=0; kii22<height; kii22++){
                Matriz_BGR_auxi[kjj22][kii22] = Pixel_aux2[kii22];
            }
        }
        // #################### APLICO FLIP VERTICAL ##########################################################
        
        // #################### APLICO FLIP HORIZONTAL ##########################################################
        //opcion = new BGR();
        BGR [] Pixel_aux3 = new BGR [width];
        Stack <BGR[]> contenedor3 = new Stack();
        
        for (int kj2=0; kj2<height; kj2++){
            BGR [] Pixeles3 = new BGR [width];
            for (int ki2=0; ki2<width; ki2++){
                opcion = Matriz_BGR_auxi[ki2][kj2];   
                Pixeles3[ki2] = opcion;
            }
            contenedor3.push(Pixeles3);   
        }
        
        for (int kjj2=0; kjj2<height; kjj2++){
            Pixel_aux3 = contenedor3.pop();
            for (int kii2=0; kii2<width; kii2++){
                Matriz_BGR_auxi[kii2][kjj2] = Pixel_aux3[kii2];
            }
        }
        
        // #################### FIN APLICO FLIP HORIZONTAL ##########################################################
        
        opcion = new BGR();
        for (int z=0; z<width; z++){
            for (int z2=0; z2<height; z2++){              
                opcion = Matriz_BGR_auxi[z][z2];    
                
                B_ccw270 = (byte) opcion.Retornar_B();
                datos_ccw270.write(B_ccw270);
               
                G_ccw270 = (byte) opcion.Retornar_G();
                datos_ccw270.write(G_ccw270);
                
                R_ccw270 = (byte) opcion.Retornar_R();
                datos_ccw270.write(R_ccw270);   
            }
            
            for (int h=0; h<Padding; h++){
                datos_ccw270.write(0);
            }
        }
        
        int aux;
        aux = height;
        height = width;
        width = aux;
        contenedor2.clear();
        contenedor3.clear();
        Matriz_BGR = Matriz_BGR_auxi;
    
        datos_ccw270.close();
        buffer_ccw270.close();
        copia_ccw270.close();  
    }

    // LISTA
    static void Girar_CW_270(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion CW 270");
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_cw270 = new FileOutputStream("CW_270_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_cw270 = new BufferedOutputStream(copia_cw270);
        DataOutputStream datos_cw270 = new DataOutputStream(buffer_cw270);
        
        byte mi_aux;
        
        mi_aux = byte_array[21];
        byte_array[21] = byte_array[25];
        byte_array[25] = mi_aux;
        
        mi_aux = byte_array[20];
        byte_array[20] = byte_array[24];
        byte_array[24] = mi_aux;
        
        mi_aux = byte_array[19];
        byte_array[19] = byte_array[23];
        byte_array[23] = mi_aux;
        
        mi_aux = byte_array[18];
        byte_array[18] = byte_array[22];
        byte_array[22] = mi_aux;
  
        datos_cw270.write(byte_array);
        
        Matriz_BGR_auxi = new BGR [width][height];
        
        int Padding = 0;
        if ((height*3)%4!=0){
             Padding = 4-((height*3)%4);
        }
        
        int B_cw270, G_cw270, R_cw270;
        Pixel_aux = new BGR [width];
        Stack <BGR[]> contenedor = new Stack();
        contenedor.clear();
        
        for (int q4=0; q4<height; q4++){
            Pixel_aux = new BGR[width]; 
            for (int q5=0; q5<width; q5++){
                Pixel_aux[q5] = Matriz_BGR[q4][q5];
            }
            contenedor.push(Pixel_aux);
        }
        
        for (int z=0; z<height; z++){
            Pixel_aux = contenedor.pop();
            for (int z2=0; z2<width; z2++){
                Matriz_BGR_auxi[z2][z] = Pixel_aux[z2];
            }
        }
        
        opcion = new BGR();
        for (int z=0; z<width; z++){
            for (int z2=0; z2<height; z2++){              
                opcion = Matriz_BGR_auxi[z][z2];    
                
                B_cw270 = (byte) opcion.Retornar_B();
                datos_cw270.write(B_cw270);
               
                G_cw270 = (byte) opcion.Retornar_G();
                datos_cw270.write(G_cw270);
                
                R_cw270 = (byte) opcion.Retornar_R();
                datos_cw270.write(R_cw270);   
            }
            
            for (int h=0; h<Padding; h++){
                datos_cw270.write(0);
            }
        }
        
        int aux;
        aux = height;
        height = width;
        width = aux;
        contenedor.clear();
        Matriz_BGR = Matriz_BGR_auxi;
        
        datos_cw270.close();
        buffer_cw270.close();
        copia_cw270.close();  
    }

    // LISTA
    static void Aplicar_Flip_Hor_Min(int num) throws FileNotFoundException, IOException {
        System.out.println("Aplico funcion Flip Horizontal Imagen menor a 24");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_flip_hor = new FileOutputStream("Flip_HorizontalMin_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_flip_hor = new BufferedOutputStream(copia_flip_hor);
        DataOutputStream datos_flip_hor = new DataOutputStream(buffer_flip_hor);
        
        if (Padding_min>0){
        width = (((int)byte_array[21]&0xff)<<24) | (((int)byte_array[20]&0xff)<<16) | (((int)byte_array[19]&0xff)<<8) | (int)byte_array[18]&0xff;
        int width2 = width+Padding_min;
        System.out.println("Width: "+width2+" Mi padding es: "+Padding_min);
        byte_array[18] = (byte) (width2 & 0xFF);   
        byte_array[19] = (byte) ((width2 >> 8) & 0xFF);   
        byte_array[20] = (byte) ((width2 >> 16) & 0xFF);   
        byte_array[21] = (byte) ((width2 >> 24) & 0xFF);
        
        width2 = (((int)byte_array[21]&0xff)<<24) | (((int)byte_array[20]&0xff)<<16) | (((int)byte_array[19]&0xff)<<8) | (int)byte_array[18]&0xff;
        
        System.out.println(width2);
        }
        
        datos_flip_hor.write(byte_array);
       
        opcion = new BGR();
        //width = width+Padding_min;
        int [] Pixel_aux = new int [width];
        int longitud = Paleta_BGR.length;
        
        for (int h=0; h<longitud; h++){
            datos_flip_hor.write(Paleta_BGR[h].Retornar_B());
            datos_flip_hor.write(Paleta_BGR[h].Retornar_G());
            datos_flip_hor.write(Paleta_BGR[h].Retornar_R());
            datos_flip_hor.write(Paleta_BGR[h].Retornar_A());
        }
        
        if (num_bits==1){
            
            int Padding = 0;
            if ((width/8)%4!=0){
                Padding = 4-((width/8)%4);
            }
            int cont = 0;
            int cont2 = 0;
            Stack <byte[]> pilaB = new Stack<byte[]>();
            int marcador = width/8;       
            byte [] lista = new byte [marcador];
            
            for (int u3=0; u3<Pixeles_Min.length; u3+=marcador){
                lista = new byte [marcador];
                for (int i3=0; i3<marcador; i3++){
                    lista[i3] = Pixeles_Min[cont];
                    cont++;
                }
                pilaB.push(lista);
                //datos_flip_hor.write(pixx); 
            }
            
            byte [] lista_aux = new byte [marcador];
            
            while (!pilaB.isEmpty()){
                lista_aux = new byte [marcador];
                lista_aux = pilaB.pop();
                for (int i2=0; i2<marcador; i2++){
                    Pixeles_Min[cont2] = lista_aux[i2];
                    cont2++;
                    datos_flip_hor.write(lista_aux[i2]);
                }
                
                for (int i4=0; i4<Padding; i4++){
                    datos_flip_hor.write(0);
                }
            }
          
        } else if (num_bits==4){
            
            int Padding = 0;
            if ((width/2)%4!=0){
                Padding = 4-((width/2)%4);
            }
            int cont = 0;
            int cont2 = 0;
            Stack <byte[]> pilaB = new Stack<byte[]>();
            int marcador = width/2;       
            byte [] lista = new byte [marcador];
            
            for (int u3=0; u3<Pixeles_Min.length; u3+=marcador){
                lista = new byte [marcador];
                for (int i3=0; i3<marcador; i3++){
                    lista[i3] = Pixeles_Min[cont];
                    cont++;
                }
                pilaB.push(lista);
                //datos_flip_hor.write(pixx); 
            }
            
            byte [] lista_aux = new byte [marcador];
            
            while (!pilaB.isEmpty()){
                lista_aux = new byte [marcador];
                lista_aux = pilaB.pop();
                for (int i2=0; i2<marcador; i2++){
                    Pixeles_Min[cont2] = lista_aux[i2];
                    cont2++;
                    datos_flip_hor.write(lista_aux[i2]);
                }
                for (int i4=0; i4<Padding; i4++){
                    datos_flip_hor.write(0);
                }
            }
           
        } else if (num_bits==8){
            
            
            Stack <int[]> contenedor = new Stack();
            contenedor.clear();
            int opcion;
            
            for (int kj2=0; kj2<height; kj2++){
                int [] Pixeles_aux = new int [width];
                for (int ki2=0; ki2<width; ki2++){
                    opcion = Pixeles[kj2][ki2];   
                    Pixeles_aux[ki2] = opcion;
                }
                contenedor.push(Pixeles_aux);   
            }
            
            Pixel_aux = new int[width];
            for (int kjj2=0; kjj2<height; kjj2++){
                Pixel_aux = contenedor.pop();

                for (int kii2=0; kii2<width; kii2++){
                    Pixeles[kjj2][kii2] = Pixel_aux[kii2];
                }
            }
            
            int opcion2;
            System.out.println(width);
            for (int kjjj23=0; kjjj23<height; kjjj23++){
                
                for (int kiii23=0; kiii23<width; kiii23++){

                    opcion2 = Pixeles[kjjj23][kiii23];   
                    /*if ((int) opcion2<0){
                        opcion2 = opcion2+256;
                    }*/
                    datos_flip_hor.writeByte((byte) opcion2); 
                    //System.out.println(String.valueOf(opcion));
                    //System.out.println(Byte.toString((byte) opcion));
                    
                }
               
                for (int h=0; h<Padding_min; h++){
                    datos_flip_hor.write(null);
                    //datos_flip_hor.write(0);
                    
                }
                 
            }
            //int opcion;
            /*for (int kjjj2=height-1; kjjj2>=0; kjjj2--){
                for (int kiii2=0; kiii2<width; kiii2++){
                    opcion = Pixeles[kjjj2][kiii2]; 
                    datos_flip_hor.writeByte(opcion); 
                }
                for (int h=0; h<Padding_min; h++){
                    datos_flip_hor.writeByte(0);
                }
            }*/
            
            
        } else if (num_bits==16){
            
            int Padding = 0;
            if ((width*num_bits)%4!=0){
                Padding = 4-((width*num_bits)%4);
            }
            int opcion;
            Stack <int[]> contenedor = new Stack();

            for (int kj2=0; kj2<height; kj2++){
                int [] Pixeles_aux = new int [width];
                for (int ki2=0; ki2<width; ki2++){
                    opcion = Pixeles[kj2][ki2];   
                    Pixeles_aux[ki2] = opcion;
                }
                contenedor.push(Pixeles_aux);   
            }

            for (int kjj2=0; kjj2<height; kjj2++){
                Pixel_aux = contenedor.pop();

                for (int kii2=0; kii2<width; kii2++){
                    Pixeles[kjj2][kii2] = Pixel_aux[kii2];
                }
            }

            for (int kjjj2=0; kjjj2<height; kjjj2++){
                for (int kiii2=0; kiii2<width; kiii2++){

                    opcion = Pixeles[kjjj2][kiii2];    
                    datos_flip_hor.write(opcion);               
                }
                for (int padVal=0; padVal<Padding; padVal++){
                    datos_flip_hor.write(0);
                }
            } 
        } 
       
        //datos_flip_hor.flush();
        datos_flip_hor.close();
        //buffer_flip_hor.flush();
        buffer_flip_hor.close();
        copia_flip_hor.close(); 
    }

    
    static void Aplicar_Flip_Ver_Min(int num) throws IOException {
        System.out.println("Aplico funcion Flip Vertical Imagen menor a 24");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_flip_ver = new FileOutputStream("Flip_VerticalMin_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_flip_ver = new BufferedOutputStream(copia_flip_ver);
        DataOutputStream datos_flip_ver = new DataOutputStream(buffer_flip_ver);
        datos_flip_ver.write(byte_array);
       
        //opcion = new BGR();
        int [] Pixel_aux2 = new int [height];
        int longitud = Paleta_BGR.length;
        
        for (int h=0; h<longitud; h++){
            datos_flip_ver.write(Paleta_BGR[h].Retornar_B());
            datos_flip_ver.write(Paleta_BGR[h].Retornar_G());
            datos_flip_ver.write(Paleta_BGR[h].Retornar_R());
            datos_flip_ver.write(Paleta_BGR[h].Retornar_A());
        }
        
        if (num_bits==1){
            
            int Padding = 0;
            if ((width*num_bits)%4!=0){
                Padding = 4-((width*num_bits)%4);
            }
            int cont = 0;
            int cont2 = 0;
            Stack <byte[]> pilaB = new Stack<byte[]>();
            int marcador = width/8;       
            byte [] lista = new byte [marcador];
            
            for (int u3=0; u3<Pixeles_Min.length; u3+=marcador){
                lista = new byte [marcador];
                for (int i3=0; i3<marcador; i3++){
                    lista[i3] = Pixeles_Min[cont];
                    cont++;
                }
                pilaB.push(lista);
                //datos_flip_hor.write(pixx); 
            }
            
            byte [] lista_aux = new byte [marcador];
            
            while (!pilaB.isEmpty()){
                lista_aux = new byte [marcador];
                lista_aux = pilaB.pop();
                for (int i2=0; i2<marcador; i2++){
                    Pixeles_Min[cont2] = lista_aux[i2];
                    cont2++;
                    datos_flip_ver.write(lista_aux[i2]);
                }
                
                for (int i4=0; i4<Padding; i4++){
                    datos_flip_ver.write(0);
                }
            }
          
        } else if (num_bits==4){
            
            int Padding = 0;
            if ((width*num_bits)%4!=0){
                Padding = 4-((width*num_bits)%4);
            }
            int cont = 0;
            int cont2 = 0;
            Stack <byte[]> pilaB = new Stack<byte[]>();
            int marcador = width/4;       
            byte [] lista = new byte [marcador];
            
            for (int u3=0; u3<Pixeles_Min.length; u3+=marcador){
                lista = new byte [marcador];
                for (int i3=0; i3<marcador; i3++){
                    lista[i3] = Pixeles_Min[cont];
                    cont++;
                }
                pilaB.push(lista);
                //datos_flip_hor.write(pixx); 
            }
            
            byte [] lista_aux = new byte [marcador];
            
            while (!pilaB.isEmpty()){
                lista_aux = new byte [marcador];
                lista_aux = pilaB.pop();
                for (int i2=0; i2<marcador; i2++){
                    Pixeles_Min[cont2] = lista_aux[i2];
                    cont2++;
                    datos_flip_ver.write(lista_aux[i2]);
                }
                for (int i4=0; i4<Padding; i4++){
                    datos_flip_ver.write(0);
                }
            }
           
        } else if (num_bits==8){
            
            int Padding = 0;
            if ((width)%4!=0){
                Padding = 4-((width)%4);
            }
            
            int opcion;
            Stack <int[]> contenedor = new Stack();
            contenedor.clear();
            //int [][] Pixeles_aux = new int [height][width];
            
            for (int y=0; y<height; y++){
                for (int y2=width-1; y2>-1; y2--){
                    datos_flip_ver.write(Pixeles[y][y2]);
                }
                for (int h=0; h<Padding; h++){
                    datos_flip_ver.write(0);
                }
            }
            /*
            for (int kj2=0; kj2<width; kj2++){
                int [] Pixeles_aux = new int [height];
                for (int ki2=0; ki2<height; ki2++){
                    opcion = Pixeles[ki2][kj2];   
                    Pixeles_aux[ki2] = opcion;
                }
                contenedor.push(Pixeles_aux);   
            }

            for (int kjj2=0; kjj2<width; kjj2++){
                Pixel_aux2 = contenedor.pop();

                for (int kii2=0; kii2<height; kii2++){
                    Pixeles[kii2][kjj2] = Pixel_aux2[kii2];
                }
            }

            for (int kjjj2=0; kjjj2<height; kjjj2++){
                for (int kiii2=0; kiii2<width; kiii2++){

                    opcion = Pixeles[kjjj2][kiii2];    
                    datos_flip_ver.write(opcion);               
                }
                for (int h=0; h<Padding; h++){
                    datos_flip_ver.write(0);
                }
            }*/
            
        } else if (num_bits==16){
            
            int Padding = 0;
            if ((width*num_bits)%4!=0){
                Padding = 4-((width*num_bits)%4);
            }
            int opcion;
            Stack <int[]> contenedor = new Stack();

            for (int kj2=0; kj2<height; kj2++){
                int [] Pixeles_aux = new int [width];
                for (int ki2=0; ki2<width; ki2++){
                    opcion = Pixeles[kj2][ki2];   
                    Pixeles_aux[ki2] = opcion;
                }
                contenedor.push(Pixeles_aux);   
            }

            for (int kjj2=0; kjj2<height; kjj2++){
                Pixel_aux2 = contenedor.pop();

                for (int kii2=0; kii2<width; kii2++){
                    Pixeles[kjj2][kii2] = Pixel_aux2[kii2];
                }
            }

            for (int kjjj2=0; kjjj2<height; kjjj2++){
                for (int kiii2=0; kiii2<width; kiii2++){

                    opcion = Pixeles[kjjj2][kiii2];    
                    datos_flip_ver.write(opcion);               
                }
                for (int h=0; h<Padding; h++){
                    datos_flip_ver.write(0);
                }
            } 
        } 
       
        //datos_flip_ver.flush();
        datos_flip_ver.close();
        //buffer_flip_ver.flush();
        buffer_flip_ver.close();
        copia_flip_ver.close(); 
    }

    static void Girar_CW_90_Min(int num) {
        System.out.println("Aplico funcion CW 90 Imagen menor a 24");
    }

    static void Girar_CCW_90_Min(int num) throws IOException {
        System.out.println("Aplico funcion CCW 90 Imagen menor a 24");
        
        // CREO EL NUEVO ARCHIVO GENERADO CON SU RESPECTIVA SALIDA
        FileOutputStream copia_ccw90_min = new FileOutputStream("CCW_90_Min_"+num+"_"+nombre_final);
        BufferedOutputStream buffer_ccw90_min = new BufferedOutputStream(copia_ccw90_min);
        DataOutputStream datos_ccw90_min = new DataOutputStream(buffer_ccw90_min);
        
        byte mi_aux;
        //21 y 20 -->
        //25 y 24 -->
        mi_aux = byte_array[21];
        byte_array[21] = byte_array[25];
        byte_array[25] = mi_aux;
        
        mi_aux = byte_array[20];
        byte_array[20] = byte_array[24];
        byte_array[24] = mi_aux;
        
        mi_aux = byte_array[19];
        byte_array[19] = byte_array[23];
        byte_array[23] = mi_aux;
        
        mi_aux = byte_array[18];
        byte_array[18] = byte_array[22];
        byte_array[22] = mi_aux;
  
        datos_ccw90_min.write(byte_array);
        
        opcion = new BGR();
        int [] Pixel_aux = new int [width];
        int longitud = Paleta_BGR.length;
        
        for (int h=0; h<longitud; h++){
            datos_ccw90_min.write(Paleta_BGR[h].Retornar_B());
            datos_ccw90_min.write(Paleta_BGR[h].Retornar_G());
            datos_ccw90_min.write(Paleta_BGR[h].Retornar_R());
            datos_ccw90_min.write(Paleta_BGR[h].Retornar_A());
        }
        
        int [][] Matriz_Pixeles_auxi= new int [width][height];
        
        if (num_bits==1){
            
        } else if (num_bits==4){
            
        } else if (num_bits==8){
            
            int Padding = 0;
            if ((height)%4!=0){
                 Padding = 4-((height)%4);
            }
            // ############### APLICO GIRO 90 GRADOS CCW #################################################################3
            Pixel_aux = new int [width];
            Stack <int[]> contenedor = new Stack();
            contenedor.clear();
            
            for (int q4=0; q4<height; q4++){
                Pixel_aux = new int[width]; 
                for (int q5=0; q5<width; q5++){
                    Pixel_aux[q5] = Pixeles[q4][q5];
                }
                contenedor.push(Pixel_aux);
            }
            
            for (int z=0; z<height; z++){
                //Pixel_aux = new int[width]; 
                Pixel_aux = contenedor.pop();
                for (int z2=0; z2<width; z2++){
                    Matriz_Pixeles_auxi[z2][z] = Pixel_aux[z2];
                }
            }
            
            int opcion = 0;
            for (int kjjj2=0; kjjj2<width; kjjj2++){
                for (int kiii2=0; kiii2<height; kiii2++){
                    
                    opcion = Matriz_Pixeles_auxi[kjjj2][kiii2]<<1;    
                    datos_ccw90_min.writeByte(opcion);               
                }
                for (int h2=0; h2<Padding; h2++){
                    datos_ccw90_min.write(0);
                    
                }
            }
            
            contenedor.clear();
            
        } else if (num_bits==16){
            
            int Padding = 0;
            if ((height*2)%4!=0){
                 Padding = 4-((height*2)%4);
            }
            // ############### APLICO GIRO 90 GRADOS CCW #################################################################3
            Pixel_aux = new int [width];
            Stack <int[]> contenedor = new Stack();
            contenedor.clear();
            
            for (int q4=0; q4<height; q4++){
                Pixel_aux = new int[width]; 
                for (int q5=0; q5<width; q5++){
                    Pixel_aux[q5] = Pixeles[q4][q5];
                }
                contenedor.push(Pixel_aux);
            }
            
            for (int z=0; z<height; z++){
                //Pixel_aux = new int[width]; 
                Pixel_aux = contenedor.pop();
                for (int z2=0; z2<width; z2++){
                    Matriz_Pixeles_auxi[z2][z] = Pixel_aux[z2];
                }
            }
            
            int opcion;
            for (int kjjj2=0; kjjj2<width; kjjj2++){
                for (int kiii2=0; kiii2<height; kiii2++){
                    opcion = Matriz_Pixeles_auxi[kjjj2][kiii2];    
                    datos_ccw90_min.write(opcion);               
                }
                for (int h=0; h<Padding; h++){
                    datos_ccw90_min.write(0);
                }
            }
            
            contenedor.clear();
        }
        
        int aux;
        aux = height;
        height = width;
        width = aux;
        Pixeles = Matriz_Pixeles_auxi;
        datos_ccw90_min.close();
        buffer_ccw90_min.close();
        copia_ccw90_min.close();    
      
    }
    
    static void Girar_CW_270_Min(int num) {
        System.out.println("Aplico CW 270 Imagen menor a 24");
    }
    
    static void Girar_CCW_270_Min(int num) {
        System.out.println("Aplico funcion CCW 270 Imagen menor a 24");
    }
    
}
