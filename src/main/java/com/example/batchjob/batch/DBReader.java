package com.example.batchjob.batch;

import com.example.batchjob.model.User;
import com.example.batchjob.repository.UserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBReader implements ItemReader<User> {

    @Autowired
    private UserRepository userRepository;

    private int nextStudentIndex;

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        User nextUser = null;

        List<User>  userData = userRepository.findAll();

        if (nextStudentIndex < userData.size()) {
            nextUser = userData.get(nextStudentIndex);

            System.out.println("Read User : " + nextUser.toString());

            nextStudentIndex++;
        }

        return nextUser;
    }
}
