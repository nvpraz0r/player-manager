import java.text.NumberFormat;

/**
 * This class creates and holds
 * player information
*/
public class Player {

    private String player;
    private double money;

    /**
     * Default player constructor
    */
    public Player() {}

    /**
     * Player constructor
     * @param player player's name
     * @param money player's money amount
    */
    public Player(String player, double money) {
        this.player = player;
        this.money = money;
    }

    /**
     * Set player name
     * @param player player's name
    */
    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Get player name
     * @return player name
    */
    public String getPlayer() {
        return player;
    }

    /**
     * Set player money amount
     * @param player player's name
     * @param money player's money amount
    */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Get player money amount
     * @return player money amount
    */
    public double getMoney() {
        return money;
    }

    /**
     * Get player money amount in currency format
     * @return player money amount formatted
    */
    public String getMoneyFormatted() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(money);
    }
}
