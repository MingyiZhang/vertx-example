import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;

public class HelloWorldRxJavaVerticle extends AbstractVerticle {

  static final Logger logger = LoggerFactory.getLogger(HelloWorldRxJavaVerticle.class);

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(
        new HelloWorldRxJavaVerticle(),
        res -> {
          if (res.succeeded()) {
            logger.info("Deployment id is: " + res.result());
          } else {
            logger.info("Deployment failed!");
          }
        });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx
        .createHttpServer()
        .requestHandler(r -> r.response().end("Hi there Vert.x!"))
        .listen(
            8080,
            result -> {
              if (result.succeeded()) {
                startPromise.complete();
              } else {
                startPromise.fail(result.cause());
              }
            });
  }
}
