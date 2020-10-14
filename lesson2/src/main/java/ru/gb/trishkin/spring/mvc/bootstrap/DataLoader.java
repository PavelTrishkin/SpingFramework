//package ru.gb.trishkin.spring.mvc.bootstrap;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.gb.trishkin.spring.mvc.domain.Role;
//import ru.gb.trishkin.spring.mvc.domain.Users;
//import ru.gb.trishkin.spring.mvc.repository.UserRepo;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    public static final Users USER = new Users();
//    public static final Users ADMIN = new Users();
//
//    private final UserRepo userRepo;
//
//    static {
//        USER.setName("User");
//        USER.setPassword("pass");
//        USER.setRole(Role.USER);
//
//        ADMIN.setName("Admin");
//        ADMIN.setPassword("admin");
//        ADMIN.setRole(Role.ADMIN);
//    }
//
//    public DataLoader(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        initData();
//    }
//
//    private void initData(){
//        userRepo.save(USER);
//        userRepo.save(ADMIN);
//    }
//
//}
