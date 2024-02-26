package com.IT.osahaneat.services;

import com.IT.osahaneat.Responsitory.RoleRepository;
import com.IT.osahaneat.Responsitory.UserRepository;
import com.IT.osahaneat.entity.Roles;
import com.IT.osahaneat.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = userRepository.findByUserName(username);
//        if(user==null){
//            throw new UsernameNotFoundException("user cant exist");
//        }
//        System.out.println(123);
////        chưa phân quyền nên tạo rqa  ra arayList rông
//        return new User(username,user.getPassword(), new ArrayList<>());
//    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(username, user.getPassword(), getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Roles role) {
        // Sử dụng getRoleName() để lấy tên role từ đối tượng Role
        String roleName = role.getRoleName();

        // Tạo đối tượng SimpleGrantedAuthority với tên role (đã thêm "ROLE_" prefix)
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        // Trả về danh sách authorities, trong trường hợp này chỉ có một authority
        return Collections.singletonList(authority);
    }

}
