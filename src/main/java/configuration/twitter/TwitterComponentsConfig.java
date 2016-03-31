package configuration.twitter;

import org.auraframework.adapter.ComponentLocationAdapter;
import org.auraframework.util.ServiceLoaderImpl.AuraConfiguration;
import org.auraframework.util.ServiceLoaderImpl.Impl;

@AuraConfiguration
public class TwitterComponentsConfig {

	@Impl
	public static ComponentLocationAdapter twitterLocationAdapterImplProvider() {
		ComponentLocationAdapter cla = new ComponentLocationAdapter.Impl("twitter-components");
		return cla;
	}
}
