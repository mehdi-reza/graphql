package example.graphql.fetchers;

import example.types.Profile;
import io.vertx.ext.web.handler.graphql.VertxDataFetcher;

/**
 * 
 * @author Mehdi Raza
 *
 */
public class ProfileFetcher {

	public static VertxDataFetcher<Profile> getProfileFetcher() {
		
		return new VertxDataFetcher<Profile>((env, promise) -> {
			Profile profile = new Profile();
			profile.setId(1);
			profile.setName("Mehdi Raza");
			promise.complete(profile);
		});
	}

}
