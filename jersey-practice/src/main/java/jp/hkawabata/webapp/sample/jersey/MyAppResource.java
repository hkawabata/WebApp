package jp.hkawabata.webapp.sample.jersey;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class MyAppResource {
    @Inject
    private IZooKeeperWatcher zkWatcher;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String rootPage() {
        return "<html><body><h1>Root Page</h1></body></html>";
    }

    @GET
    @Path("/xml")
    @Produces(MediaType.APPLICATION_XML)
    public String xml() {
        return "<tag>sample xml</tag>";
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String json() {
        return "{\"num\": 100}";
    }

    /**
     * 同じ /acceptTypeTest というパスでアクセスされたとき、リクエストの Accept ヘッダの内容次第で挙動を切り替える
     * 以下の3つを叩いたときに結果が変わる
     *   - curl --dump-header - -H 'Accept:text/xml' http://localhost:8080/acceptTypeTest
     *   - curl --dump-header - -H 'Accept:application/json' http://localhost:8080/acceptTypeTest
     *   - curl --dump-header - -H 'Accept:plain/text' http://localhost:8080/acceptTypeTest
     */
    @GET
    @Path("/acceptTypeTest")
    @Produces(MediaType.APPLICATION_JSON)
    public String acceptTypeTestOnlyJSON() {
        return "{\"result\": \"JSON が要求されたため JSON 形式で返却します。\"}";
    }
    @GET
    @Path("/acceptTypeTest")
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public String acceptTypeTestOnlyXML() {
        return "<result>XML が要求されたため XML 形式で返却します</result>";
    }
    @GET
    @Path("/acceptTypeTest")
    public String acceptTypeTestOthers() {
        return "JSON/XML ともに Accept ヘッダに含まれなかったため、plain/text を返却します";
    }

    /**
     * リクエストパラメータ・パスパラメータを受け取って使用する
     * 受け取り時にユーザ定義のオブジェクト (User) への変換も行う
     */
    @GET
    @Path("/paramTest/{pathParam}")
    public String paramTest(
            @PathParam("pathParam") String pathParam,
            @DefaultValue("false") @QueryParam("debug") boolean debug,
            @DefaultValue("太郎") @QueryParam("user") User user
    ){
        String res = String.format("pathParam: %s, QueryParam: %s", pathParam, user.name);
        if (debug) {
            res += " (debug mode)";
        }
        return res;
    }
    public static class User {
        String name;
        public User(String user) {
            this.name = user;
        }
    }

    /**
     * ZooKeeper と接続してデータを取ってくる
     * - アプリケーションサーバ起動時に一度だけ ZooKeeper からデータを取得
     * - その後は ZooKeeper のデータが変更されたら即時再フェッチして値を更新
     *
     * @return
     */
    @GET
    @Path("/zk")
    @Produces(MediaType.TEXT_PLAIN)
    public String zk() {
        return zkWatcher.s();
    }
}
