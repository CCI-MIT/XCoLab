package org.xcolab.view.config.sentry;

import io.sentry.DefaultSentryClientFactory;
import io.sentry.SentryClient;
import io.sentry.dsn.Dsn;
import io.sentry.event.helper.ContextBuilderHelper;
import io.sentry.marshaller.Marshaller;
import io.sentry.marshaller.json.HttpInterfaceBinding;
import io.sentry.marshaller.json.JsonMarshaller;

public class CustomSentryClientFactory extends DefaultSentryClientFactory {

    @Override
    public SentryClient createSentryClient(Dsn dsn) {
        SentryClient sentryClient = new SentryClient(createConnection(dsn), getContextManager(dsn));
        sentryClient.addBuilderHelper(new CustomHttpEventBuilderHelper());
        sentryClient.addBuilderHelper(new ContextBuilderHelper(sentryClient));
        return configureSentryClient(sentryClient, dsn);
    }

    @Override
    protected Marshaller createMarshaller(Dsn dsn) {
        final JsonMarshaller marshaller = (JsonMarshaller) super.createMarshaller(dsn);
        HttpInterfaceBinding httpBinding = new HttpInterfaceBinding();
        marshaller.addInterfaceBinding(CustomSentryHttpInterface.class, httpBinding);
        return marshaller;
    }
}
