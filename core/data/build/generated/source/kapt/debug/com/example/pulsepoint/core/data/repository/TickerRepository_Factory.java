package com.example.pulsepoint.core.data.repository;

import com.example.pulsepoint.core.network.FakeTickerProvider;
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
public final class TickerRepository_Factory implements Factory<TickerRepository> {
  private final Provider<FakeTickerProvider> tickerProvider;

  private TickerRepository_Factory(Provider<FakeTickerProvider> tickerProvider) {
    this.tickerProvider = tickerProvider;
  }

  @Override
  public TickerRepository get() {
    return newInstance(tickerProvider.get());
  }

  public static TickerRepository_Factory create(Provider<FakeTickerProvider> tickerProvider) {
    return new TickerRepository_Factory(tickerProvider);
  }

  public static TickerRepository newInstance(FakeTickerProvider tickerProvider) {
    return new TickerRepository(tickerProvider);
  }
}
