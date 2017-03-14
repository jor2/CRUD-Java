/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author Jordan
 */
public class GameOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;

    // This method opens a connection to the Oracle database
    public Connection openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

//          Tallaght
//            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//            ods.setUser("x00129654");
//            ods.setPassword("password");

//            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//            ods.setUser("x00130180");
//            ods.setPassword("password");

//          Home Oracle XE
//            ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
//            ods.setUser("walsh");
//            ods.setPassword("password");

            ods.setURL("jdbc:oracle:thin:@localhost:1521:XE");
            ods.setUser("system");
            ods.setPassword("password");

            conn = ods.getConnection();
            System.out.println("Connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
        return conn;
    }

    public void dropGamesTable() {
        System.out.println("Checking for existence of Game table");
        try {
            String s1 = "DROP TABLE Game";
            pstmt = conn.prepareStatement(s1);
            try {
                // Drop the Contacts table.
                pstmt.execute();
                System.out.println("Games table dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Game table");
        }

    }

    public void dropGenreTable() {
        System.out.println("Checking for existence of Genre table");
        try {
            String s1 = "DROP TABLE Genre";
            pstmt = conn.prepareStatement(s1);
            try {
                pstmt.execute();
                System.out.println("Genre table dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Genre table");
        }
    }

    public void dropPCTable() {
            System.out.println("Checking for existence of PC table");
            try {
                String s1 = "DROP TABLE PC";
                pstmt = conn.prepareStatement(s1);
                try {
                    pstmt.execute();
                    System.out.println("PC table dropped");
                } catch (SQLException ex) {
                    // No need to report an error.
                    // The table does not exist.
                }
            } catch (SQLException ex) {
                System.out.println("Problem dropping the PC table");
            }
    }
    
    public void dropConsoleTable() {
        System.out.println("Checking for existence of Console table");
        try {
            String s1 = "DROP TABLE Console";
            pstmt = conn.prepareStatement(s1);
            try {
                pstmt.execute();
                System.out.println("Console table dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Console table");
        }
    }

    public void dropGenreSequence() {
        try {
            String s2 = "drop sequence genId_seq";
            pstmt = conn.prepareStatement(s2);
            try {
                pstmt.execute();
                System.out.println("Genre Sequence dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Genre sequence");
        }
    }
        public void dropGameSequence() {
        try {
            String s2 = "drop sequence gameId_seq";
            pstmt = conn.prepareStatement(s2);
            try {
                pstmt.execute();
                System.out.println("Game Sequence dropped");
            } catch (SQLException ex) {
                // No need to report an error.
                // The sequence does not exist.
            }
        } catch (SQLException ex) {
            System.out.println("Problem dropping the Game sequence");
        }
    }
    public void createGenreSequence() {
        // Creating a sequence    
        try {
            String createseq1 = "create sequence genId_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq1);
            pstmt.executeUpdate();
            System.out.println("Genre Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with creating Genre Sequence " + ex.getMessage());
        }

    }

    public void createGameSequence() {
        // Creating a sequence    
        try {
            String createseq2 = "create sequence gameId_seq increment by 1 start with 1";
            pstmt = conn.prepareStatement(createseq2);
            pstmt.executeUpdate();
            System.out.println("Game Sequence created");
        } catch (SQLException ex) {
            System.out.print("Problem with creating Game Sequence " + ex.getMessage());
        }
    }


    public void CreateGenreTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Genre "
                    + "(genre_Id NUMBER NOT NULL, genre_Name VARCHAR2(30), PRIMARY KEY(genre_Id))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            System.out.println("Genre table created");
        } catch (SQLException e) {
            System.out.print("SQL Exception creating and inserting values into Genre " + e.getMessage());
            System.exit(1);
        }
    }

    public void createGameTable() {
        // Create a Table           
        try {
            String create = "CREATE TABLE Game "
                    + "(game_id NUMBER PRIMARY KEY NOT NULL, age_rating VARCHAR2(10), name VARCHAR2(30), publisher VARCHAR2(30), platform VARCHAR2(30), genre_id NUMBER NOT NULL, foreign key (GENRE_ID) REFERENCES GENRE(GENRE_ID))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            System.out.println("Game table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into Game" + ex.getMessage());
        }
    }
    
    public void createConsoleTable() {
        // Create a Table           
        try {
            String create = "CREATE TABLE CONSOLE "
                    + "(game_id NUMBER PRIMARY KEY NOT NULL, release_date DATE, price DECIMAL(5,2), quantity NUMBER NOT NULL, FOREIGN KEY (game_id) REFERENCES GAME(game_id))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            System.out.println("Console table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into Console" + ex.getMessage());
        }
    }

    
    public void createPCTable() {
        // Create a Table           
        try {
            String create = "CREATE TABLE PC "
                    + "(GAME_ID NUMBER NOT NULL PRIMARY KEY, release_date DATE, price DECIMAL (5,2), quantity NUMBER NOT NULL, bonus_content VARCHAR2(30), platform VARCHAR2(30), FOREIGN KEY (GAME_ID) REFERENCES game (game_id))";
            pstmt = conn.prepareStatement(create);
            pstmt.executeUpdate();

            System.out.println("PC table created");
        } catch (SQLException ex) {
            System.out.println("SQL Exception creating and inserting values into PC" + ex.getMessage());
        }
    }

    public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    
    public static void main(String[] args) {
        GameOperations co = new GameOperations();
        co.openDB();
        co.dropGameSequence();
        co.dropGenreSequence();

        co.dropPCTable();
        co.dropConsoleTable();
        co.dropGamesTable();
        co.dropGenreTable();

        co.createGenreSequence();
        co.createGameSequence();

        co.CreateGenreTable();
        co.createGameTable();
        co.createConsoleTable();
        co.createPCTable();
    }
}
