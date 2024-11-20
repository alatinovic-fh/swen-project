package org.example.application.routing;

import org.example.application.controller.Controller;
import org.example.application.exception.ControllerNotFound;

import java.util.ArrayList;
import java.util.List;

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
        throw new ControllerNotFound("Not found path: "+path);
    }

    public void addRoute(String route, Controller controller){
        this.routes.add(new Route(route, controller));
    }

}
