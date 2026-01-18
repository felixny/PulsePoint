package com.example.pulsepoint.core.notifications.di;

import android.content.Context;
import com.example.pulsepoint.core.notifications.PulseNotificationManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class NotificationsModule_ProvidePulseNotificationManagerFactory implements Factory<PulseNotificationManager> {
  private final Provider<Context> contextProvider;

  private NotificationsModule_ProvidePulseNotificationManagerFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PulseNotificationManager get() {
    return providePulseNotificationManager(contextProvider.get());
  }

  public static NotificationsModule_ProvidePulseNotificationManagerFactory create(
      Provider<Context> contextProvider) {
    return new NotificationsModule_ProvidePulseNotificationManagerFactory(contextProvider);
  }

  public static PulseNotificationManager providePulseNotificationManager(Context context) {
    return Preconditions.checkNotNullFromProvides(NotificationsModule.INSTANCE.providePulseNotificationManager(context));
  }
}
