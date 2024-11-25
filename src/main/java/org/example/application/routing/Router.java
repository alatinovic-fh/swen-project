package org.example.application.routing;

import org.example.application.controller.Controller;
import org.example.application.exception.ControllerNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the logic for choosing the
 * Controllers by matching the Routes with the given path
 *
 * @author Aleksandar Latinovic
 * */

public class Router {

    private final List<Route> routes;

    public Router(){
        this.routes = new ArrayList<>();
    }

    /**
     * This method iterates through all Routes and matches
     * them with the given Path
     *
     * @param path
     * @return the Controller that matches the Route
     */
    public Controller getController(String path) {
        for(Route route : this.routes){
            if(!path.startsWith(route.getRoute())){
                continue;
            }
            return route.getController();
        }
        throw new ControllerNotFoundException("Not found path: "+path);
    }

    public void addRoute(String route, Controller controller){
        this.routes.add(new Route(route, controller));
    }

}
