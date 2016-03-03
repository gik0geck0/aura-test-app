<aura:application>
    <!-- <link rel="stylesheet" href="/css/assets/styles/salesforce-lightning-design-system.css" /> -->
    <link rel="stylesheet" href="/css/bootstrap-3.3.6-dist/css/bootstrap.css" />

    <script src="/css/jquery-1.12.0.js" />
    <!-- <script src="/css/bootstrap-3.3.6-dist/js/bootstrap.js" /> -->

    <h1>The page</h1>
    <twitter:title />

    <routing:SimpleRoutedPath route="home|^$" component="twitter:home" />
    <routing:SimpleRoutedPath route="profile" component="twitter:profile" />
</aura:application>
