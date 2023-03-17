package com.bowwow.customer.inquiry;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.User;

@Service
@Transactional
public class InquiryService {

	public static final int INQUIRY_PER_PAGE = 5;
	
	@Autowired
	private InquiryRepository inquiryRepo;

	public Page<Inquiry> findParentInquiriesByProduct(int productId, Pageable pageable) {
	    return inquiryRepo.findByProduct_IdAndParentIsNullOrderByRegDateDesc(productId, pageable);
	}

	public void save(Inquiry inquiry) {
		inquiryRepo.save(inquiry);
		
	}

	public Inquiry findById(Integer id) {
		return inquiryRepo.findById(id).get();
	}

	public void deleteById(Integer id) {
		inquiryRepo.deleteById(id);
	}

	public List<Inquiry> findByUser(User user) {
		return inquiryRepo.findByUser(user);
	}

	public Page<Inquiry> findParentInquiriesByUser(Integer integer, Pageable pageable) {
		return inquiryRepo.findByUser_IdAndParentIsNullOrderByRegDateDesc(integer, pageable);
	}

	
	
	
	
	
//	public Inquiry createInquiry(InquiryRequest inquiryRequest) {
//	    Inquiry inquiry = new Inquiry();
//
//	    // 요청에서 받아온 값을 적절한 타입으로 변환하여 inquiry 객체에 설정합니다.
//	    inquiry.setComment(inquiryRequest.getComment());
//
//	    Product product = productService.getProductById(inquiryRequest.getProductId());
//	    inquiry.setProduct(product);
//
//	    User user = userService.getUserById(inquiryRequest.getUserId());
//	    inquiry.setUser(user);
//
//	    // 부모 문의가 있다면 연결합니다.
//	    if (inquiryRequest.getParentId() != null) {
//	        Inquiry parent = inquiryRepository.findById(inquiryRequest.getParentId())
//	                .orElseThrow(() -> new EntityNotFoundException("Parent Inquiry not found"));
//	        inquiry.setParent(parent);
//	    }
//
//	    return inquiryRepository.save(inquiry);
//	}
//
//	public Inquiry getInquiryById(Integer id) {
//	    return inquiryRepository.findById(id)
//	            .orElseThrow(() -> new EntityNotFoundException("Inquiry not found"));
//	}
//
//	public Inquiry updateInquiry(Integer id, InquiryRequest inquiryRequest) {
//	    Inquiry inquiry = inquiryRepository.findById(id)
//	            .orElseThrow(() -> new EntityNotFoundException("Inquiry not found"));
//
//	    // 요청에서 받아온 값을 적절한 타입으로 변환하여 inquiry 객체에 설정합니다.
//	    inquiry.setComment(inquiryRequest.getComment());
//
//	    Product product = productService.getProductById(inquiryRequest.getProductId());
//	    inquiry.setProduct(product);
//
//	    User user = userService.getUserById(inquiryRequest.getUserId());
//	    inquiry.setUser(user);
//
//	    // 부모 문의가 변경된 경우, 연결을 업데이트합니다.
//	    if (inquiryRequest.getParentId() != null) {
//	        Inquiry parent = inquiryRepository.findById(inquiryRequest.getParentId())
//	                .orElseThrow(() -> new EntityNotFoundException("Parent Inquiry not found"));
//	        inquiry.setParent(parent);
//	    } else {
//	        inquiry.setParent(null);
//	    }
//
//	    return inquiryRepository.save(inquiry);
//	}
//
//	public void deleteInquiry(Integer id) {
//	    inquiryRepository.deleteById(id);
//	}
}
