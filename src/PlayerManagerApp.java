import java.util.List;

/**
 * Main class
 */
public class PlayerManagerApp {

    private static PlayerDB playerDB = new PlayerDB();

    public static void main(String[] args) {
        System.out.println("Welcome to the Product Manager\n");
        displayMenu();

        // perform 1 or more actions
        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            // get the input from the user
            action = Console.getString("Enter a command: ");
            System.out.println();

            //menu
            if (action.equalsIgnoreCase("list")) {
                displayAllPlayers();
            } else if (action.equalsIgnoreCase("add")) {
                addPlayer();
            } else if (action.equalsIgnoreCase("update")) {
                updatePlayer();
            } else if (action.equalsIgnoreCase("del") || 
                       action.equalsIgnoreCase("delete")) {
                deletePlayer();
            } else if (action.equalsIgnoreCase("help") || 
                       action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                exit();
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }

    /**
     * Display menu for user interaction
     */
    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all players");
        System.out.println("add     - Add a player");
        System.out.println("update  - Update a player");
        System.out.println("del     - Delete a player");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    /**
     * Display information
     * regarding all players
     * in database
     */
    public static void displayAllPlayers() {
        System.out.println("Player List");

        List<Player> products = playerDB.getAll();
        if (products == null) {
            System.out.println("Error! Unable to get players.\n");
        } else {
            Player p;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < products.size(); i++) {
                p = products.get(i);
                sb.append(StringUtils.padWithSpaces(
                        p.getPlayer(), 8));
                sb.append(p.getMoneyFormatted());
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * Add player to database
     */
    public static void addPlayer() {
        //prompt user for information
        String playerName = Console.getString("Enter player's name: ");
        double money = Console.getDouble("Enter player's amount of money: ");

        //add information to player object
        Player player = new Player();
        player.setPlayer(playerName);
        player.setMoney(money);

        //return results
        boolean success = playerDB.add(player);
        if (success) {
            System.out.println(player
                    + " has been added to the database.\n");
        } else {
            System.out.println("Error! Unable to add player.\n");
        }
    }

    /**
     * Update all players
     */
    public static void updatePlayer() {
        String playerName = Console.getString("Enter player name to update: ");
        
        Player player = playerDB.get(playerName);
        if (player == null) {
            System.out.println("No player name matches that player.\n");
            return;
        } 
        
        double price = Console.getDouble("Enter amount to be adjusted: ");

        player.setMoney(price);

        boolean success = playerDB.update(player);
        if (success) {
            System.out.println(playerName 
                    + " has been updated in the database.\n");
        } else {
            System.out.println("Error! Unable to upldate player.\n");
        }

    }

    /**
     * Delete player
     */
    public static void deletePlayer() {
        String playerName = Console.getString("Enter player name to delete: ");

        Player player = playerDB.get(playerName);
        if (player != null) {
            boolean success = playerDB.delete(player);
            if (success) {
                System.out.println(player.getPlayer()
                        + " has been deleted from the database.\n");
            } else {
                System.out.println("Error! Unable to delete player.\n");
            }
        } else {
            System.out.println("No product matches that player.\n");
        }
    }
    
    /**
     * Exit application
     */
    public static void exit() {
        playerDB.closeConnection();
        System.out.println("Closing application.\n");        
    }
}