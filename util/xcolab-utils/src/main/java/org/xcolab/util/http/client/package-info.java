/**
 * This package contains classes to help building queries for RESTful services.
 *
 * In most cases, the entry point for the user will be through an instance of the
 * {@link org.xcolab.util.http.client.RestResource1} class.
 *
 * A RestResource can be created the following way:
 * {@code
 *      private static final RestResource<ContentArticle, Long> contentArticleResource =
 *             new RestResource1<>(ContentResource.CONTENT_ARTICLE, ContentArticle.TYPES);
 * }
 *
 * Once you have an instance, you can construct and execute queries using the fluid API:
 *
 * Example {@link org.xcolab.util.http.client.queries.ListQuery}:
 * {@code
 *      contentArticleResource.list()                   // 1
 *              .queryParam("folderId", 123)            // 2
 *              .execute();                             // 3
 * }
 *
 * In the above example, we create a new list query (1), add a query parameter with the
 * name {@code "folderId} to it (2), and then execute it to retrieve the resulting list (3).
 * In Step 3, the following query will be executed:
 * {@code GET http://xcolab-content-service/contestArticles?folderId=123}
 *
 * The address of the service, {@code http://xcolab-content-service} is retrieved from the
 * {@link org.xcolab.util.http.client.CoLabService} enum, which is specified in the
 * {@link org.xcolab.util.http.client.enums.ResourceEnum} used to create the RestResource.
 *
 * @see org.xcolab.util.http.client.RestResource
 * @see org.xcolab.util.http.client.queries.Query
 */
package org.xcolab.util.http.client;
