package cn.littlemotor.web.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class test {
    public static void main(String[] args){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("$2a$10$Di0QN8.3RGLV9hM7.4RqD.JuDRCl0ETRDnTrT1loA22BFdIdWLF1e"));
    }
}
