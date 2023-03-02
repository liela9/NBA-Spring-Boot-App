package com.example.demo.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyInputService {
    private final MyInputRepository myInputRepository;

    @Autowired
    public MyInputService(MyInputRepository myInputRepository) {
        this.myInputRepository = myInputRepository;
    }

    public List<MyInput> getAllInputs() {
        return myInputRepository.findAll();
    }
    public List<MyInput> getInputsByUser(String email) {
        return myInputRepository.findInputsByUser(email);
    }
    public void addNewInput(MyInput input) {
        myInputRepository.save(input);
    }
}
