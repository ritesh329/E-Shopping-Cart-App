package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.cart;
import model.product;

public class productDao {

	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public productDao(Connection con) {

		this.con = con;

	}

	public List<product> getAllProducts() {
		List<product> products = new ArrayList<product>();

		try {

			query = "select * from productss";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				product row = new product();
//				    row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				products.add(row);

			}

		} catch (Exception e) {

			System.out.print("error");

		}

		return products;
	}

	public List<cart> getCardProducts(ArrayList<cart> cartList) {
		List<cart> products = new ArrayList<cart>();
		try {

			if (cartList.size() > 0) {

				for (cart item : cartList) {

					query = "select * from productss where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						cart row = new cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}

				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

		return products;
	}

	public product getSignleProduct(int id) {

		product row = null;

		try {

			query = "select * from productss where id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {
				row = new product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));

			}

		} catch (Exception e) {

		}
		return row;
	}

	public double getTotalCardPrice(ArrayList<cart> cartList) {

		double sum = 0;
		try {

			if (cartList.size() > 0) {
				for (cart item : cartList) {

					query = "select price from productss where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();

					while (rs.next()) {

						sum += rs.getDouble("price") * item.getQuantity();

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return sum;
	}
}
