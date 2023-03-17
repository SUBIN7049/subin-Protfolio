package com.bowwow.customer.inquiry;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bowwow.common.entity.Inquiry;
import com.bowwow.common.entity.User;

public interface InquiryRepository extends PagingAndSortingRepository<Inquiry, Integer> {

	public Page<Inquiry> findByProduct_IdAndParentIsNullOrderByRegDateDesc(int productId, Pageable pageable);

	public List<Inquiry> findByUser(User user);

	public Page<Inquiry> findByUser_IdAndParentIsNullOrderByRegDateDesc(Integer integer, Pageable pageable);


}
