package com.example.demo.views;

import com.example.demo.input.MyInput;
import com.example.demo.team.Team;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class InputForm extends FormLayout {
    private MyInput input = new MyInput();

    EmailField email = new EmailField("Email");
    TextField fee = new TextField("Fee");
    TextField date = new TextField("Date");
    ComboBox<String> homeTeam = new ComboBox<>("Home team");
    ComboBox<String> visitorTeam = new ComboBox<>("Visitor team");
    Binder<MyInput> binder = new BeanValidationBinder<>(MyInput.class);

    Button save =  new Button("Save");
    Button delete =  new Button("Delete");
    Button cancel =  new Button("Cancel");

    public InputForm(List<String> teams){
        addClassName("input-form");
        binder.bindInstanceFields(this);

        homeTeam.setItems(teams);
        visitorTeam.setItems(teams);

        add(
                email,
                date,
                homeTeam,
                visitorTeam,
                fee,
                createBtnsLayout()
        );
    }

    public void setInput(MyInput input){
        this.input = input;
        binder.readBean(input);
    }

    private HorizontalLayout createBtnsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickListener(e -> validateAndSave());
        delete.addClickListener(e -> fireEvent(new DeleteEvent(this, input)));
        cancel.addClickListener(e -> fireEvent(new CloseEvent(this)));

        save.addClickShortcut(Key.ENTER);
        cancel.addClickShortcut(Key.ESCAPE);

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, cancel);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(input);
            fireEvent(new SaveEvent(this, input));
        } catch (ValidationException e){
            e.printStackTrace();
        }
    }

    //Events
    public static abstract class InputFormEvent extends ComponentEvent<InputForm> {
        private MyInput input;

        protected InputFormEvent(InputForm source, MyInput input) {
            super(source, false);
            this.input = input;
        }

        public MyInput getInput() {
            return input;
        }
    }

    public static class SaveEvent extends InputFormEvent {
        SaveEvent(InputForm source, MyInput input) {
            super(source, input);
        }
    }

    public static class DeleteEvent extends InputFormEvent {
        DeleteEvent(InputForm source, MyInput input) {
            super(source, input);
        }

    }

    public static class CloseEvent extends InputFormEvent {
        CloseEvent(InputForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
