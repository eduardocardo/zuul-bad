
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("ir"),

    QUIT("quitar"),

    HELP("ayuda"),

    LOOK("mirar"),

    EAT("comer"),

    BACK("volver"),
    
    TAKE("coger"),

    DROP("tirar"),

    ITEMS("inventario"),

    UNKNOWN(" ");
    private String comando;
    
    /**
     * Constructor del enum Option
     * @param comando es el comando asociado a la constante
     */
    Option(String comando)
    {
        this.comando = comando;
    }
    
    /**
     * Metodo que devuelve el comando asociado a la constante
     */
    public String getComando()
    {
        return comando;
    }
}
