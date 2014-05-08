package jp.crudefox.oauth2play2.oauth2.bridge;

import play.api.mvc.AnyContent;
import play.api.mvc.Request;


public class Bridge {

	public static BridgeRequest convert(final Request<AnyContent> request){
		return new ScalaBridgeRequest(request);
	}


//	public static BridgeRequest convert(final Request<RequestHeader> request){
//		return new BridgeRequest() {
//
//			@Override
//			public String version() {
//				return request.version();
//			}
//
//			@Override
//			public String uri() {
//				return request.uri();
//			}
//
////			@Override
////			public  scala.Predef.Map<String, String> tags() {
////				return request.tags();
////			}
//
////			@Override
////			public String session(String key) {
////				return ((RequestHeader) request.).session(key);
////			}
//
////			@Override
////			public Boolean secure() {
////				return request.
////			}
//
//			@Override
//			public String remoteAddress() {
//				return request.remoteAddress();
//			}
//
//			@Override
//			public String rawQueryString() {
//				return request.rawQueryString();
//			}
//
//			@Override
//			public Map<String, List<String>> queryString() {
//				HashMap<String, List<String>> map = new HashMap<String, List<String>>();
//
////				for(.; ;){
////
////				}
//				return map;
//			}
//
//			@Override
//			public String path() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public String method() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public Long id() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public String host() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public Headers headers() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public Option<String> getQueryString(String key) {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public String domain() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public String contentType() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public String charset() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//
//			@Override
//			public AnyContent body() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//		};
//	}




}
