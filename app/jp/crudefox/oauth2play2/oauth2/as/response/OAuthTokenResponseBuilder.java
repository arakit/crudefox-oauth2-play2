package jp.crudefox.oauth2play2.oauth2.as.response;

import jp.crudefox.oauth2play2.oauth2.common.OAuth;
import jp.crudefox.oauth2play2.oauth2.common.message.OAuthResponseBuilder;

public class OAuthTokenResponseBuilder extends OAuthResponseBuilder {

    public OAuthTokenResponseBuilder(int responseCode) {
        super(responseCode);
    }

    public OAuthTokenResponseBuilder setAccessToken(String token) {
        this.parameters.put(OAuth.OAUTH_ACCESS_TOKEN, token);
        return this;
    }

    public OAuthTokenResponseBuilder setExpiresIn(String expiresIn) {
        this.parameters.put(OAuth.OAUTH_EXPIRES_IN, expiresIn == null ? null : Long.valueOf(expiresIn));
        return this;
    }

    public OAuthTokenResponseBuilder setRefreshToken(String refreshToken) {
        this.parameters.put(OAuth.OAUTH_REFRESH_TOKEN, refreshToken);
        return this;
    }

    public OAuthTokenResponseBuilder setTokenType(String tokenType) {
        this.parameters.put(OAuth.OAUTH_TOKEN_TYPE, tokenType);
        return this;
    }

    public OAuthTokenResponseBuilder setParam(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public OAuthTokenResponseBuilder location(String location) {
        this.location = location;
        return this;
    }


//    @Override
//    public OAuthResponse buildQueryMessage() throws OAuthSystemException {
//        return super.buildQueryMessage();
//    }
//
//    @Override
//    public OAuthResponse buildBodyMessage() throws OAuthSystemException {
//        return super.buildBodyMessage();
//    }
//
//    @Override
//    public OAuthResponse buildJSONMessage() throws OAuthSystemException {
//        return super.buildJSONMessage();
//    }
//
//    @Override
//    public OAuthResponse buildHeaderMessage() throws OAuthSystemException {
//        return super.buildHeaderMessage();
//    }

}