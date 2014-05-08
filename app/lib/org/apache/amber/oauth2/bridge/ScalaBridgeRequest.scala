package lib.org.apache.amber.oauth2.bridge

import play.api.mvc.Request
import play.api.mvc.RequestHeader
import play.api.mvc.AnyContent
import play.api.mvc.Headers
import java.util.HashMap
import play.libs.F
import java.lang.String
import java.util.List
import java.util.Map



case class ScalaBridgeRequest(request: Request[AnyContent]) extends BridgeRequest {

  lazy val query_map = {
    val map = new java.util.HashMap[String, java.util.List[String]]
    for ((k, vs) <- request.queryString) {
      val values = new java.util.ArrayList[String]
      vs.foreach { v =>
        values.add(v)
      }
      map.put(k, values);
    }
    map
  }


  override def body(): AnyContent = {
    request.body
  }

  override def headers(): Headers = {
    request.headers
  }

  override def id() : java.lang.Long = {
    java.lang.Long.valueOf(request.id.toString)
  }

  override def method(): String = {
    request.method
  }

  override def path(): String = {
    request.path
  }

  override def queryString(): java.util.Map[String, java.util.List[String]] = {
    query_map;
  }

  override def remoteAddress(): String = {
    request.remoteAddress
  }

  override def uri(): String = {
    request.uri
  }

  override def version(): String = {
    request.version
  }

  override def charset(): String = {
    request.charset.get
  }

  override def contentType(): String = {
    request.contentType.get
  }

  override def domain(): String = {
    request.domain
  }

  override def getQueryString(key: String) : Option[String] = {
//    request.getQueryString(key).getOrElse(null)
//    request.queryString.get(key). map { v =>
//      val r = F.Some(v.head)
//      //v.foreach( vv => { if(v.head!=vv) r.add(vv) } )
//      r
//    }.getOrElse(new F.None)
    request.getQueryString(key)
  }

  override def host(): String = {
    request.host
  }

  //	def
  //	map[B](f: (A) ⇒ B): Request[B]
  //	Transform the request body.

  /*
	 * 	mediaType: Option[MediaType]
	 *  The media type of this request.
	 */
  //public MediaType mediaType();
  //public String mediaType();

  override def rawQueryString(): String = {
    request.rawQueryString
  }

  /*
	 * 	session: Session
	 *  Parses the Session cookie and returns the Session data.
	 */
  //public Session session();
  //public String session(String key);

}