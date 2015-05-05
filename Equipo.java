
/**
 * Esta clase representa un objeto que puede equiparse el player
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipo
{
    //es el numero identificador del objeto
    private int id;
    //indica el nombre del item
    private String nombre;
    //es una descripcion del objeto
    private String descripcion;
    //indica el peso del item en kg
    private float peso;
    //indica si el objeto se puede coger
    private boolean coger;
    //indica el numero identificador actual de la clase 
    private static int currentId = 1;
    //indica el bono de ataque del equipo
    private int bonoAtaque;
    //indica el bono de defensa del equipo
    private int bonoDefensa;
    //indica la resistencia que le queda al equipo
    private int durabilidad;

    /**
     * Constructor for objects of class Equipo
     * @param id es el numero identificativo del equipo
     * @param nombre es el nombre del equipo
     * @param descripcion es una descripcion del equipo
     * @param peso es el peso que tiene
     * @param coger indica si el objeto se puede coger o no
     * @param ataque es el bono de ataque del equipo
     * @param def es el bono de defensa del equipo
     * @param dura es el grado de durabilidad que tiene el equipo
     */
    public Equipo(int id,String nombre,String descripcion,float peso,boolean coger,int ataque,int def,int dura)
    {
        this.id = id;
        currentId++;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        coger = coger;
        if(ataque > 0)
        {
            bonoAtaque = ataque;
        }
        else
        {
            bonoAtaque = 0;
        }
        if(def > 0)
        {
            bonoDefensa = def;
        }
        else
        {
            bonoDefensa = 0;
        }
        if(dura > 0)
        {
            durabilidad = dura;
        }
        else
        {
            durabilidad = 5;
        }
    }
 /**
     * 
     * @return el numero identificativo del equipo 
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return el nombre del equipo 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @return la descripcion del equipo 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @return el peso del equipo 
     */
    public float getPeso() {
        return peso;
    }

    /**
     * Metodo que indica si el equipo se puede coger o no
     * @return true si se puede coger,false si no se puede
     */
    public boolean isCoger() {
        return coger;
    }

    /**
     * 
     * @return el bono de ataque que tiene el equipo
     */
    public int getBonoAtaque() {
        return bonoAtaque;
    }

    /**
     * 
     * @return el bono de defensa que tiene el equipo 
     */
    public int getBonoDefensa() {
        return bonoDefensa;
    }

    /**
     * 
     * @return la durabilidad que tiene actualmente el equipo 
     */
    public int getDurabilidad() {
        return durabilidad;
    }
    
    public String toString()
    {
        return "(" + id + ")" + nombre + " " + descripcion + 
                " ,peso : " + peso + "\nbono de ataque : " + bonoAtaque +
                " ,bono de defensa : " + bonoDefensa + " ,durabilidad :" +
                durabilidad;
    }
}
