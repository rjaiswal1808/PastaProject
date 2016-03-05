package com.helpiez.app.http;

/**
 * Created by sanjeev.b1 on 1/25/2016.
 */
public class URLs {

    private static final String SEPARATOR = "/";

    public static final String URL_VERSION
            = "http://techiezjunkyard.com/api/version";
    public static final String URL_ZONES
            = "http://techiezjunkyard.com/api/zones";
    private static final String URL_PROJECTS
            = "http://techiezjunkyard.com/api/projects";
    private static final String URL_IMAGE
            = "http://techiezjunkyard.com/api/projects";

    public static String getProjectsUrl() {
        return getProjectsUrl("ncr");
    }

    public static String getProjectsUrl(String location) {
        StringBuilder builder = new StringBuilder();
        builder.append(URL_PROJECTS).append(SEPARATOR).append(location);
        return builder.toString();
    }

    public static String getImageUrl(String id) {
        StringBuilder builder = new StringBuilder();
        builder.append(URL_IMAGE).append(SEPARATOR).append(id);
        return builder.toString();
    }

}
