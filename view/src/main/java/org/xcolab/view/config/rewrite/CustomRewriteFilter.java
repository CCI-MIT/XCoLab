package org.xcolab.view.config.rewrite;

import org.ocpsoft.common.pattern.WeightedComparator;
import org.ocpsoft.common.spi.ServiceEnricher;
import org.ocpsoft.common.util.Iterators;
import org.ocpsoft.logging.Logger;
import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.AbstractRewrite;
import org.ocpsoft.rewrite.Version;
import org.ocpsoft.rewrite.el.spi.ExpressionLanguageProvider;
import org.ocpsoft.rewrite.event.Rewrite;
import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.ocpsoft.rewrite.servlet.RewriteLifecycleContext;
import org.ocpsoft.rewrite.servlet.ServletRewriteProvider;
import org.ocpsoft.rewrite.servlet.config.lifecycle.JoinRewriteLifecycleListener;
import org.ocpsoft.rewrite.servlet.event.BaseRewrite;
import org.ocpsoft.rewrite.servlet.event.InboundServletRewrite;
import org.ocpsoft.rewrite.servlet.http.HttpRewriteLifecycleContext;
import org.ocpsoft.rewrite.servlet.impl.DefaultHttpRewriteProvider;
import org.ocpsoft.rewrite.servlet.impl.DefaultRewriteLifecycleListener;
import org.ocpsoft.rewrite.servlet.impl.HttpInboundRewriteProducer;
import org.ocpsoft.rewrite.servlet.impl.HttpOutboundRewriteProducer;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteContextImpl;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteRequestCycleWrapper;
import org.ocpsoft.rewrite.servlet.impl.HttpRewriteResultHandler;
import org.ocpsoft.rewrite.servlet.spi.ContextListener;
import org.ocpsoft.rewrite.servlet.spi.InboundRewriteProducer;
import org.ocpsoft.rewrite.servlet.spi.OutboundRewriteProducer;
import org.ocpsoft.rewrite.servlet.spi.RequestCycleWrapper;
import org.ocpsoft.rewrite.servlet.spi.RequestListener;
import org.ocpsoft.rewrite.servlet.spi.RequestParameterProvider;
import org.ocpsoft.rewrite.servlet.spi.RewriteLifecycleListener;
import org.ocpsoft.rewrite.servlet.spi.RewriteResultHandler;
import org.ocpsoft.rewrite.spi.ConfigurationCacheProvider;
import org.ocpsoft.rewrite.spi.InvocationResultHandler;
import org.ocpsoft.rewrite.spi.RewriteProvider;
import org.ocpsoft.rewrite.util.ServiceLogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CustomRewriteFilter implements Filter {

    private static final Logger log = Logger.getLogger(RewriteFilter.class);

    private static final String FILTER_COUNT_KEY = RewriteFilter.class.getName() + "_FILTER_COUNT";


    private List<RewriteLifecycleListener<Rewrite>> listeners;
    private List<RequestCycleWrapper<ServletRequest, ServletResponse>> wrappers;
    private List<RewriteProvider<ServletContext, Rewrite>> providers;
    private List<RewriteResultHandler> resultHandlers;
    private List<InboundRewriteProducer<ServletRequest, ServletResponse>> inbound;
    private List<OutboundRewriteProducer<ServletRequest, ServletResponse, Object>> outbound;

    private ServletContext servletContext;

    @Override
    @SuppressWarnings("unchecked")
    public void init(FilterConfig filterConfig) {
        log.info("CustomRewriteFilter starting up...");

        servletContext = filterConfig.getServletContext();

        listeners = Arrays.asList(new RewriteLifecycleListener[]{
                new JoinRewriteLifecycleListener(), new DefaultRewriteLifecycleListener()});
        wrappers = Arrays.asList(new RequestCycleWrapper[]{new HttpRewriteRequestCycleWrapper()});
        providers = Arrays.asList(new RewriteProvider[]{new DefaultHttpRewriteProvider()});
        providers.forEach(provider -> provider.init(servletContext));
        resultHandlers = Arrays.asList(new RewriteResultHandler[]{new HttpRewriteResultHandler()});
        inbound = Arrays.asList(new InboundRewriteProducer[]{new HttpInboundRewriteProducer()});
        outbound = Arrays.asList(new OutboundRewriteProducer[]{new HttpOutboundRewriteProducer()});

        listeners.sort(new WeightedComparator());
        wrappers.sort(new WeightedComparator());
        providers.sort(new WeightedComparator());
        resultHandlers.sort(new WeightedComparator());
        inbound.sort(new WeightedComparator());
        outbound.sort(new WeightedComparator());

        ServiceLogger.logLoadedServices(log, RewriteLifecycleListener.class, listeners);
        ServiceLogger.logLoadedServices(log, RequestCycleWrapper.class, wrappers);
        ServiceLogger.logLoadedServices(log, RewriteProvider.class, providers);
        ServiceLogger.logLoadedServices(log, RewriteResultHandler.class, resultHandlers);
        ServiceLogger.logLoadedServices(log, InboundRewriteProducer.class, inbound);
        ServiceLogger.logLoadedServices(log, OutboundRewriteProducer.class, outbound);

      /*
       * Log more services for debug purposes only.
       */
        ServiceLogger.logLoadedServices(log, ContextListener.class,
                Iterators.asList(ServiceLoader.load(ContextListener.class)));

        ServiceLogger.logLoadedServices(log, RequestListener.class,
                Iterators.asList(ServiceLoader.load(RequestListener.class)));

        ServiceLogger.logLoadedServices(log, RequestParameterProvider.class,
                Iterators.asList(ServiceLoader.load(RequestParameterProvider.class)));

        ServiceLogger.logLoadedServices(log, ExpressionLanguageProvider.class,
                Iterators.asList(ServiceLoader.load(ExpressionLanguageProvider.class)));

        ServiceLogger.logLoadedServices(log, InvocationResultHandler.class,
                Iterators.asList(ServiceLoader.load(InvocationResultHandler.class)));

        ServiceLogger.logLoadedServices(log, ServiceEnricher.class,
                Iterators.asList(ServiceLoader.load(ServiceEnricher.class)));

      /*
       * Load ConfigurationProviders and ConfigurationCacheProviders here solely so that we
       * can see registered implementations at boot time.
       */
        ServiceLogger.logLoadedServices(log, ConfigurationCacheProvider.class,
                Iterators.asList(ServiceLoader.load(ConfigurationCacheProvider.class)));

//        List<ConfigurationProvider<?>> configurations = Iterators.asList(ServiceLoader
//                .load(ConfigurationProvider.class));
//        ServiceLogger.logLoadedServices(log, ConfigurationProvider.class, configurations);
//
//        for (RewriteProvider<ServletContext, Rewrite> provider : providers) {
//            if (provider instanceof ServletRewriteProvider)
//                ((ServletRewriteProvider<?>) provider).init(servletContext);
//        }
//
//        if ((configurations == null) || configurations.isEmpty())
//        {
//            if (log.isWarnEnabled())
//                log.warn("No ConfigurationProviders were registered: " +
//                        "Rewrite will not be enabled on this application. " +
//                        "Did you forget to create a '/META-INF/services/" + ConfigurationProvider.class.getName() +
//                        " file containing the fully qualified name of your provider implementation?");
//        }

        if (log.isInfoEnabled())
            log.info(Version.getFullName() + " initialized.");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException
    {
        InboundServletRewrite<ServletRequest, ServletResponse> event = createRewriteEvent(request,
                response);

        if (event == null)
        {
            if (log.isWarnEnabled())
                log.warn("No Rewrite event was produced - RewriteFilter disabled on this request.");
            chain.doFilter(request, response);
        }
        else
        {
            incrementFilterCount(request);

            if (request.getAttribute(RewriteLifecycleContext.LIFECYCLE_CONTEXT_KEY) == null)
            {
                HttpRewriteLifecycleContext
                        context = new HttpRewriteContextImpl(inbound, outbound, listeners,
                        resultHandlers, wrappers, providers);
                request.setAttribute(RewriteLifecycleContext.LIFECYCLE_CONTEXT_KEY, context);
            }

            for (RewriteLifecycleListener<Rewrite> listener : listeners)
            {
                if (listener.handles(event))
                    listener.beforeInboundLifecycle(event);
            }

            for (RequestCycleWrapper<ServletRequest, ServletResponse> wrapper : wrappers)
            {
                if (wrapper.handles(event))
                {
                    event.setRequest(wrapper.wrapRequest(event.getRequest(), event.getResponse(), servletContext));
                    event.setResponse(wrapper.wrapResponse(event.getRequest(), event.getResponse(), servletContext));
                }
            }

            try {
                rewrite(event);
            }
            catch (ServletException e) {
                if (getFilterCount(request) == 1)
                    AbstractRewrite.logEvaluatedRules(event, Level.ERROR);

                decrementFilterCount(request);
                throw e;
            }
            catch (RuntimeException e) {
                if (getFilterCount(request) == 1)
                    AbstractRewrite.logEvaluatedRules(event, Level.ERROR);

                decrementFilterCount(request);
                throw e;
            }

            if (!event.getFlow().is(BaseRewrite.ServletRewriteFlow.ABORT_REQUEST))
            {
                if (log.isDebugEnabled())
                    log.debug("RewriteFilter passing control of request to underlying application.");

                if (response.isCommitted() && log.isWarnEnabled())
                    log.warn("Response has already been committed, and further write operations are not permitted. "
                            + "This may result in an IllegalStateException being triggered by the underlying application. To avoid this situation, "
                            + "consider adding a Rule `.when(Direction.isInbound().and(Response.isCommitted())).perform(Lifecycle.abort())`, or "
                            + "figure out where the response is being incorrectly committed and correct the bug in the offending code.");

                chain.doFilter(event.getRequest(), event.getResponse());

                if (log.isDebugEnabled())
                    log.debug("Control of request returned to RewriteFilter.");
            }

            for (RewriteLifecycleListener<Rewrite> listener : listeners)
            {
                if (listener.handles(event))
                    listener.afterInboundLifecycle(event);
            }

            if (getFilterCount(request) == 1)
                AbstractRewrite.logEvaluatedRules(event, Level.DEBUG);

            decrementFilterCount(request);
        }
    }

    public InboundServletRewrite<ServletRequest, ServletResponse> createRewriteEvent(final ServletRequest request,
            final ServletResponse response)
    {
        for (InboundRewriteProducer<ServletRequest, ServletResponse> producer : inbound)
        {
            InboundServletRewrite<ServletRequest, ServletResponse> event = producer
                    .createInboundRewrite(request, response, servletContext);
            if (event != null)
                return event;
        }
        return null;
    }

    private void rewrite(final InboundServletRewrite<ServletRequest, ServletResponse> event)
            throws ServletException, IOException
    {
        int listenerCount = listeners.size();
        for (int i = 0; i < listenerCount; i++)
        {
            RewriteLifecycleListener<Rewrite> listener = listeners.get(i);
            if (listener.handles(event))
                listener.beforeInboundRewrite(event);
        }

        int providerCount = providers.size();
        for (int i = 0; i < providerCount; i++)
        {
            RewriteProvider<ServletContext, Rewrite> provider = providers.get(i);
            if (provider.handles(event))
            {
                provider.rewrite(event);

                if (event.getFlow().is(BaseRewrite.ServletRewriteFlow.HANDLED))
                {
                    if (log.isDebugEnabled())
                        log.debug("Event flow marked as HANDLED. No further processing will occur.");
                    break;
                }
            }
        }

        for (int i = 0; i < listenerCount; i++)
        {
            RewriteLifecycleListener<Rewrite> listener = listeners.get(i);
            if (listener.handles(event))
                listener.afterInboundRewrite(event);
        }

        int handlerCount = resultHandlers.size();
        for (int i = 0; i < handlerCount; i++)
        {
            if (resultHandlers.get(i).handles(event))
                resultHandlers.get(i).handleResult(event);
        }
    }

    @Override
    public void destroy()
    {
        log.info("RewriteFilter shutting down...");

        for (RewriteProvider<ServletContext, Rewrite> provider : providers) {
            if (provider instanceof ServletRewriteProvider)
                ((ServletRewriteProvider<?>) provider).shutdown(servletContext);
        }

        log.info("RewriteFilter deactivated.");
    }

    private int getFilterCount(ServletRequest request)
    {
        return (Integer) request.getAttribute(FILTER_COUNT_KEY);
    }

    private void decrementFilterCount(ServletRequest request)
    {
        Integer count = (Integer) request.getAttribute(FILTER_COUNT_KEY);
        if (count != null)
        {
            count--;
        }
        request.setAttribute(FILTER_COUNT_KEY, count);
    }

    private void incrementFilterCount(ServletRequest request)
    {
        Integer count = (Integer) request.getAttribute(FILTER_COUNT_KEY);

        if (count == null)
            count = 1;
        else
            count++;

        request.setAttribute(FILTER_COUNT_KEY, count);
    }
}
