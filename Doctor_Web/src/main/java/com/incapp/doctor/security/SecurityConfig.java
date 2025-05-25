//package com.incapp.doctor.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.client.RestTemplate;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//import java.util.Collections;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((requests) -> requests
//			.requestMatchers("/","/User","/register").permitAll()
//			.anyRequest().authenticated())
//			.formLogin((form) -> form.loginPage("/User")
//					.defaultSuccessUrl("/UserHome", true)
//					.failureUrl("/login?error=true") // Redirect to login with error parameter
//					.permitAll())
//			
//			
//			//oauth2
//			.oauth2Login(oauth2 -> oauth2
//            .loginPage("/oauth2/authorization/google") // Separate login page for Google OAuth2
//            .defaultSuccessUrl("/UserHome", true)
//            
//            .userInfoEndpoint(userInfo -> userInfo
//                    .userService(new DefaultOAuth2UserService(){
//                    	@Override
//                        public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//                            OAuth2User oauth2User = super.loadUser(userRequest);
//                            
//                            
//                            // Extract user details
//                            String email = oauth2User.getAttribute("email");
//                            String name = oauth2User.getAttribute("name");
//                            System.out.println(email+name);
//                          
//                            // Wrap the OAuth2User with additional authorities
//                            return new DefaultOAuth2User(
//                                    null, oauth2User.getAttributes(),"name");
//                        }
//            		}))
//            .permitAll())
//			
//			
//			//.logout((logout) -> logout.permitAll()) //take you to login page
//			.logout((logout) -> logout.logoutSuccessUrl("/").permitAll()) //to redirect to specific page
//			
//			.exceptionHandling(handling -> handling.accessDeniedPage("/accessDenied"));
//
//		return http.build();
//
//	}
//	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//	
//	
//
//	@Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        
//        //using anonymous class
//		UserDetailsService customUserDetailsService=new UserDetailsService(){
//			@Override
//			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//				String URL="http://localhost:9900";
//				RestTemplate restTemplate=new RestTemplate();
//				String API="/user/getByEmail/"+email;
//				com.incapp.doctors.model.User user=restTemplate.getForObject(URL+API, com.incapp.doctors.model.User.class);
//				if (user == null) {
//		            throw new UsernameNotFoundException("User not found");
//		        }
//		        //This User class belong to this package-> org.springframework.security.core.userdetails.User
//		        return 
//	        		new User(
//	  		        user.getEmail(),
//	  		        user.getPassword(), // Encrypting password,  
//	  		        true, // accountNonEnable
//	  		        true, // accountNonExpired
//	  		        true, // credentialsNonExpired
//	  		        true, // accountNonLocked
//	  		        List.of(new SimpleGrantedAuthority(null)));  		
//			}
//		};
//        provider.setUserDetailsService(customUserDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//	
//}
