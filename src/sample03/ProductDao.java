package sample03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.ConnUtils;

public class ProductDao {

	// 신규 상품정보를 저장하는 기능
	public void insertProduct(Product product) throws SQLException {
		String sql = "insert into sample_products "
				+ "(product_no, product_name, product_maker, "
				+ "product_price, product_discount_rate, product_stock) "
				+ "values "
				+ "(sample_product_seq.nextval, ?, ?, ?, ?, ?)";
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, product.getName());
		pstmt.setString(2, product.getMaker());
		pstmt.setInt(3, product.getPrice());
		pstmt.setDouble(4, product.getDiscountRate());
		pstmt.setInt(5, product.getStock());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		con.close();
		
	}
	
    // 모든 상품정보를 조회해서 반환하는 기능
	public List<Product> getAllProducts() throws SQLException {
		String sql = 
				"select product_no, "
				+ "		product_name, "
				+ "		product_maker, "
				+ "		product_price, "
				+ "		product_discount_rate, "
				+ "		product_stock,product_create_date "
				+ "from sample_products "
				+ "order by product_no desc "; // 최신정보를 보기 위해 내림차순 

		List<Product> products = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		// execute 메소드는 insert, delete, update를 실행할 수 있는 메소드다.
		
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Product product = new Product();
			product.setNo(rs.getInt("product_no"));
			product.setName(rs.getNString("product_name"));
			product.setMaker(rs.getString("product_maker"));
			product.setDiscountRate(rs.getDouble("product_discount_rate"));
			product.setStock(rs.getInt("product_stock"));
			product.setCreateDate(rs.getDate("product_create_date"));
			
			products.add(product);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return products;
	}
	
    // 상품번호를 전달받아서 해당하는 상품정보를 반환하는 기능
	public Product getProductByNo(int productNo) throws SQLException {
		String sql = 
				"select product_no, "
				+ "		product_name, "
				+ "		product_maker, "
				+ "		product_price, "
				+ "		product_discount_rate, "
				+ "		product_stock, "
				+ "		product_create_date "
				+ "from sample_products "
				+ "where product_no = ? ";
		
		Product product = null;
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
//			int no = rs.getInt("product_no");
//			String name = rs.getString("product_name");
//			String maker = rs.getString("product_maker");
//			int price = rs.getInt("product_price");
//			double discountRate = rs.getDouble("product_discount_rate");
//			int stock = rs.getInt("product_stock");
//			Date createDate = rs.getDate("product_create_date");
//			product = new Product();
//			product.setNo(no);
//			product.setName(name);
//			product.setMaker(maker);
//			product.setPrice(price);
//			product.setDiscountRate(discountRate);
//			product.setStock(stock);
//			product.setCreateDate(createDate);
			// 위의 코드를 줄인 코드
			product = new Product();
			product.setNo(rs.getInt("product_no"));
			product.setName(rs.getString("product_name"));
			product.setMaker(rs.getString("product_maker"));
			product.setPrice(rs.getInt("product_price"));
			product.setDiscountRate(rs.getDouble("product_discount_rate"));
			product.setStock(rs.getInt("product_stock"));
			product.setCreateDate(rs.getDate("product_create_date"));
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return product;
		
	}
	
    // 최소가격, 최대가격을 전달받아서 해당 가격범위에 포함된 상품정보를 반환하는 기능
	public List<Product> getProductsByPrice(int minPrice, int maxPrice) throws SQLException {
		String sql = 
				"select product_no, "
				+ "		product_name, "
				+ "		product_maker, "
				+ "		product_price, "
				+ "		product_discount_rate, "
				+ "		product_stock, "
				+ "		product_create_date "
				+ "from sample_products "
				+ "where product_price between ? and ? "
				+ "order by product_price asc ";
		
		List<Product> products = new ArrayList<>();
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, minPrice);
		pstmt.setInt(2, maxPrice);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			int no = rs.getInt("product_no");
			String name = rs.getString("product_name");
			String maker = rs.getString("product_maker");
			int price = rs.getInt("product_price");
			double discountRate = rs.getDouble("product_discount_rate");
			int stock = rs.getInt("product_stock");
			Date createDate = rs.getDate("product_create_date");
			
			Product product = new Product();
			product.setNo(no);
			product.setName(name);
			product.setMaker(maker);
			product.setPrice(price);
			product.setDiscountRate(discountRate);
			product.setStock(stock);
			product.setCreateDate(createDate);
			
			products.add(product);
		}
		
		rs.close();
		pstmt.close();
		con.close();
		
		return products;
	}
	
    // 상품번호를 전달받아서 상품정보를 삭제하는 기능
	public void deleteProduct(int productNo) throws SQLException {
		String sql = "delete from sample_products where product_no = ? ";
		
		Connection con = ConnUtils.getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		pstmt.executeUpdate();

		pstmt.close();
		con.close();
	}
	
}
