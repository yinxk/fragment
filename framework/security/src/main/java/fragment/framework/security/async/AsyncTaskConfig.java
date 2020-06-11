package fragment.framework.security.async;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
@EnableConfigurationProperties(ExecutorProperties.class)
public class AsyncTaskConfig implements AsyncConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskConfig.class);

    private SimpleAsyncUncaughtExceptionHandler uncaughtExceptionHandler = new SimpleAsyncUncaughtExceptionHandler();

    @Autowired
    private ExecutorProperties hsdExecutorProperties;

    @Override
    @Bean(Constants.ASYNC_EXECUTOR_NAME)
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(hsdExecutorProperties.getCorePoolSize());
        threadPool.setMaxPoolSize(hsdExecutorProperties.getMaxPoolSize());
        threadPool.setQueueCapacity(hsdExecutorProperties.getQueueCapacity());
        threadPool.setKeepAliveSeconds(hsdExecutorProperties.getKeepAliveSeconds());
        threadPool.setWaitForTasksToCompleteOnShutdown(hsdExecutorProperties.isWaitForTasksToCompleteOnShutdown());
        threadPool.setAwaitTerminationSeconds(hsdExecutorProperties.getAwaitTerminationSeconds());
        threadPool.setThreadNamePrefix(hsdExecutorProperties.getThreadNamePrefix());

        try {
            threadPool.setRejectedExecutionHandler(
                    (RejectedExecutionHandler) Class.forName(hsdExecutorProperties.getRejectedExecutionHandler()).newInstance());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            LOGGER.warn("初始化线程池RejectedExecutionHandler失败，使用默认策略CallerRunsPolicy", e);
            threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        }

        threadPool.initialize();

        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return uncaughtExceptionHandler;
    }
}

