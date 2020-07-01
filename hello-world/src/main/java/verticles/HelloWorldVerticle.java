package verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class HelloWorldVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> future) throws Exception {
    vertx
        .createHttpServer()
        .requestHandler(r -> r.response().end("Hi there Vert.x!"))
        .listen(
            8080,
            result -> {
              if (result.succeeded()) {
                future.complete();
              } else {
                future.fail(result.cause());
              }
            });
  }
}
