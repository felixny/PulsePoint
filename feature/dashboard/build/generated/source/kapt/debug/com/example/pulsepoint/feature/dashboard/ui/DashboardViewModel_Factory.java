package com.example.pulsepoint.feature.dashboard.ui;

import android.app.Application;
import com.example.pulsepoint.core.data.repository.TickerRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<TickerRepository> tickerRepositoryProvider;

  private DashboardViewModel_Factory(Provider<Application> applicationProvider,
      Provider<TickerRepository> tickerRepositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.tickerRepositoryProvider = tickerRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(applicationProvider.get(), tickerRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<TickerRepository> tickerRepositoryProvider) {
    return new DashboardViewModel_Factory(applicationProvider, tickerRepositoryProvider);
  }

  public static DashboardViewModel newInstance(Application application,
      TickerRepository tickerRepository) {
    return new DashboardViewModel(application, tickerRepository);
  }
}
