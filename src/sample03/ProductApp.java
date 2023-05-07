package sample03;

import java.sql.SQLException;
import java.util.List;

import util.KeyboardReader;

public class ProductApp {

	private KeyboardReader reader = new KeyboardReader();
	private ProductDao productDao = new ProductDao();
	
	public void menu() {
		System.out.println("-----------------------------------------------");
		System.out.println("1.전체조회  2.상세조회  3.검색  4.저장  5.삭제  0.종료");
		System.out.println("-----------------------------------------------");
		System.out.println();
		
		System.out.print("### 메뉴선택: ");
		int menuNo = reader.readInt();
		System.out.println();
		
		try {
			if (menuNo == 1) {
				전체조회();
			} else if (menuNo == 2) {
				상세조회();
			} else if (menuNo == 3) {
				검색();
			} else if (menuNo == 4) {
				저장();
			} else if (menuNo == 5) {
				삭제();
			} else if (menuNo == 0) {
				종료();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
				
		System.out.println();
		System.out.println();
		System.out.println();
		
		menu();
	}
	
	private void 전체조회() throws SQLException {
		System.out.println("<< 상품 전체 조회 >>");
		System.out.println("### 상품 전체 목록을 확인하세요.");
		
		List<Product> products = productDao.getAllProducts();
		
		if (products.isEmpty()) {
			System.out.println("### 상품정보가 존재하지 않습니다.");
		} else {
			System.out.println("----------------------------------------------");
			System.out.println("상품번호\t상품이름\t가격\t할인가격");
			for (Product product : products) {
				System.out.print(product.getNo() + "\t");
				System.out.print(product.getName() + "\t");
				System.out.print(product.getPrice() + "\t");
				System.out.println(product.getDiscountPrice());
			}
			System.out.println("----------------------------------------------");			
		}
		
	}
	
	private void 상세조회() throws SQLException {
		System.out.println("<< 상품 상세정보 조회 >>");
		System.out.println("### 조회할 상품정보를 입력하세요.");
		
		System.out.print("### 상품번호: ");
		int productNo = reader.readInt();
		
		Product product = productDao.getProductByNo(productNo);
		if (product == null) {
			System.out.println("### ["+productNo+"]번 상품정보가 존재하지 않습니다.");
		} else {
			System.out.println("----------------------------------------------");
			System.out.println("상품 번호 : " + product.getNo());
			System.out.println("상품 이름 : " + product.getName());
			System.out.println("제조 회사 : " + product.getMaker());
			System.out.println("상품 가격 : " + product.getPrice());
			System.out.println("할인 비율 : " + product.getDiscountPercent());
			System.out.println("할인 가격 : " + product.getDiscountPrice());
			System.out.println("재고 수량 : " + product.getStock());
			System.out.println("등록 일자 : " + product.getCreateDate());
			System.out.println("----------------------------------------------");
		}
	} 
	
	private void 검색() throws SQLException {
		System.out.println("<< 상품 정보 검색 >>");
		System.out.println("### 가격을 입력해서 상품을 검색하세요.");

		System.out.print("### 최소가격: ");
		int min = reader.readInt();
		System.out.print("### 최대가격: ");
		int max = reader.readInt();
		
		List<Product> products = productDao.getProductsByPrice(min, max);
	
		if (products.isEmpty()) {
			System.out.println("### 상품정보가 존재하지 않습니다.");
		} else {
			System.out.println("가격 ["+min+"] ~ ["+max+"]");
			System.out.println("----------------------------------------------");
			System.out.println("상품번호\t상품이름\t가격\t할인가격");
			for (Product product : products) {
				System.out.print(product.getNo() + "\t");
				System.out.print(product.getName() + "\t");
				System.out.print(product.getPrice() + "\t");
				System.out.println(product.getDiscountPrice());
			}
			System.out.println("----------------------------------------------");			
		}
	}
	
	private void 저장() throws SQLException {
		
	}
	
	private void 삭제() throws SQLException {
		System.out.println("<< 상품 정보 삭제 >>");
		System.out.println("### 삭제할 상품번호를 입력하세요.");
		
		System.out.print("### 상품번호: ");
		int productNo = reader.readInt();
		
		productDao.deleteProduct(productNo);
		
		System.out.println("### 상품정보가 삭제되었습니다.");
	}
	
	private void 종료() throws SQLException {
		
	}
	
	public static void main(String[] args) {
		new ProductApp().menu();
	}
}
