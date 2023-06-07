package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/log")
    public String logTest() {
        String podName = System.getenv("HOSTNAME");
        System.out.println("파드 이름: " + podName);
        logger.trace("This is an trace log message");
        logger.debug("This is an debug log message");
        logger.info("This is an info log message : " + podName);
        logger.warn("This is an warn log message");
        logger.error("This is an error log message");

        return "log Test 완료! in " + podName;
    }

	@GetMapping("/database")
	public @ResponseBody Iterable<User> index() {
		User n = new User();
		n.setName("hello");
		n.setEmail("hello@world.com");
		userRepository.save(n);

		return userRepository.findAll();
	}

}
