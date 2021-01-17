package io.luxyva.switches.auth.web;

import io.luxyva.switches.auth.domain.Account;
import io.luxyva.switches.auth.repository.AccountRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class AccountController {

	@Resource
	private AccountRepository accountRepository;

	@PostMapping("/test")
	public void insert() {
		Account account = new Account();
		account.setId(12327447L);
		account.setBirthday("20200101");
		account.setName("测试");
		account.setPhone("18829289407");
		account.setFollowed(0L);
		account.setFollower(0L);
		account.setCollect(0L);
		account.setGender(false);
		account.setCreateDt(new Date());
		account.setPassword("123456");
		accountRepository.insert(account);
	}

}
