package eShop.service.impl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;

import eShop.model.SocialAccount;
import eShop.service.SocialService;

public class FacebookSocialServiceImpl implements SocialService{

	private final String idClient;
	private final String secret;
	private final String redirectUrl;
	
	public FacebookSocialServiceImpl(ServiceManager serviceManager) {
		idClient = serviceManager.getAppProperty("social.facebook.idClient");
		secret = serviceManager.getAppProperty("social.facebook.secret");
		redirectUrl = serviceManager.getAppProperty("app.host") + "/from-socialfb";
	}
	
	@Override
	public String getAuthorizeUrl() {
		ScopeBuilder scopeBuilder = new ScopeBuilder();
		scopeBuilder.addPermission(FacebookPermissions.EMAIL);
		FacebookClient client = new DefaultFacebookClient(Version.VERSION_3_3);
		return client.getLoginDialogUrl(idClient, redirectUrl, scopeBuilder);
	}
	
	@Override
	public SocialAccount getSoccialAccount(String authToken) {
		FacebookClient client = new DefaultFacebookClient(Version.VERSION_3_3);
		AccessToken token = client.obtainUserAccessToken(idClient, secret,redirectUrl, authToken);
		client = new DefaultFacebookClient(token.getAccessToken(), Version.VERSION_3_3);
		User user = client.fetchObject("me", User.class, Parameter.with("fields", "name,email,first_name,last_name"));
		return new SocialAccount(user.getName(), user.getEmail());
	}
	
}
