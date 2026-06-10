/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package id.team1.inventory;

/**
 *
 * @author team1
 */
public class AppUser {

    private final int idUser;
    private final String username;
    private final String namaLengkap;

    public AppUser(int idUser, String username, String namaLengkap) {
        this.idUser = idUser;
        this.username = username;
        this.namaLengkap = namaLengkap;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getDisplayName() {
        if (namaLengkap == null || namaLengkap.isBlank()) {
            return username;
        }
        return namaLengkap;
    }

    public static AppUser defaultAdmin() {
        return new AppUser(1, "admin", "Administrator");
    }

    public static AppUser authenticate(
        java.sql.Connection sqlCon,
        String username,
        String password
    ) throws java.sql.SQLException {
        String sql = "SELECT IdUser, Username, NamaLengkap "
                + "FROM Users "
                + "WHERE Username = ? AND `Password` = ? "
                + "LIMIT 1";

        try (java.sql.PreparedStatement stmt = sqlCon.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (java.sql.ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return new AppUser(
                        res.getInt("IdUser"),
                        res.getString("Username"),
                        res.getString("NamaLengkap")
                    );
                }
            }
        }
        return null;
    }
}
