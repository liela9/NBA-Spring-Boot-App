package com.example.demo.input;

import com.example.demo.game.Game;
import com.example.demo.game.GameService;
import com.example.demo.user.AppUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyInputService {
    private final MyInputRepository myInputRepository;
    private final GameService gameService;

    @Autowired
    public MyInputService(MyInputRepository myInputRepository, GameService gameService) {
        this.myInputRepository = myInputRepository;
        this.gameService = gameService;
    }

    public List<MyInput> getAllInputs() {
        return myInputRepository.findAll();
    }

    public List<MyInput> getAllInputs(String email, String filterText) {
        if (filterText == null || filterText.isEmpty())
            return myInputRepository.findInputsByEmail(email);

        return myInputRepository.search(email, filterText);
    }

    public List<MyInput> getInputsByUser(AppUser user) {
        return myInputRepository.findInputsByEmail(user.getEmail());
    }

    public void deleteInput(MyInput input){
        myInputRepository.delete(input);
    }

    public void addNewInput(MyInput input) throws Exception {

        List<Game> games = gameService.getGameByHomeTeam(input.getHomeTeam());
        if (games == null || games.size() == 0){
            showDialog("Incorrect home team");
            throw new Exception();
        }

        if(input.getFee() <= 0) {
            showDialog("Illegal fee");
            throw new Exception();
        }

        for (int i = 0; i < games.size(); ++i) {
            if (input.getDate().equals(games.get(i).getDate()) &&
                    input.getVisitorTeam().equals(games.get(i).getVisitorTeam())) {
                myInputRepository.save(input);
                return;
            }
        }

        //if the input game not found
        showDialog("Illegal game info");
        throw new Exception();
    }

    private void showDialog(String message) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle(message);

        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);

        dialog.open();
    }
}
