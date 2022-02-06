import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class performs necessary
 * database interactions
 * to store and retrieve
 * vital player information
*/
public class PlayerDB implements DAO<Player> {
    
    private Connection connection;
    
    /**
     * Default constructor
    */
//    public PlayerDB() throws SQLException{
//        String dbUrl = "jdbc:sqlite:players.sqlite";
//        connection = DriverManager.getConnection(dbUrl);
//    }
    public PlayerDB(){
        try {
            String dbUrl = "jdbc:sqlite:players.sqlite";
            connection = DriverManager.getConnection(dbUrl);        
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    /**
     * Attempt to sever database connection
    */
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    /**
     * Private method that
     * retrieves player information
    */
    private Player getPlayerFromRow(ResultSet rs) throws SQLException {
        String name = rs.getString(1);
        double money = rs.getDouble(2);

        Player player = new Player(name, money);        
        return player;
    }

    /**
     * Get all players from database
     * @return players if player was retrieved
     * @return null if SQL exception thrown
    */
    @Override
    public List<Player> getAll() {
        String sql = "SELECT name, money "
                    + "FROM Players ORDER BY Name ASC";
        List<Player> players = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Player p = getPlayerFromRow(rs);
                players.add(p);
            }
            return players;
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Get single player from database
     * @param player
     * @return player if found
     * @return null if player not found
     * @return null if SQL exception thrown
    */
    @Override
    public Player get(String player) {
        String sql = "SELECT name, money "
                    + "FROM Players "
                    + "WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Player p = getPlayerFromRow(rs);
                rs.close();
                return p;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Add player to database
     * @param p player object
     * @return true if player added correctly
     * @return false if SQL exception thrown
    */
    @Override
    public boolean add(Player p) {
        String sql = "INSERT INTO Players (name, money) "
                    + "VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getPlayer());
            ps.setDouble(2, p.getMoney());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Remove player from database
     * @param p player object
     * @return true if player deleted correctly
     * @return false if SQL exception thrown
    */
    @Override
    public boolean delete(Player p) {
        String sql = "DELETE FROM Players "
                    + "WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getPlayer());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Update players in player object
     * @param p player object
     * @return true if players updated correctly
     * @return false if SQL exception thrown
    */
    @Override
    public boolean update(Player p) {
        String sql = "UPDATE Players SET "
                    + "  money = ? "
                    + "WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, p.getMoney());
            ps.setString(2, p.getPlayer());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}