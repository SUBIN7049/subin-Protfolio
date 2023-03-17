package com.code.bowwow.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.bowwow.entity.cart;
import com.code.bowwow.entity.cart_detail;
import com.code.bowwow.entity.order;
import com.code.bowwow.entity.order_detail;
import com.code.bowwow.entity.product;
import com.code.bowwow.entity.review;
import com.code.bowwow.entity.user;
import com.code.bowwow.service.bowwowService;

@Controller
public class serviceController {
	
	@Autowired
	private bowwowService bowwowService;
	
	@GetMapping("/")
	public String showHome(Model theModel) {
		List<product> theProducts = bowwowService.getProductsDESC();
		theModel.addAttribute("products",theProducts);
		return "home";
	}
	
	@GetMapping("/search")
	public String SearchProduct(@RequestParam("searchName") String theName, String search, Model theModel) {
		List<product> theProducts = bowwowService.searchProducts(theName, search);
		theModel.addAttribute("products", theProducts);
	return "shop/product_list";
	}
	
	@GetMapping("/detail")
	public String detailProduct(@RequestParam("productNum") int proNum, Principal p, Model theModel) {
		List<product> theProducts = bowwowService.detailProducts(proNum);
		List<review> thereview = bowwowService.getReviews(proNum);
		//double averageScore = bowwowService.calAverageScore(proNum);
		//bowwowService.avgScore(averageScore, proNum);
		String userName = bowwowService.getUser(p.getName()).get(0).getUserName();
		theModel.addAttribute("username",userName);
		theModel.addAttribute("products", theProducts);
		theModel.addAttribute("reviews", thereview);
		//theModel.addAttribute("score", Math.round(averageScore*10)/10.0);
	return "shop/detail";
	}
	
	@GetMapping("/vip")
	public String vipProduct(Model theModel) {
		List<product> theProducts = bowwowService.vipProducts();
		theModel.addAttribute("products", theProducts);
	return "shop/vip";
	}
	
	@GetMapping("/sale")
	public String saleProduct(Model theModel) {
		List<product> theProducts = bowwowService.saleProducts();
		theModel.addAttribute("products", theProducts);
	return "shop/sale";
	}
	
	@GetMapping("/add")
	public String order(@RequestParam("product_count")int proCount, @RequestParam("product_name")String proName, order_detail s, Principal p, Model theModel) {
		
		order o = new order(p.getName());
		bowwowService.saveOrder(o);
		s.setOrder_num(bowwowService.orderNum(o)); 
		bowwowService.savaOrderDetail(s);
		
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName());
		theModel.addAttribute("orderlist",theOD);
		bowwowService.minusStock(proCount,proName);
		
		return "redirect:/orderlist?";
	}
	
	@GetMapping("/orderlist")
	public String orderList(Principal p, Model theModel) {
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName());
		theModel.addAttribute("orderlist",theOD);

		List<order_detail> theOD2 = bowwowService.myOrderlist2(p.getName());
		theModel.addAttribute("orderlist2",theOD2);
		
		return "shop/history";
	}
	
	@PostMapping("/review_add")
	public String review_add(Principal p, review review, product product) {
		List<user> theUser = bowwowService.getUser(p.getName());
		review.setUsername(theUser.get(0).getUserName());
		System.out.println(review);
		bowwowService.addReview(review);
		double averageScore = bowwowService.calAverageScore(product.getProduct_num());
		bowwowService.avgScore(averageScore, product.getProduct_num());
	
		return "redirect:/detail?productNum="+review.getProduct_num();
	}
	
	
	@GetMapping("/cancel")
	public String cancel(@RequestParam("order_detail_num") ArrayList<Integer> theOrderNums, Principal p, Model theModel) {
		bowwowService.cancelOrder(theOrderNums);//order_state 업데이트
		List<order_detail> canclelist = bowwowService.cancelList(theOrderNums);
		for(int i=0; i<canclelist.size(); i++) {
			bowwowService.plusStock(canclelist.get(i).getProduct_count(),canclelist.get(i).getProduct_name());
		}
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName());
		theModel.addAttribute("orderlist",theOD);

		List<order_detail> theOD2 = bowwowService.myOrderlist2(p.getName());
		theModel.addAttribute("orderlist2",theOD2);
		
		return"shop/history";
	}
	
	@GetMapping("/cart")
	public String userCart(Principal p, cart cart, Model theModel) {
		int cart_num = bowwowService.getCartNum(p.getName());
		if(cart_num != 0) {
			cart.setCart_num(cart_num);
			List<cart_detail> cart_detail = bowwowService.getUserCart(cart.getCart_num());
			if(cart_detail.isEmpty()) {
				theModel.addAttribute("users_cart", "null");
			}else {
				theModel.addAttribute("users_cart", cart_detail);
			}
		}else if(cart_num == 0) {
			theModel.addAttribute("users_cart", "null");
		}
		return "shop/user_cart";
	}
	
	@GetMapping("/add_cart")
	public String addCart(cart_detail cart_detail, Principal p, cart cart, Model theModel) {
		int cart_num = bowwowService.getCartNum(p.getName());
		if(cart_num != 0) {
			cart_detail.setCart_num(cart_num);
			bowwowService.addCartDetail(cart_detail);
		}else if(cart_num == 0) {
			cart.setUser_mail(p.getName());
			bowwowService.createCart(cart);
			cart_detail.setCart_num(cart_num);
			bowwowService.addCartDetail(cart_detail);
		}
		List<cart_detail> theCart = bowwowService.getUserCart(cart_num);
		theModel.addAttribute("users_cart",theCart);
		
		return "redirect:/cart?";
	}
	
	
	@GetMapping("/delete_cart")
	public String deleteCart(@RequestParam("cart_detail_num") ArrayList<Integer> theCartDetailNums) {
		bowwowService.deleteCartItem(theCartDetailNums);
		return "redirect:/cart?";
	}
	
	@GetMapping("/buy_cart")
	public String buy_cart(@RequestParam("cart_detail_num") ArrayList<Integer> theCartDetailNums, order_detail order_detail, Principal p, Model theModel) {
		order o = new order(p.getName());
		bowwowService.saveOrder(o);
		List<cart_detail> addNewOrder = bowwowService.getBuyList(theCartDetailNums);
		for(int i=0; i < addNewOrder.size(); i++) {
			order_detail.setOrder_num(bowwowService.orderNum(o));
			order_detail.setProduct_name(addNewOrder.get(i).getProduct_name());
			order_detail.setProduct_count(addNewOrder.get(i).getProduct_count());
			order_detail.setProduct_price(addNewOrder.get(i).getProduct_price());
			bowwowService.savaOrderDetail(order_detail);
			bowwowService.minusStock(addNewOrder.get(i).getProduct_count(),addNewOrder.get(i).getProduct_name());
		}
		bowwowService.deleteCartItem(theCartDetailNums);
		List<order_detail> theOD = bowwowService.myOrderlist(p.getName());
		theModel.addAttribute("orderlist",theOD);
		List<order_detail> theOD2 = bowwowService.myOrderlist2(p.getName());
		theModel.addAttribute("orderlist2",theOD2);
		return "shop/history";
	}
	
	//리뷰버튼
	  @GetMapping("/review_btn")
	   public String reviewDelete(Principal p,
	         @RequestParam(value="delete",required=false)String delete, //삭제
	         @RequestParam(value="update",required=false)String update, //수정
	         review review, product product, //리뷰객체가져오기위한셋팅
	         Model theModel) {

	      if(delete == "delete" || update == null || delete != null) {
	         bowwowService.reviewDelete(review);  //선택한 리뷰 삭제
	         double averageScore = bowwowService.calAverageScore(product.getProduct_num()); //해당제품의 평점 출력
	         bowwowService.avgScore(averageScore, product.getProduct_num()); //출력받은 제품의 평점 업데이트
	      }
	      else{
	         bowwowService.reviewModify(review); //선택한 리뷰 comment 수정
	      }
	      
	      return "redirect:/detail?productNum="+review.getProduct_num();
	   }
	
	
}
