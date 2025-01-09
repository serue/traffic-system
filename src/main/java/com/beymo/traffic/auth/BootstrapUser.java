package com.beymo.traffic.auth;

import com.beymo.traffic.role.Role;
import com.beymo.traffic.role.RoleRepository;
import com.beymo.traffic.user.model.Rank;
import com.beymo.traffic.user.model.User;
import com.beymo.traffic.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BootstrapUser  implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BootstrapUser.class);
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private static final String ADMIN_EMAIL = "admin@beymo.com";
    private static final String ADMIN_PASSWORD = "admin123";

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public BootstrapUser(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createAdminRoleIfNotExists(roleRepository);
        createAdminUserIfNotExists(userRepository, roleRepository, passwordEncoder);
    }


    private void createAdminRoleIfNotExists(RoleRepository roleRepository) {
        roleRepository.findByName(ADMIN_ROLE).ifPresentOrElse(
                role -> logger.info("Admin role already exists."),
                () -> {
                    Role role = new Role();
                    role.setName(ADMIN_ROLE);
                    roleRepository.save(role);
                    logger.info("Admin role created successfully.");
                }
        );
    }

    private void createAdminUserIfNotExists(UserRepository userRepository,
                                            RoleRepository roleRepository,
                                            BCryptPasswordEncoder passwordEncoder) {
        userRepository.findByEmail(ADMIN_EMAIL).ifPresentOrElse(
                user -> logger.info("Admin user already exists."),
                () -> {
                    Role adminRole = roleRepository.findByName(ADMIN_ROLE).orElseThrow(() ->
                            new IllegalStateException("Admin role must be present before creating an admin user.")
                    );

                    User user = new User();
                    user.setFirstname("admin");
                    user.setLastname("admin");
                    user.setEmail(ADMIN_EMAIL);
                    user.setRank(Rank.CHIEF_INSPECTOR);
                    user.setForcenumber("000001B");
                    user.setRoles(List.of(adminRole));
                    user.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
                    user.setEnabled(true);
                    user.setAccountLocked(false);

                    userRepository.save(user);
                    logger.info("Admin user created successfully with email: {}", ADMIN_EMAIL);
                }
        );
    }
}
