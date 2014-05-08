package jp.crudefox.oauth2play2.oauth2.bridge;

import java.util.List;
import java.util.Map;

import play.api.mvc.AnyContent;
import play.api.mvc.Headers;
import scala.Option;


//import play.api.http.MediaType$;
//import play.api.mvc.Session$;


public interface BridgeRequest {




	public AnyContent body();

	//public Map<String, List<String>> headers();
	public Headers headers();

	public Long id();

	public String method();

	public String path();

	//public String queryString(String key);
	public Map<String, List<String>> queryString();


	public String remoteAddress();

	//public Boolean secure();

	//public scala.Predef.Map<String, String> tags();

	//uri: String
	//The complete request URI, containing both path and query string.
	public String uri();

	//	The HTTP version.
	public String version();




//	Concrete Value Members
//	lazy val
//	acceptLanguages: Seq[Lang]
//	The Request Langs extracted from the Accept-Language header and sorted by preference (preferred first).


//	lazy val
//	acceptedTypes: Seq[MediaRange]


//	def
//	accepts(mimeType: String): Boolean
//	Check if this request accepts a given media type.


	//	Returns the charset of the request for text-based body
	public String charset();


	/*
	 * 	contentType: Option[String]
	 *  Returns the value of the Content-Type header (without the parameters (eg charset))
	 */
	public String contentType();



	/*
	 * 	cookies: Cookies
	 * The HTTP cookies.
	 */
	//public Cookies cookies();


//	def
//	copy(id: Long = this.id, tags: Map[String, String] = this.tags, uri: String = this.uri, path: String = this.path, method: String = this.method, version: String = this.version, queryString: Map[String, Seq[String]] = this.queryString, headers: Headers = this.headers, remoteAddress: String = this.remoteAddress, secure: Boolean = this.secure): RequestHeader
//	Copy the request.


	/*
	 * 	domain: String
	 * The HTTP domain
	 */
	public String domain();




//	lazy val
//	flash: Flash
//	Parses the Flash cookie and returns the Flash data.


	/*
	 * 	getQueryString(key: String): Option[String]
	 * Helper method to access a queryString parameter.
	 */
	public Option<String> getQueryString(String key);


	/*
	 * 	host: String
	 *  The HTTP host (domain, optionally port)
	 */
	public String host();


//	def
//	map[B](f: (A) ⇒ B): Request[B]
//	Transform the request body.


	/*
	 * 	mediaType: Option[MediaType]
	 *  The media type of this request.
	 */
	//public MediaType mediaType();
	//public String mediaType();



	/*
	 * 	rawQueryString: String
	 *  Returns the raw query string.
	 */
	public String rawQueryString();


	/*
	 * 	session: Session
	 *  Parses the Session cookie and returns the Session data.
	 */
	//public Session session();
	//public String session(String key);


}
