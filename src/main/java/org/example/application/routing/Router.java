package org.example.application.routing;

import org.example.application.controller.Controller;
import org.example.application.controller.UserController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private final List<Route> routes;

    public Router(){
        this.routes = new ArrayList<>();
    }

    public Controller getController(String path){
        for(Route route : this.routes){
            if(!path.startsWith(route.getRoute())){
                continue;
            }

            return route.getController();
        }

        throw new ControllerNotFound(path);
    }

    public void addRoute(String route, Controller controller){
        this.routes.add(new Route(route, controller));
    }

}
