package com.bowwow.customer.notice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Notice;

@Service
@Transactional
public class NoticeService {

	public static final int NOTICE_PER_PAGE = 3;
	
	@Autowired
	private NoticeRepository noticeRepo;

	public Page<Notice> listByPage(int pageNum, String keyword) {
		Sort sort = Sort.by(Direction.DESC, "id");
		Pageable pageable = PageRequest.of(pageNum - 1, NOTICE_PER_PAGE, sort);
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        return noticeRepo.findAll(keyword.trim(), pageable);
	    }
	    return noticeRepo.findAll(pageable);
	}

	public Notice findById(int id) {
		return noticeRepo.findById(id).get();
	}

}
