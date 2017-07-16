package cn.szxys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.szxys.mapper")
public class TerminalUpgradePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerminalUpgradePlatformApplication.class, args);
	}
}
