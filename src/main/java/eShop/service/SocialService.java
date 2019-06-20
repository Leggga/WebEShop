package eShop.service;

import eShop.model.SocialAccount;

public interface SocialService {
	
	String getAuthorizeUrl();
	
	SocialAccount getSoccialAccount(String authToken);
}
