package com.example.pulsepoint.core.network;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class FakeTickerProvider_Factory implements Factory<FakeTickerProvider> {
  @Override
  public FakeTickerProvider get() {
    return newInstance();
  }

  public static FakeTickerProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeTickerProvider newInstance() {
    return new FakeTickerProvider();
  }

  private static final class InstanceHolder {
    static final FakeTickerProvider_Factory INSTANCE = new FakeTickerProvider_Factory();
  }
}
