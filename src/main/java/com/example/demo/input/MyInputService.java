package com.example.demo.input;

import com.example.demo.user.AppUser;
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
    public List<MyInput> getAllInputs(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return myInputRepository.findAll();

        return myInputRepository.search(filterText);
    }
    public List<MyInput> getInputsByUser(AppUser user) {
        return myInputRepository.findInputsByEmail(user.getEmail());
    }
    public void addNewInput(MyInput input) {
        if (input == null){
            System.err.println("Input is null");
            return;
        }
        myInputRepository.save(input);
    }
    public void deleteInput(MyInput input){
        myInputRepository.delete(input);
    }
}
