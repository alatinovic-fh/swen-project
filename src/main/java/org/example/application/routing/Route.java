package org.example.application.routing;

import org.example.application.controller.Controller;

/**
 * This class is used to specify a Route for the API
 * and map it to the specific Controller
 *
 * @author Aleksandar Latinovic
 * */
public class Route {

    private final String route;

    private final Controller controller;

    public Route(String route, Controller controller) {
        this.route = route;
        this.controller = controller;
    }

    public String getRoute() {
        return route;
    }

    public Controller getController() {
        return controller;
    }

}
