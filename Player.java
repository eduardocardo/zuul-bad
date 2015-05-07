import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;
/**
 * Esta clase representa un jugador que entra en la torre
 * 
 * @author (Eduardo) 
 * @version (1.0)
 */
public class Player
{
    //representa la habitacion en la que esta el jugador
    private Room currentRoom;
    //coleccion que almacena las habitaciones por las que ha pasado el jugador
    private Stack<Room>stacks;
    //indica el peso actual que lleva el jugador
    private float peso;
    //indica el peso maximo que puede llevar el jugador
    private float pesoMax;
    //arrayList que almacena los items que lleva actualmente el jugador
    private ArrayList<Item> items;
    //indica la vida actual del player
    private int vida ;
    //indica si el player esta vivo o no
    private boolean isDead;
    //indica si el player esta en combate
    private boolean isCombats;
    //cte que indica la vida minima que puede tener un player
    private static final int VIDA_MIN = 0;
    //cte que indica la vida maxima que puede tener un player
    private static final int VIDA_MAX = 6;
    //cte que indica un valor de tirada critica
    private static final int TIRADA_CRITICA = 20;
    //cte que indica el valor de un tirada para tener exito cuando el player huye
    private static final int ATAQUE_OPORTUNIDAD = 13;
    //indica la vida inicial del player
    private int vidaInicial;
    //representa un objeto equipo que sera un arma
    private Equipo arma;
    //representa un objeto equipo que sera una armadura
    private Equipo armadura;
    //indica si el player ha logrado el objetivo del juego
    private boolean endGame;
    private static final int ID_END_GAME = 15;

    /**
     * Constructor for objects of class Player
     * @param pesoMax es el peso maximo que puede llevar el jugador
     */
    public Player(float pesoMax,int vida)
    {
        stacks = new Stack();
        items = new ArrayList<>();
        this.peso = 0;
        this.pesoMax = pesoMax; 
        if(vida > VIDA_MIN && vida <= VIDA_MAX)
        {
            this.vida = vida;
            vidaInicial = vida;
        }
        isDead = false;
        isCombats = false;
        arma = null;
        armadura = null;
        endGame = false;
    }

    /**
     * Metodo que devuelve el peso actual que lleva el jugador
     * @return el peso en kg que lleva actualmente el jugador
     */
    public float getPeso()
    {
        return peso;
    }

    /**
     * Metodo que devuelve la habitacion en la que se encuentra el jugador
     * @return la habitacion en la que se encuentra el jugador
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Metodo que devuelve el peso maximo que puede llevar el jugador
     * @return el peso maximo en kg
     */
    public float getPesoMax()
    {
        return pesoMax;
    }

    /**
     *Metodo que situa al jugador en una habitacion determinada
     *@param room es la habitacion en la que aparecera el jugador
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Metodo que hace que el jugador coma
     */
    public void eat()
    {
        if(!isCombats())
        {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else
        {
            System.out.println("Para que quieres comer en combate?");
        }
    }

    /**
     * Metodo que da al jugador una informacion sobre la habitacion en la que 
     * se encuentra
     */
    public void look()
    {
        if(!isCombats())
        {
            System.out.println(currentRoom.getLongDescription());
        }
        else
        {
            System.out.println("No te despistes y centrate en el combate");
        }
    }

    /**
     * Metodo que desplaza al jugador por las habitaciones por las salidas disponibles
     * @param direccion es la direccion a la que se desplaza el jugador
     */

    public void goRoom(String direccion)
    {
        //representa la habitacion a la que se desplaza
        Room nextRoom = null;
        //se guarda la habitacion a la que se desplazaria segun la direccion del parametro
        nextRoom = currentRoom.getExit(direccion);

        if (nextRoom == null) {

            System.out.println("Por ahi no hay salida!");
        }
        else if(!nextRoom.getEstaAbierta()) //la habitacion a la que se quiere mover no esta abierta
        {
            boolean encontrado = false;
            int i = 0;
            Item item = null;
            //se busca en el inventario del jugador si tiene un item llave
            while(i <items.size() && !encontrado)
            {
                if(items.get(i).getNombre().equals("llave"))
                {
                    encontrado = true;
                    item = items.get(i);
                }
            }
            //si player tiene una llave en su inventario
            if(encontrado)
            {
                //se abre la habitacion
                nextRoom.abrir();
                //desaparece la llave,es de un solo uso
                items.remove(item);
                //player se mueve a la habitacion
                stacks.push(currentRoom);
                currentRoom = nextRoom;
                look();
            }

            else
            {
                System.out.println("Hay un puerta cerrada que te impide el paso");
            }
        }
        else {
            stacks.push(currentRoom);
            currentRoom = nextRoom;
            look();

        }
    }

    /**
     * Metodo que vuelve a la habitacion anterior
     */
    public void backRoom()
    {
        if(!isCombats())
        {
            if(!stacks.empty())
            {
                currentRoom =stacks.pop();
                look();
            }
            else
            {
                System.out.println("No puedes volver a atras");
            }
        }
        else
        {
            System.out.println("No puedes volver a atras en combate");
        }
    }

    /**
     * Metodo que coge un item y lo añade a la coleccion
     * @param item es el nombre del objeto que coge el jugador
     */
    public void take(int idItem)
    {

        //primero se comprueba si el objeto que se quiere coger existe en la habitacion
        Item item = currentRoom.getItem(idItem);
        if(item != null)
        {
            //se comprueba si se supera el limite de peso maximo y si el item se puede coger

            //no supera el limite de peso maximo y el objeto se puede coger
            if((item.getPeso() + peso) <= pesoMax && item.getCoger()) 
            {
                //se añade el item a la coleccion
                items.add(item);
                
                //se suma el peso del item al peso que lleva el jugador
                peso += item.getPeso();
                //se elimina de la habitacion ese item
                currentRoom.removeItem(item);
                //se indica que se ha cogido ese item
               
                System.out.println("Has cogido " + item.getNombre());
                //si el jugador coge el item que determina el fin del juego
                if(item.getNombre().equals("objeto"))
                {
                    endGame = true;
                    System.out.println("Fin juego");
                }
            }
            //el objeto se puede coger pero supera el limite de peso maximo
            else if((item.getPeso() + peso) > pesoMax && item.getCoger())
            {
                System.out.println("No puedes coger ese objeto porque superas tu limite de peso maximo");
                System.out.println("El peso que llevas ahora es " + peso);
            }
            else
            {
                System.out.println("Ese item no se puede coger");
            }

        }
        else
        {
            System.out.println("El objeto que quieres coger no existe");
        }

    }

    /**
     * Metodo que suelta en la habitacion un item que ya posee el jugador
     * @param item es el nombre del objeto que quiere soltar
     */
    public void drop(int idItem)
    {

        //primero se comprueba que el jugador tiene ese item en su inventario
        Item item = null;
        boolean encontrado = false;
        int i = 0;
        while(i < items.size() && !encontrado)
        {
            if(items.get(i).getId() == idItem)
            {
                //si nombre pasado por parametro coincide con el nombre de algun item
                //que tiene el jugador en su inventario
                item = items.get(i);
                encontrado = true;
            }
            i++;
        }
        if(encontrado) //tiene el objeto en el inventario
        {
            //se elimina el objeto del inventario del jugador
            items.remove(item);
            //se resta el peso del objeto del peso que lleva el jugador
            peso -= item.getPeso();
            //se añade este objeto a la habitacion actual
            currentRoom.addItem(item);
            //se indica que ha soltado el item
            System.out.println("Has soltado " + item.getNombre());
        }
        else  //no tiene ese objeto en su inventario
        {
            System.out.println("No puedes tirar un objeto que no tienes");
        }
    }

    /**
     * Metodo que muestra informacion sobre los objetos que tiene actualmente el jugador
     */
    public void infoItems()
    {
        System.out.println("En tu inventario tienes :");
        if(items.size() > 0)
        {
            for(Item item : items)
            {
                System.out.println(item);
            }
        }
        else
        {
            System.out.println("No tienes ningun item");
        }
    }

    /**
     * Metodo que devuelve la vida que tiene el player
     * @return la vida del player
     */
    public int getVida()
    {
        return vida;
    }

    /**
     * Metodo que indica si un player esta vivo o no
     * @return true si esta muerto y false si esta vivo
     */
    public boolean isDead()
    {
        return isDead();
    }

    /**
     * Metodo que devuelve si el player esta en combate
     * @return true si esta en combate,false si no lo esta
     */
    public boolean isCombats()
    {
        return isCombats;
    }

    /**
     * Metodo que disminuye la vida del player
     */
    public void quitarVida()
    {
        vida--;
        if(vida <= VIDA_MIN)
        {
            isDead = true;
        }
    }

    /**
     * Metodo por el cual el player ataca a un pnj que esta presente en la habitacion
     * @param id es el numero identificativo del pnj al que se quiere atacar
     * @return true si el player muere,false si no muere
     */
    public boolean atacar(int idPnj)
    {
        //primero se comprueba que el pnj al que se quiere atacar existe en la habitacion
        Pnj pnj = currentRoom.getPnj(idPnj);
        if(pnj != null)
        {
            //playe entra en combate
            isCombats = true;
            Random rnd = new Random();
            //tirada del player
            int tirada = rnd.nextInt(20) + 1 ;
            //a la tirada se le aplica el bono de ataque
            int tiradaPlayer = tirada + bonoAtaque() - pnj.bonoDefensa();
            //a la tirada del pnj se le aplica el bono de defensa
            int tiradaPnj = pnj.atacar() + pnj.bonoAtaque() - bonoDefensa();
            //se enfrenta la tirada del dado del player frente a la del pnj
            System.out.println("Jugador saca " + tiradaPlayer + " y " + pnj.getNombre() + " saca " + tiradaPnj);
            if(tiradaPlayer > tiradaPnj)
            {
                //gana el asalto y resta una vida al pnj
                pnj.restarVida();

                //si el player obtiene una tirada critica hace 2 daños
                if(tirada >= TIRADA_CRITICA)
                {
                    pnj.restarVida();
                    System.out.println("CRITICO!!!");
                    System.out.println("Has atacado un punto vital y le has quitado dos "
                        + "puntos de vida a " + pnj.getNombre());
                }
                else
                {
                    System.out.println("Le quitas una vida a " + pnj.getNombre());
                }
                System.out.println("Vida del " + pnj.getNombre() + " : " + pnj.getVida());
                //si el pnj muere
                if(pnj.isDead())
                {
                    //el pnj muere dropeando un item en la habitacion
                    currentRoom.addItem(pnj.getItem());
                    System.out.println("Has matado a " + pnj.getNombre() + " y encuentras en sus pertencias "
                        + "\n" + pnj.getItem().toString());
                    currentRoom.removePnj(pnj);
                    isCombats = false;
                }
            }
            else if(tiradaPlayer == tiradaPnj)
            {
                System.out.println(pnj.getNombre() + " ha bloqueado tu ataque");
                //se reduce la durabilidad del arma
                arma.disminuirDurabilidad();
                System.out.println("Tu arma pierde un punto de durabilidad");
                System.out.println("Durabilidad restante :" + arma.getDurabilidad());
                if(arma.estaRota()) //se rompe el arma
                {
                    System.out.println("Tu " + arma.getNombre() + " se ha roto!!");
                }
            }
            else
            {
                //player pierde el asalto
                quitarVida();
                //se reduce la durabilidad de la armadura
                armadura.disminuirDurabilidad();
                if(armadura.estaRota())
                {
                    System.out.println("Tu " + armadura.getNombre() + " se ha roto!!");
                }
                //si el pnj saca una tirada critica hace dos puntos de daño al player
                if(pnj.atacar() == TIRADA_CRITICA)
                {
                    System.out.println("CRITICO!!");
                    System.out.println(pnj.getNombre() + " ha encontrado un punto vital y "
                        + "te ha quitado dos puntos de vida");
                    quitarVida();
                }
                else
                {
                    System.out.println(pnj.getNombre() + " te quita una vida");
                }
                System.out.println("Vida actual : " + vida);
                //si el player muere
                if(isDead)
                {
                    System.out.println("Has sido derrotado por " + pnj.getNombre());
                    System.out.println("GAME OVER");
                }
            }
        }
        else
        {
            System.out.println("el objetivo al que atacas no existe");
        }
        return isDead;
    }

    /**
     * Metodo que permirte al player huir de un combate
     * @param idPnj es el numero identificativo del pnj del que se quiere huir
     * @return true si el player muere en la huida,false si escapa vivo
     */
    public boolean huir(int idPnj)
    {
        //primero se comprueba si esta en combate
        if(isCombats)
        {
            //se comprueba si existe el pnj del que se quiere huir
            Pnj pnj = currentRoom.getPnj(idPnj);
            if(pnj != null)
            {
                isCombats = false;
                //el pnj tiene derecho a un ataque de oportunidad
                if(pnj.atacar() >= ATAQUE_OPORTUNIDAD)
                {

                    quitarVida();
                    System.out.println("En tu huida " + pnj.getNombre() + " ha atacado un punto debil y"
                        + " te quita 1 punto de vida");
                    System.out.println("Vida actual : " + vida);    
                    //si el player muere
                    if(isDead)
                    {
                        System.out.println("Has sido derrotado por " + pnj.getNombre());
                        System.out.println("GAME OVER");
                    }
                }
                else{
                    System.out.println("Consigues huir ileso y vuelves a la habitacion anterior");
                }
                //se huye a la habitacion anterior
                backRoom();
            }
            else
            {
                System.out.println("No puedes huir de algo que no existe");
            }
        }
        else
        {
            System.out.println("Para que quieres huir si no estas en combate");
        }
        return isDead;
    }

    /**
     * Metodo que permite al player un determinado item
     * @param idItem es el numero identificador del item que se quiere beber
     */
    public void beber(int idItem)
    {
        //primero se comprueba que el jugador tiene ese item en su inventario
        Item item = null;
        boolean encontrado = false;
        int i = 0;
        while(i < items.size() && !encontrado)
        {
            if(items.get(i).getId() == idItem)
            {
                //si nombre pasado por parametro coincide con el nombre de algun item
                //que tiene el jugador en su inventario
                item = items.get(i);
                encontrado = true;
            }
            i++;
        }
        if(encontrado)
        {
            //se comprueba que ese item se puede beber
            if(item.sePuedeBeber())
            {
                Random rnd = new Random();
                int curacion = rnd.nextInt(3) + 1;
                vida += curacion;
                if(vida > vidaInicial)
                {
                    vida = vidaInicial;
                }
                items.remove(item);
                System.out.println("Tras beber la pocion te has curado " + curacion + " puntos de vida");
                System.out.println("Vida actual : " + vida);
            }
            else
            {
                System.out.println("Ese item no se puede beber");
            }
        }
        else
        {
            System.out.println("No puedes beberte un item que no tienes");
        }
    }

    /**
     * Metodo que calcula el bono de ataque que tiene el player
     * @return el bono de ataque
     */
    public int bonoAtaque()
    {
        return arma.getBonoAtaque() + armadura.getBonoAtaque();
    }

    /**
     * Metodo qeu calcula el bono de defensa que tiene el player
     * @return el bono de defensa
     */
    public int bonoDefensa()
    {
        return arma.getBonoDefensa() + armadura.getBonoDefensa();
    }

    /**

     * Metodo que permite equipar al player un objeto de tipo equipo existente en la habitacion
     * @param idEquipo es el numero identificativo del equipo
     * 
     */
    public void equipar(int idEquipo)
    {
        //primero se comprueba si el objeto que se quiere coger existe en la habitacion
        Equipo objEquipo = currentRoom.getEquipo(idEquipo);

        if(objEquipo != null)
        {
            //se comprueba si al coger el equipo se supera el limite de peso
            if(objEquipo.getPeso() + peso <= pesoMax)
            {
                //se comprueba si el equipo es arma o armadura
                if(objEquipo.EsArma())
                {
                    //se comprueba si ya tiene equipada un arma
                    if(arma != null) //tiene ya equipada un arma
                    {

                        intercambiarEquipo(arma,objEquipo);
                    }
                    else //no tiene un arma equipada
                    {
                        arma = objEquipo;
                        System.out.println("Te has equipado " + objEquipo.getNombre());
                        //se elimina de la habitacion
                        currentRoom.removeEquipo(objEquipo);
                    }
                }
                else //es una armadura
                {
                    //se comprueba si ya tiene equipado una armadura
                    if(armadura != null) //ya tiene una armadura equipada
                    {
                        intercambiarEquipo(armadura,objEquipo);
                    }
                    else
                    {
                        armadura = objEquipo;
                        System.out.println("Te has equipado " + objEquipo.getNombre());
                        //se elimina la armadura de la habitacion
                        currentRoom.removeEquipo(objEquipo);
                    }
                }
            }
            else //supera el limite de peso
            {
                System.out.println("No puedes coger ese objeto porque superas tu limite de peso maximo");
                System.out.println("El peso que llevas ahora es " + peso);
            }
        }
        else
        {
            System.out.println("El equipo que quieres coger no existe");
        }

    }

    /**
     * Metodo que muestra los objetos que lleva equipado el player
     */
    public String mostrarEquipo()
    {
        String equipo ="Tienes equipado :\n";
        if(arma == null)
        {
            equipo += "Arma :  vacio\n";
        }
        else
        {
            equipo += "Arma : " + arma.getNombre() + "\n";
        }
        if(armadura == null)
        {
            equipo += "Armadura :  vacio";
        }
        else
        {
            equipo +="Armadura : " + armadura.getNombre();
        }
        return equipo;
    }

    /**
     * Metodo que intercambia un objeto equipado por otro pasado por parametro
     * @param itemEquipado es el item que se quiere quitar
     * @param itemAEquipar es el item que se quiere equipar
     */
    public void intercambiarEquipo(Equipo itemEquipado,Equipo itemAEquipar)
    {
        //primero se suelta el objeto equipada en la habitacion
        Equipo drop = itemEquipado;
        System.out.println("Has soltado al suelo " + drop.getNombre());
        currentRoom.addEquipo(drop);
        //ya esta el arma vacia y se equipa el nuevo arma
        itemEquipado = itemAEquipar;
        System.out.println("Te has equipado " + itemAEquipar.getNombre());
        //se elimina de la habitacion
        currentRoom.removeEquipo(itemAEquipar);
    }

    /**
     * Metodo que devuelve si el player ha logrado el objetivo del juego
     * @return true si ha logrado el objetivo,false si no lo ha logrado
     */
    public boolean isEndGame()
    {
        return endGame;
    }
}
