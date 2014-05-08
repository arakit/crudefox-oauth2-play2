package jp.crudefox.oauth2play2.oauth2.common.message;

import java.util.HashMap;
import java.util.Map;

import jp.crudefox.oauth2play2.oauth2.common.OAuth;
import jp.crudefox.oauth2play2.oauth2.common.exception.OAuthSystemException;
import jp.crudefox.oauth2play2.oauth2.common.parameters.BodyURLEncodedParametersApplier;
import jp.crudefox.oauth2play2.oauth2.common.parameters.JSONBodyParametersApplier;
import jp.crudefox.oauth2play2.oauth2.common.parameters.OAuthParametersApplier;
import jp.crudefox.oauth2play2.oauth2.common.parameters.QueryParameterApplier;
import jp.crudefox.oauth2play2.oauth2.common.parameters.WWWAuthHeaderParametersApplier;

public class OAuthResponseBuilder {

    protected OAuthParametersApplier applier;
    protected Map<String, Object> parameters = new HashMap<String, Object>();
    protected int responseCode;
    protected String location;

    public OAuthResponseBuilder(int responseCode) {
        this.responseCode = responseCode;
    }

    public OAuthResponseBuilder location(String location) {
        this.location = location;
        return this;
    }

    public OAuthResponseBuilder setScope(String value) {
        this.parameters.put(OAuth.OAUTH_SCOPE, value);
        return this;
    }

    public OAuthResponseBuilder setParam(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public OAuthResponse buildQueryMessage() throws OAuthSystemException {
        OAuthResponse msg = new OAuthResponse(location, responseCode);
        this.applier = new QueryParameterApplier();
        return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    }

    public OAuthResponse buildQueryMessageNonException() {
        OAuthResponse msg = new OAuthResponse(location, responseCode);
        this.applier = new QueryParameterApplier();
        try {
			return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
		} catch (OAuthSystemException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
    }

    public OAuthResponse buildBodyMessage() throws OAuthSystemException {
        OAuthResponse msg = new OAuthResponse(location, responseCode);
        this.applier = new BodyURLEncodedParametersApplier();
        return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    }

    public OAuthResponse buildJSONMessage() throws OAuthSystemException {
        OAuthResponse msg = new OAuthResponse(location, responseCode);
        this.applier = new JSONBodyParametersApplier();
        return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    }

    public OAuthResponse buildHeaderMessage() throws OAuthSystemException {
        OAuthResponse msg = new OAuthResponse(location, responseCode);
        this.applier = new WWWAuthHeaderParametersApplier();
        return (OAuthResponse)applier.applyOAuthParameters(msg, parameters);
    }
}
