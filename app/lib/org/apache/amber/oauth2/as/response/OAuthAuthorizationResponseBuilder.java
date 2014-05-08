package lib.org.apache.amber.oauth2.as.response;

import lib.org.apache.amber.oauth2.bridge.BridgeRequest;
import lib.org.apache.amber.oauth2.common.OAuth;
import lib.org.apache.amber.oauth2.common.message.OAuthResponseBuilder;
import scala.Option;



public class OAuthAuthorizationResponseBuilder extends OAuthResponseBuilder {

    public OAuthAuthorizationResponseBuilder(BridgeRequest request,int responseCode) {
        super(responseCode);
        //AMBER-45
        Option<String> o = request.getQueryString(OAuth.OAUTH_STATE);
        String  state= o.isEmpty()?null:o.get();

        try{
            if(state == null){
            	play.api.mvc.AnyContent a = (play.api.mvc.AnyContent)(request.body());

            	if (a.asFormUrlEncoded().isDefined()){
            		scala.collection.immutable.Map<String, scala.collection.Seq<String>> amap = a.asFormUrlEncoded().get();
            		Option<scala.collection.Seq<String>> ao = amap.get(OAuth.OAUTH_STATE);
            		String aos = ao.get().apply(0);
            		state = aos;
            	}
            }
        }catch(Exception anyE){
        	//do nothing
        }

        if (state!=null){
        	this.setState(state);
        }
    }

    public OAuthAuthorizationResponseBuilder setState(String state) {
        this.parameters.put(OAuth.OAUTH_STATE, state);
        return this;
    }

    public OAuthAuthorizationResponseBuilder setCode(String code) {
        this.parameters.put(OAuth.OAUTH_CODE, code);
        return this;
    }

    public OAuthAuthorizationResponseBuilder setAccessToken(String token) {
        this.parameters.put(OAuth.OAUTH_ACCESS_TOKEN, token);
        return this;
    }

    public OAuthAuthorizationResponseBuilder setExpiresIn(String expiresIn) {
        this.parameters.put(OAuth.OAUTH_EXPIRES_IN, expiresIn == null ? null : Long.valueOf(expiresIn));
        return this;
    }

    public OAuthAuthorizationResponseBuilder setExpiresIn(Long expiresIn) {
        this.parameters.put(OAuth.OAUTH_EXPIRES_IN, expiresIn);
        return this;
    }

    @Override
    public OAuthAuthorizationResponseBuilder location(String location) {
        this.location = location;
        return this;
    }

    @Override
    public OAuthAuthorizationResponseBuilder setParam(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

//
//    @Override
//    public OAuthResponse buildQueryMessage() {
//        return super.buildQueryMessage();
//    }
//
//    @Override
//    public OAuthResponse buildBodyMessage() throws OAuthSystemException {
//        return super.buildBodyMessage();
//    }
//
//    @Override
//    public OAuthResponse buildJSONMessage() {
//        try {
//			return super.buildJSONMessage();
//		} catch (OAuthSystemException e) {
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
//    }
//
//    @Override
//    public OAuthResponse buildHeaderMessage() throws OAuthSystemException {
//        return super.buildHeaderMessage();
//    }

}