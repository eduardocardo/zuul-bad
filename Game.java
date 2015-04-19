
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
        player = new Player(10f);
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, salaPrincipal, salaOscura, armeria, cocina,despensa,calabozo,tunel;

        // create the rooms
        entrada = new Room("entrada de la torre");
        salaPrincipal = new Room("estas en la sala principal");
        salaOscura = new Room("entras a una sala en la que no ves nada");
        armeria = new Room("llegas a la armeria");
        cocina = new Room("entras en lo que parece una cocina");
        despensa = new Room("te encuentras en la despensa");
        calabozo = new Room("entras al calabozo de la torre donde ves varias celdas vacias");
        tunel = new Room("has encontrado un tunel en el que no ves nada");
        Item espada = new Item("espada","vieja y afilada",2.5f,true);
        Item mesa = new Item("mesa","llena de polvo",5f,false);
        Item antorcha = new Item("antorcha","de madera",1f,true);
        Item olla = new Item("olla","muy oxidada",9f,true);
        armeria.addItem(espada);
        salaPrincipal.addItem(mesa);
        salaPrincipal.addItem(antorcha);
        cocina.addItem(olla);

        // initialise room exits
        entrada.setExit("north",salaPrincipal);
        salaPrincipal.setExit("north",armeria);
        salaPrincipal.setExit("east",calabozo);
        salaPrincipal.setExit("south",entrada);
        salaPrincipal.setExit("west",cocina);
        calabozo.setExit("west",salaPrincipal);
        calabozo.setExit("southEast",salaOscura);
        armeria.setExit("south",salaPrincipal);
        cocina.setExit("east",salaPrincipal);
        cocina.setExit("west",despensa);
        despensa.setExit("east",cocina);
        despensa.setExit("southEast",tunel);
        salaOscura.setExit("northWest",calabozo);
        tunel.setExit("northWest",despensa);

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
        }
        System.out.println("Thank you for playing.  Good bye.");

    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
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

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            go(command);
        }

        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look"))
        {
            player.look();
        }
        else if(commandWord.equals("eat"))
        {
            player.eat();
        }
        else if(commandWord.equals("back"))
        {
            player.backRoom();
        }
        else if(commandWord.equals("take"))
        {
                take(command);
        }
        else if(commandWord.equals("drop"))
        {
            drop(command);
        }
        else if(commandWord.equals("items"))
        {
            player.infoItems();
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
            System.out.println("Quit what?");
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
    public void take(Command command)
    {
         if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("take what?");
                return ;
            }

         String name = command.getSecondWord().toLowerCase();
         player.take(name);
    }
    
    /**
     * Metodo que intenta tirar,un objeto que tiene en el inventario,en la habitacion
     * @param command es el comando que indica que tiene que tirar
     */
    public void drop(Command command)
    {
          if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("drop what?");
                return;
            }
          String item = command.getSecondWord();
          player.drop(item);
    }
    
    /**
     * Metodo que intenta ir en una direccion indica por el parametro,si direccion no es valida
     * muestra un mensaje de error por pantalla
     * @param command es el comando que indica la direccion a la que quiere ir
     */
    public void go(Command command)
    {
         if(!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Go where?");
                return;
            }

         String direction = command.getSecondWord();
         player.goRoom(direction);
    }
}
