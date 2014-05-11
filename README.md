crudefox-oauth2-play2
=====================

oauth2 auth server for play2 lib.  
scala to java API.  
This is with reference to "Apache Amber" and "cleanyong/oauth2play2scala"  

Although this product has been developed for personal use.  
Yet, during development.  

# Development environment
* playframework 2.2.3  
* sbt 0.13.0  
* scala 2.10.3  
* java 7  

# how to use this lib.
## this liblary
```
play publish-local
```


## your app of ./project/Build.scala
```
object ApplicationBuild extends Build {

  val appName = "appname"
  val appVersion = "1.0-SNAPSHOT"
  val appDependencies = Seq(
      "jp.crudefox" %% "crudefox-oauth2-play2" % "1.0-SNAPSHOT"
  )

  val main = play.Project(appName, appVersion, appDependencies).
    settings(
      ...
     )

}
```
## ide
ex. add eclipse classpath, jar.

## 

## controller sample

```
/**
 *  oauth2 controler
 *
 */
object SOAuth2ProviderController extends Controller {

  def auth = Action { implicit request =>
    try {
      //dynamically recognize an OAuth profile based on request characteristic (params,
      // method, content type etc.), perform validation
      val oauthRequest = new OAuthAuthzRequest(Bridge.convert(request))

      //some code ....
      if (oauthRequest.getClientSecret() == null) {
        //              throw OAuthProblemException.error("404", "no such user")
      }

      //build OAuth response
      val resp =
        OAuthASResponse.
          authorizationResponse(Bridge.convert(request), 302).
          //new OAuthAuthorizationResponseBuilder(request, 302).
          setCode("hfsfhkjsdf").
          //location("http://app-host:9000/authz").
          location(routes.SOAuth2ProviderController.auth.absoluteURL(false)).
          buildQueryMessage

      Found(resp.getLocationUri())

      //if something goes wrong
    } catch {
      case ex: OAuthProblemException =>

        try {
          val resp = OAuthResponse.
            errorResponse(404)
            .error(ex)
            .location(routes.SOAuth2ProviderController.err.absoluteURL(false))
            .buildQueryMessage();
          Redirect(resp.getLocationUri());

        } catch {
          case e: OAuthSystemException =>
            e.printStackTrace();
            InternalServerError(e.getMessage())
        }
      case ex: OAuthSystemException =>
        ex.printStackTrace()
        InternalServerError(ex.getMessage())

    }

  }

  def token = Action { implicit request =>

    val oauthIssuerImpl: OAuthIssuer = new OAuthIssuerImpl(new MD5Generator())

    try {
      val oauthRequest: OAuthTokenRequest = new OAuthTokenRequest(Bridge.convert(request));

      val authzCode = oauthRequest.getCode();

      // some code
      // System.out.println(authzCode);

      val accessToken = oauthIssuerImpl.accessToken();
      val refreshToken = oauthIssuerImpl.refreshToken();

      // some code
      System.out.println(accessToken);
      System.out.println(refreshToken);

      val r = OAuthASResponse
        .tokenResponse(200) //HttpServletResponse.SC_OK
        .setAccessToken(accessToken)
        .setExpiresIn("3600")
        .setRefreshToken(refreshToken)
        .buildJSONMessage()

      Ok(r.getBody());

      //      Ok("aaa");

      //if something goes wrong
    } catch {
      case ex: OAuthProblemException =>
        var r: OAuthResponse = null;
        try {
          r = OAuthResponse
            .errorResponse(401)
            .error(ex)
            .buildJSONMessage();
        } catch {
          case e: OAuthSystemException =>
            e.printStackTrace();
            InternalServerError(e.getMessage());
        }

        InternalServerError(r.getBody());

      case ex: OAuthSystemException =>
        ex.printStackTrace()
        InternalServerError(ex.getMessage())

    }

  }

  def err = Action { implicit request =>
    Ok("エラー");
  }

}
```

thank's,  
"Apache Amber" , "cleanyong/oauth2play2scala". and play2
