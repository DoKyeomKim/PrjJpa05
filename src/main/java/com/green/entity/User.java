package com.green.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
// Entity는 테이블 명과 동일하지만 oracle은 "user"라는 이름의 테이블을 만들 수 없기 때문에
// 테이블 명을 변경해야 함(usersjpa)
@Table(name="usersjpa")
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
// UserDetails 인터페이스를 구현하여 User 엔티티를 생성함
public class User implements UserDetails{
	
	// 칼럼부분을 표시
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false)
	private Long id;
	
	@Column(name="email", nullable=false, unique=true)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	// @Builder
	// 생성자 대신 아래 문법으로 사용 가능, 파라미터 순서에 무관함
	// User user = user.builder().email().password().build();
	@Builder
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	
	// implement할 메소드들
	@Override 	//권한 반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자가 가지고 있는 권한의 목록을 반환한다.
		// 현재는 User 권한만 반환
		return List.of(new SimpleGrantedAuthority("user"));
	}

	@Override //사용자 비밀번호
	public String getPassword() {
		return password;
	}

	@Override //사용자 아이디(여기서는 이메일로 로그인할거니 이메일임)
	public String getUsername() {
		return email;
	}

	@Override //계정 만료여부 반환
	public boolean isAccountNonExpired() {
		// 계정이 만료되었는지를 확인하는 로직
		return true; // true -> 만료되지 않음.
	}

	@Override //계정 잠금 여부 반환
	public boolean isAccountNonLocked() {
		// 계정 잠금 여부 확인하는 로직
		return true; //true -> 잠기지 않음 
	}

	@Override //패스워드 만료여부 반환
	public boolean isCredentialsNonExpired() {
		// 패스워드가 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않음.
	}

	@Override // 계정 사용 가능 여부 반환
	public boolean isEnabled() {
		// 계정 사용 가능 여부를 반환하는 로직
		return true; // true-> 계정 사용 가능 , false-> 계정 사용 불가
	}




}
