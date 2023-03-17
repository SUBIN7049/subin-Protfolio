package com.bowwow.customer.user;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bowwow.common.entity.Product;
import com.bowwow.common.entity.User;
import com.bowwow.customer.product.ProductRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProductRepository proRepo;
	
	public List<User> listAll() {
		return (List<User>) userRepo.findAll();
	}
	
	public List<Product> ProductAll(){
		return (List<Product>) proRepo.findAll();
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User findById(int id) {
		return userRepo.findById(id).get();
	}
	
	public User save(User user) {
	      boolean isUpdatingUser = (user.getId() != null);
	      
	      if(isUpdatingUser) {
	         User existingUser = userRepo.findById(user.getId()).get();
	         if(user.getPassword().isEmpty()) {   //유저폼에서 유저 업데이트시 비밀번호를 변경하지 않았을때
	            user.setPassword(existingUser.getPassword());
	         }else {
	            if(user.getPassword() == existingUser.getPassword()) {  //유저폼 아닌데서 point or like 가 업데이트 될때
	               user.setPassword(existingUser.getPassword());
	            }else {                          //유저폼에서 유저 업데이트시 비밀번호를 변경했을때
	            encodePassword(user);
	            }
	         }
	      }else {
	         encodePassword(user);
	         System.err.println("신규등록");
	      }
	      return userRepo.save(user);
	   }

	private void encodePassword(User user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
	}

	public String isNickNameUnique(Integer id, String nickName, String email) {
		User userByNickName = userRepo.findByNickName(nickName);
		User userByEmail = userRepo.findByEmail(email);
		
		boolean isCreatingNew = (id == null);	//
		
		if(isCreatingNew) {	//회원가입
			if(userByEmail == null && userByNickName == null) {
				return "OK";
			}else if(userByEmail != null && userByNickName == null) {
				return "DuplicateEmail";
			}else if(userByNickName != null && userByEmail == null) {
				return "DuplicateNickName";
			}else if(userByEmail != null && userByNickName != null) {
				return "Duplicate";
			}
		}else {	//내정보수정
			if(userByNickName == null) {
				return "OK";
			}else if(userByNickName != null && userByNickName.getId() == id) {
				return "OK";	
			}else if(userByNickName != null && userByNickName.getId() != id ) {
				return "DuplicateNickName";
			}
		}
		return "OK";
	}


}
