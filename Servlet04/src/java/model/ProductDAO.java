/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author ho huy
 */
public class ProductDAO {

    //contructor
    public ProductDAO() {
    }

    //map to product
    private ProductDTO mapToPrd(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        double price = rs.getDouble("price");
        String size = rs.getString("size");
        boolean status = rs.getBoolean("status");
        String image = rs.getString("image");
        return new ProductDTO(id, name, description, image, price, size, status);
    }

    //base CRUD
    public boolean create(ProductDTO product) {
        String sql = "INSERT INTO tblProducts\n"
                + "           ([id]\n"
                + "           ,[name]\n"
                + "           ,[description]\n"
                + "           ,[image]\n"
                + "           ,[price]\n"
                + "           ,[size]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try ( Connection conn = DbUtils.getConnection();) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getId());
            st.setString(2, product.getName());
            st.setString(3, product.getDescription());
            st.setString(4, product.getImage());
            st.setDouble(5, product.getPrice());
            st.setString(6, product.getSize());
            st.setBoolean(7, product.isStatus());

            return st.executeUpdate() != 0;
        } catch (Exception e) {
        }
        return false;
    }

    private List<ProductDTO> retrieve(String condition, Object... params) {
        String sql = "SELECT *\n"
                + "  FROM tblProducts\n"
                + "  WHERE " + condition;

        try ( Connection conn = DbUtils.getConnection();) {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = null;

            for (int i = 0; i < params.length; i++) {
                st.setObject(i + 1, params[i]);
            }

            rs = st.executeQuery();
            List<ProductDTO> productList = new ArrayList<>();

            while (rs.next()) {
                ProductDTO product = mapToPrd(rs);
                productList.add(product);
            }

            return productList;
        } catch (Exception e) {
        }
        return null;
    }

    public boolean update(ProductDTO product) {
        String sql = "UPDATE tblProducts\n"
                + "   SET [name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[size] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE id = ?";

        try ( Connection conn = DbUtils.getConnection();) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, product.getName());
            st.setString(2, product.getDescription());
            st.setString(3, product.getImage());
            st.setDouble(4, product.getPrice());
            st.setString(5, product.getSize());
            st.setBoolean(6, product.isStatus());
            st.setString(7, product.getId());

            return st.executeUpdate() != 0;
        } catch (Exception e) {
        }
        return false;
    }

    private boolean delete(String id) {
        String sql = "DELETE FROM tblProducts WHERE id = ?";

        try ( Connection conn = DbUtils.getConnection();) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            return st.executeUpdate() != 0;
        } catch (Exception e) {
        }
        return false;
    }

    //advanced CRUD
    private List<ProductDTO> retrieveByStatus(String condition, Object... params) {
        return retrieve("status = 1 AND " + condition, params);
    }

    public ProductDTO retrieveById(String id) {
        return retrieve("id = ?", id).get(0);
    }

    public List<ProductDTO> retrieveByName(String name) {
        return retrieveByStatus("name LIKE ?", "%" + name + "%");
    }

    public static void main(String[] args) {
        ProductDAO pdao = new ProductDAO();
        System.out.println(pdao.retrieveByName(""));
    }
}
