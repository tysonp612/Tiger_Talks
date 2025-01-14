package com.group2.Tiger_Talks.backend.service._implementation.Authentication;

import com.group2.Tiger_Talks.backend.model.User.UserProfileDTO;
import com.group2.Tiger_Talks.backend.model.Utils.OnlineStatus;
import com.group2.Tiger_Talks.backend.repository.User.UserProfileRepository;
import com.group2.Tiger_Talks.backend.service.Authentication.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogInServiceImpl implements LogInService {

    @Autowired
    private UserProfileRepository userRepository;

    @Override
    public Optional<UserProfileDTO> logInUser(String email, String password) {
        return userRepository.findById(email)
                .map(userProfile -> {
                    if (userProfile.getPassword().equals(password)) {
                        userProfile.setOnlineStatus(OnlineStatus.AVAILABLE);
                        userRepository.save(userProfile);
                        return Optional.of(userProfile.toDto());
                    } else {
                        return Optional.<UserProfileDTO>empty();
                    }
                }).orElseGet(Optional::empty);
    }

    @Override
    public void logOut(String email) {
        userRepository.findById(email)
                .map(userProfile -> {
                    userProfile.setOnlineStatus(OnlineStatus.OFFLINE);
                    return userRepository.save(userProfile);
                });
    }
}
