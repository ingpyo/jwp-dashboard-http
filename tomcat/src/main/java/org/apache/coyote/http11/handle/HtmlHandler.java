package org.apache.coyote.http11.handle;

import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.request.start.HttpVersion;
import org.apache.coyote.http11.response.HttpResponse;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class HtmlHandler extends Handler {

    public static final String CONTENT_TYPE = "text/html;charset=utf-8";
    public static final String HTTP_STATUS_OK = "200 OK";

    @Override
    public HttpResponse handle(final HttpRequest request) throws IOException {
        final HttpVersion httpVersion = request.getHttpStartLine().getHttpVersion();
        final String responseBody = makeResponseBody(request.getHttpStartLine().getRequestTarget().getOrigin());

        return HttpResponse.of(httpVersion, HTTP_STATUS_OK, CONTENT_TYPE, responseBody);
    }

    private String makeResponseBody(final String requestTarget) throws IOException {
        final URL resource = getClass().getClassLoader().getResource("static/" + requestTarget);
        return new String(Files.readAllBytes(new File(resource.getFile()).toPath()));
    }
}