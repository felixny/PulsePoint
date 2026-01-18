package com.example.pulsepoint.core.notifications;

import com.example.pulsepoint.core.data.repository.TickerRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class LiveUpdateService_MembersInjector implements MembersInjector<LiveUpdateService> {
  private final Provider<TickerRepository> tickerRepositoryProvider;

  private final Provider<PulseNotificationManager> notificationManagerProvider;

  private LiveUpdateService_MembersInjector(Provider<TickerRepository> tickerRepositoryProvider,
      Provider<PulseNotificationManager> notificationManagerProvider) {
    this.tickerRepositoryProvider = tickerRepositoryProvider;
    this.notificationManagerProvider = notificationManagerProvider;
  }

  @Override
  public void injectMembers(LiveUpdateService instance) {
    injectTickerRepository(instance, tickerRepositoryProvider.get());
    injectNotificationManager(instance, notificationManagerProvider.get());
  }

  public static MembersInjector<LiveUpdateService> create(
      Provider<TickerRepository> tickerRepositoryProvider,
      Provider<PulseNotificationManager> notificationManagerProvider) {
    return new LiveUpdateService_MembersInjector(tickerRepositoryProvider, notificationManagerProvider);
  }

  @InjectedFieldSignature("com.example.pulsepoint.core.notifications.LiveUpdateService.tickerRepository")
  public static void injectTickerRepository(LiveUpdateService instance,
      TickerRepository tickerRepository) {
    instance.tickerRepository = tickerRepository;
  }

  @InjectedFieldSignature("com.example.pulsepoint.core.notifications.LiveUpdateService.notificationManager")
  public static void injectNotificationManager(LiveUpdateService instance,
      PulseNotificationManager notificationManager) {
    instance.notificationManager = notificationManager;
  }
}
