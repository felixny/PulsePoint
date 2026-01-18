package com.example.pulsepoint.core.notifications;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class PulseNotificationManager_Factory implements Factory<PulseNotificationManager> {
  private final Provider<Context> contextProvider;

  private PulseNotificationManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PulseNotificationManager get() {
    return newInstance(contextProvider.get());
  }

  public static PulseNotificationManager_Factory create(Provider<Context> contextProvider) {
    return new PulseNotificationManager_Factory(contextProvider);
  }

  public static PulseNotificationManager newInstance(Context context) {
    return new PulseNotificationManager(context);
  }
}
