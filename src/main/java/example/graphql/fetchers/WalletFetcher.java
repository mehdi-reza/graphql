package example.graphql.fetchers;

import java.util.Arrays;
import java.util.List;

import example.types.Wallet;
import io.vertx.ext.web.handler.graphql.VertxDataFetcher;

/**
 * 
 * @author Mehdi Raza
 *
 */
public class WalletFetcher {

	public static VertxDataFetcher<List<Wallet>> getWalletsFetcher() {
		
		return new VertxDataFetcher<List<Wallet>>((env, promise) -> {

			Wallet jazz = new Wallet();
			jazz.setId(1);
			jazz.setName("JAZZ");
			
			Wallet easyPaisa = new Wallet();
			easyPaisa.setId(1);
			easyPaisa.setName("EasyPaisa");

			promise.complete(Arrays.asList(new Wallet[] {jazz, easyPaisa}));
		});
	}
}
