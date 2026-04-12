package persistence;

import entities.Bottom;
import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BottomMapper {

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) {

        List<Bottom> bottomList = new ArrayList<>();

        String sql = "SELECT * FROM public.bottoms";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("bottom_id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                bottomList.add(new Bottom(id, name, price));
            }
        } catch(SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
        return bottomList;
    }

    public static Bottom getBottomById(int id, ConnectionPool connectionPool) {

        Bottom b = null;

        String sql = "SELECT * FROM public.bottoms WHERE bottom_id = ?";

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    b = new Bottom(id, name, price);
                }
            }
        } catch(SQLException e) {
            throw new DatabaseException("DB fejl: " + e.getMessage());
        }
        return b;
    }
}