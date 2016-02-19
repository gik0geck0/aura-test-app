<aura:application>
    <h1>The page</h1>
    <twitter:title />

    <routing:SimpleRoutedPath route="home|^$" component="twitter:home" />
    <routing:SimpleRoutedPath route="profile" component="twitter:profile" />
</aura:application>
