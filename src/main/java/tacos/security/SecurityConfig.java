package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // jdbc定于用户时需要装载的数据源
//    @Autowired
//    DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public StandardPasswordEncoder encoder(){
        return new StandardPasswordEncoder("53cr3t");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 内存定义用户
//        auth.inMemoryAuthentication().withUser("tjk").password("1234").authorities("ROLE_USER");

        // jdbc定义用户
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password enabled from Users where username=?")
//                .authoritiesByUsernameQuery("select username, authority from UserAuthorities where username=?")
//                .passwordEncoder(new SCryptPasswordEncoder());

        // ldap方式定于用户鉴权
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("(member={0})")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode");
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());

    }
}
