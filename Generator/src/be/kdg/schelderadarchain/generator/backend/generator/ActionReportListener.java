package be.kdg.schelderadarchain.generator.backend.generator;

import be.kdg.schelderadarchain.generator.backend.dom.ActionReport;

/**
 * Used to get callbacks from an action report
 *
 * @author Cas Decelle
 */

public interface ActionReportListener {
    /**
     * Called when the an action report is received
     * @param actionReport
     */
    void onActionReportReceived(ActionReport actionReport);
}
