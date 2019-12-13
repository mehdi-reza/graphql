package example.graphql;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import graphql.GraphQL;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.graphql.GraphQLHandlerOptions;
import io.vertx.ext.web.handler.graphql.GraphiQLHandler;
import io.vertx.ext.web.handler.graphql.GraphiQLHandlerOptions;
import io.vertx.ext.web.handler.graphql.impl.GraphQLHandlerImpl;

/**
 * 
 * @author Mehdi Raza
 *
 */
@ApplicationScoped
public class Routes {

	@Inject GraphQL graphQL;

	public void init(@Observes Router router) {
		
		boolean enableGraphiQL = true;
		
		router.route("/graphql").handler(new GraphQLHandlerImpl(graphQL, new GraphQLHandlerOptions()));
		router.route("/graphiql/*").handler(GraphiQLHandler.create(new GraphiQLHandlerOptions().setEnabled(enableGraphiQL)));
	}
}
