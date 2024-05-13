package test.gateway1.repository;

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyRouteDefinitionRepository implements RouteDefinitionLocator, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    private List<RouteDefinition> routeDefinitionList = new ArrayList<>();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeDefinitionList);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void refreshRoute3(){
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId("route3");
        routeDefinition.setUri(URI.create("http://localhost:8081"));
        routeDefinition.setPredicates(Arrays.asList(new PredicateDefinition("Path=/api/v3/**")));
        routeDefinition.setFilters(Arrays.asList());
        routeDefinitionList = Arrays.asList(routeDefinition);
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void refreshRoute4(){
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId("route4");
        routeDefinition.setUri(URI.create("http://localhost:8082"));
        routeDefinition.setPredicates(Arrays.asList(new PredicateDefinition("Path=/api/v4/**")));
        routeDefinition.setFilters(Arrays.asList());
        routeDefinitionList = Arrays.asList(routeDefinition);
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

}