package com.bowwow.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/product/check_subname")	//subName 중복체크
	public String checkUnique(@Param("id") Integer id, @Param("subName") String subName) {
		return service.isSubNameUnique(id, subName)? "OK" : "Duplicated";
	}
}
