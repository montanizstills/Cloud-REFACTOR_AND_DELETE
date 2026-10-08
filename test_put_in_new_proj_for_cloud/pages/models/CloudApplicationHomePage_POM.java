package com.vertexinc.cloud.test.pages.models;

import org.openqa.selenium.By;

public interface CloudApplicationHomePage_POM {
    default By getDashboardButton() {
        return By.cssSelector("a[href='/Admin/AdminDash']");
    }

    default By getRatesJurisdictionButton() {
        return By.cssSelector("a[href='/Admin/RateLookup']");
    }

    default By getOspLogsSearchButton() {
        return By.cssSelector("a[href='/Admin/OspLogsSearch']");
    }

    default By getQueueServiceButton() {
        return By.cssSelector("a[href='/Admin/QueueService/QueueServiceSummaryDetail']");
    }

    default By getCtcImpactButton() {
        return By.cssSelector("a[href='/Admin/AdminDash#']");
    }

    default By getCtcImpactTaxAreaTransactionsButton() {
        return By.cssSelector("a[href='/Admin/CtcImpact/TaxAreaTransactions']");
    }

    default By getCtcImpactTaxabilityCategoriesButton() {
        return By.cssSelector("a[href='/Admin/CtcImpact/TaxabilityCategories']");
    }

    default By getCtcImpactReturnsButton() {
        return By.cssSelector("a[href='/Admin/CtcImpact/Returns']");
    }

    default By getCtcImpactStateRegistrationsButton() {
        return By.cssSelector("a[href='/Admin/CtcImpact/StateRegistrations']");
    }

    default By getLogsButton() {
        return By.xpath("/html/body/div[1]/div/div[1]/div[1]/ul/li[6]/a");//Main has no redirect. Redirect Home. Brittle implementation.;
    }

    default By getLogsTaxLinkLogsButton() {
        return By.cssSelector("a[href='/Admin/LogFile']");
    }

    default By getLogsECWLogsButton() {
        return By.cssSelector("a[href='/Admin/EcWizardLogs']");
    }

    default By getLogsBigCommerceLogs() {
        return By.cssSelector("a[href='/Admin/BigCommerceLogs']");
    }

    /*TODO - test clicking this endpoint/button*/
    default By getManageCustomersButton() {
        return By.cssSelector("a[href='/Admin/CustomerManagement']");
    }

    default By getPremiumCustomersButton() {
        return By.cssSelector("a[href='#']");//Main has no redirect. Redirect Home.;
    }

    default By getPremiumCustomersPremiumCustomersButton() {
        return By.cssSelector("a[href='/Admin/Premium']");
    }

    default By getPremiumCustomersDownloadReturnsFilesButton() {
        return By.cssSelector("a[href='/Admin/Premium/DownloadReturnFiles']");
    }

    default By getMetricsButton() {
        return By.cssSelector("a[href='/Admin/Metrics']");
    }

    default By getDocumentCenterButton() {
        return By.cssSelector("a[href='/Admin/DocumentCenter']");
    }

    default By getInviteUsersButton() {
        return By.cssSelector("a[href='/Admin/Users/Invite']");
    }

    default By getVertexUsersButton() {
        return By.cssSelector("a[href='/Admin/Users']");
    }

    default By getVertexCloudVarsButton() {
        return By.cssSelector("a[href='/Admin/Clients/Vars']");
    }

    default By getInlineHelpEditorButton() {
        return By.cssSelector("a[href='/Admin/InlineHelp']");
    }

    default By getUpdateNotificationButton() {
        return By.cssSelector("a[href='/Admin/Notifier']");
    }

    default By getManageOSeriesConfigButton() {
        return By.cssSelector("a[href='/Admin/ManageOSeries']");
    }

    default By getManageReturnsConfigButton() {
        return By.cssSelector("a[href='/Admin/ReturnFields']");
    }

    default By getManageTaxCalendarButton() {
        return By.cssSelector("a[href='/Admin/TaxCalendar']");
    }

    default By getErrorMonitorButton() {
        return By.cssSelector("a[href='/Admin/ManageQueueWorkflow']");
    }

    default By getSystemConfigurationButton() {
        return By.cssSelector("a[href='/Admin/SystemConfiguration']");
    }

    default By getOAuthCredentialManagementButton() {
        return By.cssSelector("a[href='/Admin/OAuthCredentials/Create']");
    }

    default By getCollapseButton() {
        return By.cssSelector("a[href='#']");//Main has no redirect. Redirect Home.;
    }

    default By getVisitHelpCenterButton() {
        return By.cssSelector("a[href='/Admin/VisitHelpCenter']");
    }

    default By getWhatsNewButton() {
        return By.cssSelector("a[href='/Admin/VisitHelpCenter/WhatsNew']");
    }

    default By getLogoutButton(){
        return By.cssSelector("a[id='logout']");
    }

}
