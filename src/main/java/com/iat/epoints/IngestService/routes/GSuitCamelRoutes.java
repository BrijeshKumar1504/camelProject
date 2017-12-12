package com.iat.epoints.IngestService.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class GSuitCamelRoutes extends RouteBuilder {


        @Override
        public void configure() {
            CamelContext context = new DefaultCamelContext();

            restConfiguration()
                    .contextPath("").apiContextPath("")
                    .apiProperty("api.title", "Ingest Rest API")
                    .apiProperty("api.version", "1.0")
                    .apiProperty("cors", "true")
                    .apiContextRouteId("ingest-api")
                    .bindingMode(RestBindingMode.json);

            rest("/pullusers").description("Gsuit User API")
                    .get("/").description("The list of all users")
                    .route().routeId("gsuit-api")
                    //.bean()
                    .endRest();


            from("direct:remoteService")
                    .routeId("direct-route")
                    .tracing()
                    //.log(">>> ${body.id}")
                    .log(">>> ${body.name}")
                    .transform().simple("Hello ${in.body.name}")
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
        }
    }

