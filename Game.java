
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player(10f,4);
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, salaPrincipal, salaTortura, armeria, cocina,despensa,calabozo,tunel,escaleras1,planta1;

        // create the rooms
        entrada = new Room("entrada de la torre",true);
        salaPrincipal = new Room("estas en la sala principal",true);
        salaTortura = new Room("entras a una sala en la que ves varios artilugios de tortura",true);
        armeria = new Room("llegas a la armeria",true);
        cocina = new Room("entras en lo que parece una cocina",true);
        despensa = new Room("te encuentras en la despensa",true);
        calabozo = new Room("entras al calabozo de la torre donde ves varias celdas vacias",true);
        tunel = new Room("has encontrado un tunel",true);
        escaleras1 = new Room("estas en las escaleras que suben al primer piso",true);
        planta1 = new Room("te encuentras en la planta 1 de la torre",true);

        Equipo espada = new Equipo(1,"espada","vieja y afilada",2.5f,true,3,0,10);
        Equipo hacha = new Equipo(2,"hacha","con un mango de madera",2.5f,true,4,0,10);
        Equipo escudo = new Equipo(3,"escudo","que tiene grabado un dragon",4f,false,0,3,20);
        Equipo escudoPaves = new Equipo(4,"escudo paves","que parece hecho por los enanos",8f,false,-2,5,20);
        Item mesa = new Item("mesa","llena de polvo",5f,false,2,false);
        Item antorcha = new Item("antorcha","de madera",1f,true,1,false);
        Item olla = new Item("olla","muy oxidada",9f,true,4,false);
        Item llave = new Item("llave","dorada y muy mordisqueada",0.3f,true,3,false);
        Item pocion1 = new Item("pocion","que identificas como curativa",1f,true,5,true);
        Item pocion2 = new Item("pocion","que identificas como curativa",1f,true,6,true);
        Item finJuego = new Item("objeto"," que finalizan el juego",0f,true,15,false);

        Pnj troll = new Pnj(1,"Troll","con una piel verde y escamosa y un fuerte mal olor",4,llave,false,3);
        Pnj kobold = new Pnj(2,"Kobold","con un sombrero pirata y segun te ve dice : Arrr!!",2,pocion2,false,1);
        tunel.addPnj(kobold);
        salaTortura.addPnj(troll);
        armeria.addEquipo(espada);
        armeria.addEquipo(hacha);
        armeria.addEquipo(escudo);
        armeria.addEquipo(escudoPaves);
        salaPrincipal.addItem(mesa);
        salaPrincipal.addItem(antorcha);
        cocina.addItem(olla);
        cocina.addItem(pocion1);
        planta1.addItem(finJuego);

        // initialise room exits
        entrada.setExit("north",salaPrincipal);
        salaPrincipal.setExit("north",armeria);
        salaPrincipal.setExit("east",calabozo);
        salaPrincipal.setExit("south",entrada);
        salaPrincipal.setExit("west",cocina);
        salaPrincipal.setExit("northWest",escaleras1);
        calabozo.setExit("west",salaPrincipal);
        calabozo.setExit("southEast",salaTortura);
        armeria.setExit("south",salaPrincipal);
        cocina.setExit("east",salaPrincipal);
        cocina.setExit("west",despensa);
        despensa.setExit("east",cocina);
        despensa.setExit("southEast",tunel);
        salaTortura.setExit("northWest",calabozo);
        tunel.setExit("northWest",despensa);
        escaleras1.setExit("southEast",salaPrincipal);
        escaleras1.setExit("north",planta1);
        planta1.setExit("south",escaleras1);

        player.setCurrentRoom(entrada); // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if(!finished)
            {
                finished = player.isEndGame();

            }
        }
        System.out.println("Thank you for playing.  Good bye.");

    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al Mundo de Zuul!");
        System.out.println("World of Zuul es un nuevo,increiblemente aburrido juego de aventuras.");
        System.out.println("Si necesitas ayuda,teclea " + Option.HELP.getComando());
        System.out.println();
        player.look();

    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No se lo que quieres decir....");
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch(commandWord)
        {
            case HELP :
            printHelp();
            break;
            case GO:
            go(command);
            break;
            case QUIT:
            wantToQuit = quit(command);
            break;
            case LOOK :
            player.look();
            break;
            case EAT:
            player.eat();
            break;
            case BACK:
            player.backRoom();
            break;
            case TAKE:
            take(command);
            break;
            case DROP:
            drop(command);
            break;
            case ITEMS:
            player.infoItems();
            break;
            case ATTACK:
            wantToQuit = attack(command);
            break;
            case FLEE:
            wantToQuit = flee(command);
            break;
            case DRINK:
            drink(command);
            break;
            case EQUIP:
            equip(command);
            break;
            case EQUIPMENT :
            System.out.println(player.mostrarEquipo());
            break;
        }
        return wantToQuit;
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.allCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quitar?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Metodo que intenta coger un item
     * @param command es el comando que indica que tiene que coger
     */
    private void take(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Coger que?");
            return ;
        }

        int name = Integer.parseInt(command.getSecondWord());
        if(!player.isCombats())
        {
            player.take(name);
        }
        else
        {
            System.out.println("No puedes coger nada estando en combate");
        }

    }

    /**
     * Metodo que intenta tirar,un objeto que tiene en el inventario,en la habitacion
     * @param command es el comando que indica que tiene que tirar
     */
    private void drop(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Tirar que?");
            return;
        }

        int item = Integer.parseInt(command.getSecondWord());
        if(!player.isCombats())
        {
            player.drop(item);
        }
        else
        {
            System.out.println("No puedes arrojar nada en combate");
        }

    }

    /**
     * Metodo que intenta ir en una direccion indica por el parametro,si direccion no es valida
     * muestra un mensaje de error por pantalla
     * @param command es el comando que indica la direccion a la que quiere ir
     */
    private void go(Command command)
    {      
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir donde?");
            return;
        }

        String direction = command.getSecondWord();
        if(!player.isCombats())
        {
            player.goRoom(direction);
        }
        else
        {
            System.out.println("No puedes irte en combate");
        }
    }

    /**
     * Metodo que intenta atacar a un pnj indicado por parametro,si el parametro no es valido muestra
     * mensaje por pantalla
     * @param command es el comando que indica el pnj al que se quiere atacar
     */
    private boolean attack(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Atacar que?");

        }

        int pnj = Integer.parseInt(command.getSecondWord());
        return player.atacar(pnj);

    }

    /**
     * Metodo por el cual el player puede huir de de un combate
     * @param command es el comando que indica de que pnj se quiere huir
     * @return true si el player muere en la huida,false en los demas casos
     */
    private boolean flee(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Huir de quien?");
            return false;
        }

        int pnj = Integer.parseInt(command.getSecondWord());
        return player.huir(pnj);

    }

    /**
     * Metodo por el cual el player puede beber un determinado item pasado por parametro
     * @param command es el comando que indica que item quiere beber
     */
    public void drink(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Beber que?");
            return;
        }
        int item = Integer.parseInt(command.getSecondWord());
        player.beber(item);

    }

    /**
     * Metodo por el cual el player se equipa un objeto pasado por parametro
     * @param command es el comando que indica lo que se quiere equipar
     */
    public void equip(Command command)
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Equipar que?");
            return;
        }

        int equipo = Integer.parseInt(command.getSecondWord());
        player.equipar(equipo);

    }
}
