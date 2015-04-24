
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


    BACK("volver")
    ,

    TAKE("coger"),


    DROP("tirar"),


    ITEMS("objetos"),


    UNKNOWN("desconocido");
    private String comando;

    /**
     * Constructor del enum Option
     */
    Option(String comando)
    {
        this.comando = comando;
    }

    /**
     * Metodo que devuelve el comando
     * @return el comando 
     */   
    public String getCommand()
    {
        return comando;
    }
}

