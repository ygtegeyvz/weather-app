package com.weatherwebapp.weatherapp;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringUI
public class MainUI extends UI {
    private VerticalLayout root;

    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        root.setWidth("100%");
        root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(root);
    }

    private void addForm() {
        HorizontalLayout user = new HorizontalLayout();

        TextField CityAdding = new TextField();

        user.setWidth("100%");

        CityAdding.setWidth("500");

        Button AddingButton = new Button("Izmir'in Hava Durumunu Göster");
        user.addComponent(AddingButton);
        AddingButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        AddingButton.setIcon(VaadinIcons.SUN_RISE);
        AddingButton.addClickListener(click ->{
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            WeatherService weatherService = new WeatherService(restTemplateBuilder);
            Label label = new Label();
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.addComponent(label);
            WeatherE weather = weatherService.getWeather("Turkey", "Izmir");
            Label label2 = new Label();
            label.addStyleName(ValoTheme.LABEL_H1);
            label.setValue(weather.getName());
            label2.addStyleName(ValoTheme.LABEL_H2);

            label2.setValue(weather.getCelsiusTemperature());
            Image icon = new Image(null, new ExternalResource("http://openweathermap.org/img/w/" + weather.getWeatherIcon() + ".png"));

            verticalLayout.addComponent(icon);
            verticalLayout.addStyleName(ValoTheme.LAYOUT_CARD);

            verticalLayout.setWidth("61%");
            verticalLayout.addComponent(label2);
            verticalLayout.setDefaultComponentAlignment(Alignment.TOP_LEFT);
            root.addComponent(verticalLayout);

        });

        user.addComponent(AddingButton);
        user.setMargin(false);
        user.setSpacing(true);
        user.addStyleName("Light");

        root.addComponent(user);
    }

    private void addHeader() {
        Label header = new Label("Izmir Hava Durumu");
        header.addStyleName(ValoTheme.LABEL_H2);
        root.addComponent(header);
    }

    private void setupLayout() {
        root = new VerticalLayout();
        setContent(root);
    }
}
