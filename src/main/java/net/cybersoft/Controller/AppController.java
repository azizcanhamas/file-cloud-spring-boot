package net.cybersoft.Controller;

import java.util.List;

import net.cybersoft.Entity.User;
import net.cybersoft.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//Uygulamanin tamamini yoneten Controller sinifi
@Controller
public class AppController {

	@Autowired
	private UserRepository userRepo;

	// http://localhost:8080 linki ile
	// gelindiginde login'e yonlendir.
	@GetMapping("")
	public String viewHomePage() {
		return "login";
	}

	// Kullanici kayit olma sayfasina girdigi zaman
	// istekler buraya yonlendirilir. Bos bir User
	// nesnesi olusturulur ve Thymeleaf'e gonderilir.
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	// Kayit olma formu dolduruldugu zaman bu fonksiyon calisir
	// TEKNIK: HTML tarafinda /process_register adresine
	// Thymeleaf ile action alindigi zaman buraya yonlendirme gerceklesir.
	@PostMapping("/process_register")
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		
		return "register_success";
	}

	// Tum kullanicilarin listelenmis oldugu /users sayfasina
	// gelindiginde bu fonksiyon calisir. findAll() komutu
	// sayesinde veritabaninda tum kayitlar getirilir ve
	// Thymeleaf'e geri gonderilir.
	@GetMapping("/home")
	public String listUsers(Model model) {
		List<User> listUsers = userRepo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "home";
	}
}
