package com.example.demo.accounts;


import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Set;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assert.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByUsername() {

        //Given
        String password = "someone";
        String username = "someone@email.com";
        Account account = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        this.accountService.saveAccount(account);
//        this.accountRepository.save(account);

        //When
        UserDetailsService userDetailsService = (UserDetailsService) accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //Then
        assertThat(this.passwordEncoder.matches(password,userDetails.getPassword())).isTrue();

    }

    //    @Test(expected = UsernameNotFoundException.class)
    @Test
    public void findByUsernameFail() {

        // case 2
        //Expected
        String username = "random@email.com";
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(username));

        //When
        accountService.loadUserByUsername(username);

        //case 1
//        try {
//            accountService.loadUserByUsername(username);
//            fail("supposed to be failed");
//        }catch (UsernameNotFoundException e){
//
//            assertThat(e.getMessage()).containsSequence(username);
//        }

    }
}