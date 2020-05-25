package com.example.batchjob.batch;

import com.example.batchjob.model.User;
import com.example.batchjob.repository.UserPagingRepository;
import com.example.batchjob.repository.UserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBReaderPagable implements ItemReader<User> {

    @Autowired
    private UserPagingRepository userPagingRepository;

    private int nextStudentIndex;

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        User nextUser = null;

        Page<User> pageData = userPagingRepository.findAll(PageRequest.of(0, 2));

        List<User> userData = pageData.getContent();

        if (nextStudentIndex < userData.size()) {
            nextUser = userData.get(nextStudentIndex);

            System.out.println(nextStudentIndex + " :: Read UserPageable : " + nextUser.toString());

            nextStudentIndex++;
        }

        return nextUser;
    }
}
