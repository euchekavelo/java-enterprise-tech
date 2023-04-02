package ru.skillbox.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.skillbox.paymentservice.domain.User;
import ru.skillbox.paymentservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean userBalanceChanged(double orderPrice, Integer userId) {
        boolean balanceChanged = false;
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            double userBalance = user.getBalance();
            if (userBalance >= orderPrice) {
                user.setBalance(userBalance - orderPrice);
                userRepository.save(user);

                balanceChanged = true;
            }
        }

        return balanceChanged;
    }
}
