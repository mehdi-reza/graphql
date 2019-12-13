package example.graphql;

import java.io.InputStreamReader;

import javax.enterprise.inject.Produces;

import example.graphql.fetchers.ProfileFetcher;
import example.graphql.fetchers.WalletFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * 
 * @author Mehdi Raza
 *
 */
public class GraphQLProducer {

	private Logger logger = LoggerFactory.getLogger(GraphQLProducer.class);

	@Produces
	public GraphQL setup() {

		logger.info("Setting up GraphQL..");

		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry registry = schemaParser.parse(
				new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/resources/graphql.schema")));

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(registry, wirings());
		return GraphQL.newGraphQL(graphQLSchema).build();
	}

	private RuntimeWiring wirings() {

		logger.info("Wiring queries..");

		return RuntimeWiring.newRuntimeWiring()
				.type("Query", builder -> builder.dataFetcher("profile", ProfileFetcher.getProfileFetcher()))
				.type("Profile", builder -> builder.dataFetcher("wallets", WalletFetcher.getWalletsFetcher())).build();
	}
}
