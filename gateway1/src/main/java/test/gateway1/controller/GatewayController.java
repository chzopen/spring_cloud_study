package test.gateway1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import test.gateway1.repository.MyRouteDefinitionRepository;

import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/gateway")
public class GatewayController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Autowired
    private MyRouteDefinitionRepository myRouteDefinitionRepository;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping("addRoute")
    public String addRoute()
    {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId("test3");
        routeDefinition.setUri(URI.create("http://localhost:8081"));
        routeDefinition.setPredicates(Arrays.asList(new PredicateDefinition("Path=/api/v3/**")));
        routeDefinition.setFilters(Arrays.asList());
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return ":/gateway/addRoute";
    }

    @GetMapping("deleteRoute")
    public String deleteRoute()
    {
        routeDefinitionWriter.delete(Mono.just("test3")).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return ":/gateway/deleteRoute";
    }

    @GetMapping("refreshRoute3")
    public String refreshRoute3()
    {
        myRouteDefinitionRepository.refreshRoute3();
        return ":/gateway/refreshRoutes1";
    }

    @GetMapping("refreshRoute4")
    public String refreshRoute4()
    {
        myRouteDefinitionRepository.refreshRoute4();
        return ":/gateway/refreshRoutes2";
    }


}