package jp.crudefox.oauth2play2.oauth2.common.message;

import jp.crudefox.oauth2play2.oauth2.common.OAuth;
import jp.crudefox.oauth2play2.oauth2.common.error.OAuthError;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthProblemException;

public class OAuthErrorResponseBuilder extends OAuthResponseBuilder {

    public OAuthErrorResponseBuilder(int responseCode) {
        super(responseCode);
    }

    public OAuthErrorResponseBuilder error(OAuthProblemException ex) {
        this.parameters.put(OAuthError.OAUTH_ERROR, ex.getError());
        this.parameters.put(OAuthError.OAUTH_ERROR_DESCRIPTION, ex.getDescription());
        this.parameters.put(OAuthError.OAUTH_ERROR_URI, ex.getUri());
        this.parameters.put(OAuth.OAUTH_STATE, ex.getState());
        return this;
    }

    public OAuthErrorResponseBuilder setError(String error) {
        this.parameters.put(OAuthError.OAUTH_ERROR, error);
        return this;
    }

    public OAuthErrorResponseBuilder setErrorDescription(String desc) {
        this.parameters.put(OAuthError.OAUTH_ERROR_DESCRIPTION, desc);
        return this;
    }

    public OAuthErrorResponseBuilder setErrorUri(String state) {
        this.parameters.put(OAuthError.OAUTH_ERROR_URI, state);
        return this;
    }

    public OAuthErrorResponseBuilder setState(String state) {
        this.parameters.put(OAuth.OAUTH_STATE, state);
        return this;
    }

    public OAuthErrorResponseBuilder setRealm(String realm) {
        this.parameters.put(OAuth.WWWAuthHeader.REALM, realm);
        return this;
    }

    public OAuthErrorResponseBuilder location(String location) {
        this.location = location;
        return this;
    }
}