import java.util.Random;
/**
 * Esta clase representa un personaje no jugador que estara presente en las habitaciones
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pnj
{
    //indica el numero identificador del pnk
    private int id;
    //indica el nombre del pnj
    private String nombre;
    //indica una descripcion del pnj
    private String descripcion;
    //indica la vida que tiene el pnj
    private int vida;
    //es el item que puede dropear el pnj
    private Item item;
    //indica si el pnj esta vivo o no
    private boolean isDead;
    //indica el numero identificador actual de la clase Pnj
    private static int currentId = 1;
    //cte que indica el limite minimo de vida que puede tener
    private static final int VIDA_MIN = 0;
    //cte que indica el limite maximo de vida que puede tener el pnj
    private static final int VIDA_MAX = 6;
    //indica el grado de dificultad del pnj,comprende desde 1 a 5
    private int valorDeDesafio;

    /**
     * Constructor for objects of class Pnj
     * @param id es el numero identificador del pnj
     * @param nombre es el nombre del pnj
     * @param descripcion es la descripcion del pnj
     * @param es la vida que tiene el pnj,pro defecto 2
     * @para es el objeto que puede dropear el pnj
     * @param indica si el pnj esta vivo o no
     * @param valor es valor que indica el grado de dificultad del pnj,por defecto 2
     */
    public Pnj(int id,String nombre,String descripcion,int vida,Item item,boolean isDead,int valor)
    {
        this.id = id;
        currentId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        if(vida > VIDA_MIN && vida <=VIDA_MAX)
        {
            this.vida = vida;
        }
        else
        {
            this.vida = 2;
        }
        this.item = item;
        this.isDead = isDead;
        if(valor >= 1 && valor <= 5)
        {
            valorDeDesafio = valor;
        }
        else
        {
            valorDeDesafio = 2;
        }
    }

    /**
     * Metodo que devuelve el id del pnj
     * @return el numero identificado del pnj
     */
    public int getId()
    {
        return id;
    }

    /**
     * Metodo que devuelve el nombre del pnj
     * @return el nombre del pnj
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Metodo que devuelve la descripcion del pnj
     * @return la descripcion del pnj
     */
    public String getDescripcion()
    {
        return descripcion;
    }

    /**
     * Metodo que devuelve la vida del pnj
     * @return la vida del pnj
     */
    public int getVida()
    {
        return vida;
    }

    /**
     * Metodo que devuelve el item que tiene el pnj
     * @return el item que posee el pnj
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * Metodo que indica si el pnj esta vivo o no
     * @return true si el pj esta muerto,false si esta vivo
     */
    public boolean isDead()
    {
        return isDead;
    }

    /**
     * Metodo que disminuye la vida del pnj
     */
    public void restarVida()
    {
        vida--;
        if(vida <= VIDA_MIN)
        {
            isDead = true;
        }
    }

    /**
     * Metodo por el cual el pnj realiza un ataque que consiste en  tirar un dado de 20
     * @return el valor de la tirada del dado
     */
    public int atacar()
    {
        Random rnd = new Random();
        return rnd.nextInt(20) + 1;
    }

    /**
     * Metodo que muestra informacion sobre el pnj
     */
    public String toString()
    {
        return "(" + id + ")" + " " + nombre + " " + descripcion; 
    }
    
    /**
     * Metodo que indica el bono de ataque del pnj y viene determinado por el valor de desafio
     * @return el bono de ataque
     */
    public int bonoAtaque()
    {
        int bono = 0;
        switch(valorDeDesafio)
        {
            case 1:
            bono = 1;
            break;
             case 2:
            bono = 2;
            break;
             case 3:
            bono = 2;
            break;
             case 4:
            bono = 3;
            break;
             case 5:
            bono = 4;
            break;
        }
        return bono;
        
    }
    
    /**
     * Metodo que indica el bono de defensa del pnj y viene determinado por el valor de desafio
     * @return el bono de defensa
     */
    public int bonoDefensa()
    {
        int bono = 0;
        switch(valorDeDesafio)
        {
            case 1:
            bono = 2;
            break;
             case 2:
            bono = 1;
            break;
             case 3:
            bono = 2;
            break;
             case 4:
            bono = 2;
            break;
             case 5:
            bono = 3;
            break;
        }
        return bono;
        
    }
}
