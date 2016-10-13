package com.ext.portlet.models.ui;

import edu.mit.cci.roma.client.TupleStatus;

import com.ext.portlet.model.ModelOutputChartOrder;
import com.ext.portlet.model.ModelOutputItem;

public class ModelOutputErrorBehavior {

    ErrorPolicy policy;
    String msg;
    TupleStatus status;

    private ModelOutputErrorBehavior(TupleStatus status, ErrorPolicy policy, String msg) {
        this.status = status;
        this.policy = policy;
        this.msg = msg;
    }

    public TupleStatus getStatus() {
        return status;
    }

    public ErrorPolicy getPolicy() {
        return policy;
    }

    public String getMessage() {
        return msg;
    }

    public static ModelOutputErrorBehavior createEmptyBehavior(TupleStatus status) {
        return new ModelOutputErrorBehavior(status,null,null);
    }

    public static ModelOutputErrorBehavior getBehavior(TupleStatus status, ModelOutputChartOrder item) {
        if (status == TupleStatus.OUT_OF_RANGE) {
            String policyName = item.getModelIndexRangePolicy();
            if (policyName == null || policyName.trim().equals("")) {
                return createEmptyBehavior(status);
            }
            String msg = item.getModelIndexRangeMessage();
           return new ModelOutputErrorBehavior(status,ErrorPolicy.valueOf(policyName),msg);
        } else if (status == TupleStatus.INVALID) {
            String policyName = item.getModelIndexErrorPolicy();
            if (policyName == null || policyName.trim().equals("")) {
                return createEmptyBehavior(status);
            }
            String msg = item.getModelIndexErrorMessage();
           return new ModelOutputErrorBehavior(status,ErrorPolicy.valueOf(policyName),msg);

        } else {
            return null;
        }
    }

     public static ModelOutputErrorBehavior getBehavior(TupleStatus status, ModelOutputItem item) {
        if (status == TupleStatus.OUT_OF_RANGE) {
            String policyName = item.getModelItemRangePolicy();
            if (policyName == null || policyName.trim().equals("")) {
                return createEmptyBehavior(status);
            }
            String msg = item.getModelItemRangeMessage();
           return new ModelOutputErrorBehavior(status,ErrorPolicy.valueOf(policyName),msg);
        } else if (status == TupleStatus.INVALID) {
            String policyName = item.getModelItemErrorPolicy();
            if (policyName == null || policyName.trim().equals("")) {
                return createEmptyBehavior(status);
            }
            String msg = item.getModelItemErrorMessage();
           return new ModelOutputErrorBehavior(status,ErrorPolicy.valueOf(policyName),msg);

        } else {
            return null;
        }
    }

}
