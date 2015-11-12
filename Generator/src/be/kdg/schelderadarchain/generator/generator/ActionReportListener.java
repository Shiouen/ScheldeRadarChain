package be.kdg.schelderadarchain.generator.generator;

import be.kdg.schelderadarchain.generator.dom.ActionReport;

/**
 * Created by Cas on 10/11/2015.
 */
public interface ActionReportListener {

    void onActionReportReceived(ActionReport actionReport);
}
